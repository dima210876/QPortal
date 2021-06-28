package my.project.QPortal.controllers;

import my.project.QPortal.model.*;
import my.project.QPortal.repository.*;
import my.project.QPortal.service.EmailService;
import my.project.QPortal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class MainController
{
    private final UserService userService;
    private final FieldRepository fieldRepository;
    private final OptionRepository optionRepository;
    private final QuestionnaireRepository questionnaireRepository;
    private final ResponseRepository responseRepository;
    private final ResponseFieldRepository responseFieldRepository;
    private final EmailService emailService;

    @Autowired
    public MainController(UserService userService,
                          FieldRepository fieldRepository,
                          OptionRepository optionRepository,
                          QuestionnaireRepository questionnaireRepository,
                          ResponseRepository responseRepository,
                          ResponseFieldRepository responseFieldRepository,
                          EmailService emailService)
    {
        this.userService = userService;
        this.fieldRepository = fieldRepository;
        this.optionRepository = optionRepository;
        this.questionnaireRepository = questionnaireRepository;
        this.responseRepository = responseRepository;
        this.responseFieldRepository = responseFieldRepository;
        this.emailService = emailService;
    }

    @PostMapping("/registration")
    public ResponseEntity<User> registerUser(@RequestBody User user)
    {
        if (userService.isEmailUnique(user.getEmail()))
        {
            emailService.send(user.getEmail(),
                    "QPortal registration", "You have been successfully registered!");
            return new ResponseEntity<>(userService.register(user), HttpStatus.CREATED);
        }
        else return new ResponseEntity<>(HttpStatus.CONFLICT);
    }


    @GetMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user)
    {
        Optional<User> currentUser = userService.login(user);
        if(currentUser.isPresent())
            return new ResponseEntity<User>(currentUser.get(), HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/editProfile/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") int id, @RequestBody User user)
    {
        Optional <User> currentUser = userService.getById(id);
        if(currentUser.isPresent())
        {
            String email = user.getEmail();
            if ( email.isEmpty() || email.isBlank() ||
                    (!user.getEmail().equals(currentUser.get().getEmail()) && !userService.isEmailUnique(email)))
            {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<User>(userService.update(id, user), HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/changePassword/{id}")
    public ResponseEntity<User> changePassword(@PathVariable("id") int id, @RequestBody ValueContainer newPassword)
    {
        Optional<User> currentUser = userService.getById(id);
        if(currentUser.isPresent())
        {
            if (newPassword.getStringValue().isEmpty() || newPassword.getStringValue().isBlank())
            {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            emailService.send(currentUser.get().getEmail(),
                    "QPortal changing password", "Account password has been successfully changed.");
            return new ResponseEntity<User>(userService.changePassword(id, newPassword.getStringValue()), HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/fields/{id}")
    public ResponseEntity<List<Field>> getFields(@PathVariable("id") int id)
    {
        Optional<User> currentUser = userService.getById(id);
        if(currentUser.isPresent())
        {
            List<Field> fieldList = currentUser.get().getQuestionnaire().getFields();
            return new ResponseEntity<List<Field>>(fieldList, HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/fields/{id}")
    public ResponseEntity<List<Field>> addField(@PathVariable("id") int id, @RequestBody Field newField)
    {
        Optional<User> currentUser = userService.getById(id);
        if(currentUser.isPresent())
        {
            if (newField.getQuestionnaire_id() == id)
            {
                int newId = (int) fieldRepository.count() + 1;
                newField.getOptions().forEach(option -> option.setField_id(newId));
                fieldRepository.save(newField);
                List<Field> fieldList = currentUser.get().getQuestionnaire().getFields();
                return new ResponseEntity<List<Field>>(fieldList, HttpStatus.OK);
            }
            else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/fields/{id}")
    public ResponseEntity<List<Field>> editField(@PathVariable("id") int id, @RequestBody Field updatedField)
    {
        Optional<User> currentUser = userService.getById(id);
        if(currentUser.isPresent())
        {
            Optional<Field> currentField = fieldRepository.findById(updatedField.getId());
            if(currentField.isPresent())
            {
                updatedField.getOptions().forEach(option -> option.setField_id(updatedField.getId()));
                Field field = currentField.get();
                field.getOptions().forEach(option -> optionRepository.delete(option));
                field.getOptions().clear();
                field.setLabel(updatedField.getLabel());
                field.setType(updatedField.getType());
                field.setRequired(updatedField.getRequired());
                field.setIsactive(updatedField.getIsactive());
                field.setOptions(updatedField.getOptions());

                List<Field> fieldList = currentUser.get().getQuestionnaire().getFields();
                return new ResponseEntity<List<Field>>(fieldList, HttpStatus.OK);
            }
            else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/fields/{id}")
    public ResponseEntity<List<Field>> deleteField(@PathVariable("id") int id, @RequestBody ValueContainer fieldId)
    {
        Optional<User> currentUser = userService.getById(id);
        if(currentUser.isPresent())
        {
            Optional<Field> currentField = fieldRepository.findById(fieldId.getIntValue());
            if(currentField.isPresent() &&
                    currentUser.get().getQuestionnaire().getFields().contains(currentField.get()))
            {
                fieldRepository.deleteById(fieldId.getIntValue());
                List<Field> fieldList = currentUser.get().getQuestionnaire().getFields();
                return new ResponseEntity<List<Field>>(fieldList, HttpStatus.OK);
            }
            else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/questionnaire/{id}")
    public ResponseEntity<List<Field>> getQuestionnaireFields(@PathVariable("id") int id)
    {
        Optional<Questionnaire> questionnaire = questionnaireRepository.findById(id);
        if(questionnaire.isPresent())
        {
            List<Field> fieldList = questionnaire.get().getFields();
            return new ResponseEntity<List<Field>>(fieldList, HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/questionnaire/{id}")
    public ResponseEntity<String> sendAnswers(@PathVariable("id") int id, @RequestBody Response response)
    {
        Optional<Questionnaire> questionnaire = questionnaireRepository.findById(id);
        if(questionnaire.isPresent())
        {
            response.setQuestionnaire_id(id);
            List <ResponseField> responseFields = response.getResponseFields();
            response.setResponseFields(new ArrayList<>());
            Response savedResponse = responseRepository.save(response);
            responseFields.forEach(responseField -> responseField.setResponse_id(savedResponse.getId()));
            savedResponse.setResponseFields(responseFields);
            responseRepository.save(savedResponse);
            return new ResponseEntity<String>("Thank you! Your response was saved.", HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/responses/{id}")
    public ResponseEntity<Questionnaire> getQuestionnaireResults(@PathVariable("id") int id)
    {
        Optional<Questionnaire> questionnaire = questionnaireRepository.findById(id);
        if(questionnaire.isPresent())
        {
            return new ResponseEntity<Questionnaire>(questionnaire.get(), HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
