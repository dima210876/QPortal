package my.project.QPortal.controllers;

import my.project.QPortal.model.User;
import my.project.QPortal.model.ValueContainer;
import my.project.QPortal.service.EmailService;
import my.project.QPortal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class UserController
{
    private final UserService userService;
    private final EmailService emailService;

    @Autowired
    public UserController(UserService userService, EmailService emailService)
    {
        this.userService = userService;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String principalName = authentication.getName();
        Optional <User> currentUser = userService.getById(id);
        if(currentUser.isPresent())
        {
            if (!principalName.equals(currentUser.get().getEmail()))
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String principalName = authentication.getName();
        Optional<User> currentUser = userService.getById(id);
        if(currentUser.isPresent())
        {
            if (!principalName.equals(currentUser.get().getEmail()))
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

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

}
