package my.project.QPortal.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @Column(name = "password", length = 50, nullable = false)
    private String password;

    @Column(name = "firstname", length = 50)
    private String firstname;

    @Column(name = "lastname", length = 50)
    private String lastname;

    @Column(name = "phone", length = 20)
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
