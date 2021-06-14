package my.project.QPortal.controllers;

import my.project.QPortal.model.User;
import my.project.QPortal.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController
{
    @Autowired
    UserRepository userRepository;

    @Autowired
    FieldRepository fieldRepository;

    @Autowired
    OptionRepository optionRepository;

    @Autowired
    QuestionnaireRepository questionnaireRepository;

    //private User currentUser;

    @GetMapping("/login")
    public String login(Model model, User user)
    {

        return "login";
    }

    @PostMapping("/registration")
    public String register(Model model, User user)
    {

        return "registration";
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
}
