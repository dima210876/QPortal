package my.project.QPortal.controllers;

import my.project.QPortal.model.Response;
import my.project.QPortal.model.User;
import my.project.QPortal.repository.*;
import my.project.QPortal.service.EmailService;
import my.project.QPortal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class MainController
{
    private UserService userService;
    private FieldRepository fieldRepository;
    private OptionRepository optionRepository;
    private QuestionnaireRepository questionnaireRepository;
    private final EmailService emailService;

    @Autowired
    public MainController(UserService userService,
                          FieldRepository fieldRepository,
                          OptionRepository optionRepository,
                          QuestionnaireRepository questionnaireRepository,
                          EmailService emailService)
    {
        this.userService = userService;
        this.fieldRepository = fieldRepository;
        this.optionRepository = optionRepository;
        this.questionnaireRepository = questionnaireRepository;
        this.emailService = emailService;
    }
    /*

    @PostMapping("/registration")
    void saveUser(@RequestBody User user)
    {
        if (userService.isEmailUnique(user.getEmail())) userService.save(user);
    }

    @RequestMapping(value = "//editProfile/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getProfileFields(@PathVariable("id") int id)
    {

    }

    @RequestMapping(value = "//editProfile/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Response>> editProfile(@PathVariable("id") int id, String email, String firstname,
                                                      String lastname, String phone)
    {

    }












    @PostMapping("/editProfile/{id}")
    public String editProfile(Model model, User user, @PathVariable("id") int id)
    {

        return "editProfile";
    }

    @PostMapping("/changePassword/{id})")
    public String changePassword(Model model, User user,  @PathVariable("id") int id)
    {

        return "changePassword";
    }

    @GetMapping("/fields")
    public String getFields(Model model)
    {

        return "fields";
    }

    @GetMapping("/fields")
    public String showFieldsPage(Model model, @RequestParam(defaultValue = "0") int page)
    {
        model.addAttribute("fieldsList",
                FieldRepository.findAll(PageRequest.of(page, 5)));
        model.addAttribute("currentPage", page);
        return "fields";
    }

    @PostMapping("/questionnaire")
    public String saveQuestionnaire(Model model)
    {

        return "questionnaire";
    }

    @GetMapping("/success")
    public String showResult(Model model)
    {

        return "success";
    }

    @GetMapping("/responses")
    public String showResponsesPage(Model model, @RequestParam(defaultValue = "0") int page)
    {
        model.addAttribute("responsesList",
                ResponseRepository.findAll(PageRequest.of(page, 5)));
        model.addAttribute("currentPage", page);
        return "responses";
    }
    */
}
