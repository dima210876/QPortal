package my.project.QPortal.controllers;

import my.project.QPortal.model.*;
import my.project.QPortal.service.QuestionnaireService;
import my.project.QPortal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class QuestionnaireController
{
    private final UserService userService;
    private final QuestionnaireService questionnaireService;

    @Autowired
    public QuestionnaireController(UserService userService, QuestionnaireService questionnaireService)
    {
        this.userService = userService;
        this.questionnaireService = questionnaireService;
    }

    @GetMapping("/questionnaire/{id}")
    public ResponseEntity<List<Field>> getQuestionnaireFields(@PathVariable("id") int id)
    {
        Optional<Questionnaire> questionnaire = questionnaireService.getById(id);
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
        Optional<Questionnaire> questionnaire = questionnaireService.getById(id);
        if(questionnaire.isPresent())
        {
            questionnaireService.saveAnswers(id, response);
            return new ResponseEntity<String>("Thank you! Your response was saved.", HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/responses/{id}")
    public ResponseEntity<Questionnaire> getQuestionnaireResults(@PathVariable("id") int id)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String principalName = authentication.getName();
        Optional<User> currentUser = userService.getById(id);
        if (!principalName.equals(currentUser.get().getEmail()))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        Optional<Questionnaire> questionnaire = questionnaireService.getById(id);
        if(questionnaire.isPresent())
        {
            return new ResponseEntity<Questionnaire>(questionnaire.get(), HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
