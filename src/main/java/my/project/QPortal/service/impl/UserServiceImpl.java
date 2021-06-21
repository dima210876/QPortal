package my.project.QPortal.service.impl;

import my.project.QPortal.model.User;
import my.project.QPortal.repository.UserRepository;
import my.project.QPortal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService
{
    private UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository)
    {
        this.repository = repository;
    }

    public User save(User user)
    {
        return repository.save(user);
    }

    public Optional <User> getById(int id)
    {
        return repository.findById(id);
    }

    public List<User> getAll()
    {
        return repository.findAll();
    }

    public User update(int id, String email, String firstname, String lastname, String phone)
    {
        Optional<User> user = getById(id);
        if(user.isPresent())
        {
            User currentUser = user.get();
            if (!email.isEmpty() && !email.isBlank()) currentUser.setEmail(email);
            currentUser.setFirstname(firstname);
            currentUser.setLastname(lastname);
            currentUser.setPhone(phone);
            repository.save(currentUser);
            return currentUser;
        }
        return user.get();
    }

    public User changePassword(int id, String newPassword)
    {
        Optional<User> user = getById(id);
        if(user.isPresent())
        {
            User currentUser = user.get();
            currentUser.setPassword(newPassword);
            repository.save(currentUser);
            return currentUser;
        }
        return user.get();
    }

    public void delete(int id)
    {
        Optional<User> user = getById(id);
        if(user.isPresent())
        {
            repository.delete(user.get());
        }
        return;
    }

    public boolean isEmailUnique(String email)
    {
        if (repository.findByEmail(email).isEmpty()) return true;
         else return false;
    }
}

