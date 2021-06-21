package my.project.QPortal.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @NotBlank
    @Column(name = "email")
    private String email;

    @NotNull
    @NotBlank
    @Column(name = "password")
    private String password;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "phone")
    private String phone;

    public int getId() { return id; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getFirstname() { return firstname; }

    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getLastname() { return lastname; }

    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public User() {}

    public User(String email, String password, String firstname, String lastname, String phone)
    {
        this.email = email;
        this.password = password;
        if (!firstname.isEmpty() && !firstname.isBlank()) this.firstname = firstname;
        if (!lastname.isEmpty() && !lastname.isBlank()) this.lastname = lastname;
        if (!phone.isEmpty() && !phone.isBlank()) this.phone = phone;
    }

    public User(String email, String password)
    {
        this.email = email;
        this.password = password;
    }

    public User(User user)
    {
        this.id = user.id;
        this.email = user.email;
        this.password = user.password;
        this.firstname = user.firstname;
        this.lastname = user.lastname;
        this.phone = user.phone;
    }
}
