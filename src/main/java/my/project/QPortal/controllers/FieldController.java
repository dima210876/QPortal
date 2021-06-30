package my.project.QPortal.controllers;

import my.project.QPortal.model.Field;
import my.project.QPortal.model.User;
import my.project.QPortal.model.ValueContainer;
import my.project.QPortal.service.FieldService;
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
public class FieldController
{
    private final UserService userService;
    private final FieldService fieldService;

    @Autowired
    public FieldController(UserService userService,
                           FieldService fieldService)
    {
        this.userService = userService;
        this.fieldService = fieldService;
    }

    @GetMapping("/fields/{id}")
    public ResponseEntity<List<Field>> getFields(@PathVariable("id") int id)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String principalName = authentication.getName();
        Optional<User> currentUser = userService.getById(id);
        if(currentUser.isPresent())
        {
            if (!principalName.equals(currentUser.get().getEmail()))
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            List<Field> fieldList = currentUser.get().getQuestionnaire().getFields();
            return new ResponseEntity<List<Field>>(fieldList, HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/fields/{id}")
    public ResponseEntity<List<Field>> addField(@PathVariable("id") int id, @RequestBody Field newField)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String principalName = authentication.getName();
        Optional<User> currentUser = userService.getById(id);
        if(currentUser.isPresent())
        {
            if (!principalName.equals(currentUser.get().getEmail()))
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            int quest_id = currentUser.get().getQuestionnaire().getId();
            if (newField.getQuestionnaire_id() == quest_id)
            {
                fieldService.addField(newField);
                List<Field> fieldList = fieldService.getAllByQuestionnaireId(quest_id);
                return new ResponseEntity<List<Field>>(fieldList, HttpStatus.OK);
            }
            else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/fields/{id}")
    public ResponseEntity<List<Field>> editField(@PathVariable("id") int id, @RequestBody Field updatedField)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String principalName = authentication.getName();
        Optional<User> currentUser = userService.getById(id);
        if(currentUser.isPresent())
        {
            if (!principalName.equals(currentUser.get().getEmail()))
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            Optional<Field> currentField = fieldService.getById(updatedField.getId());
            if(currentField.isPresent())
            {
                fieldService.editField(updatedField.getId(), updatedField);
                List<Field> fieldList = fieldService.getAllByQuestionnaireId(updatedField.getQuestionnaire_id());
                return new ResponseEntity<List<Field>>(fieldList, HttpStatus.OK);
            }
            else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/fields/{id}")
    public ResponseEntity<List<Field>> deleteField(@PathVariable("id") int id, @RequestBody ValueContainer fieldId)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String principalName = authentication.getName();
        Optional<User> currentUser = userService.getById(id);
        if(currentUser.isPresent())
        {
            if (!principalName.equals(currentUser.get().getEmail()))
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            Optional<Field> currentField = fieldService.getById(fieldId.getIntValue());
            if(currentField.isPresent() &&
                    currentUser.get().getQuestionnaire().getFields().contains(currentField.get()))
            {
                int quest_id = currentField.get().getQuestionnaire_id();
                fieldService.delete(fieldId.getIntValue());
                List<Field> fieldList = fieldService.getAllByQuestionnaireId(quest_id);
                return new ResponseEntity<List<Field>>(fieldList, HttpStatus.OK);
            }
            else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
