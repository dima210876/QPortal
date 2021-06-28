package my.project.QPortal.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "users")
public class User implements UserDetails, Serializable
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

    @Column(name = "first_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastname;

    @Column(name = "phone")
    private String phone;

    @OneToOne
    @JoinColumn(name = "questionnaire_id", referencedColumnName = "id")
    private Questionnaire questionnaire;

    public int getId() { return id; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority("ROLE_USER");
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() { return password; }

    @Override
    public String getUsername() { return email; }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }

    public void setPassword(String password) { this.password = password; }

    public String getFirstname() { return firstname; }

    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getLastname() { return lastname; }

    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public Questionnaire getQuestionnaire() { return questionnaire; }

    public void setQuestionnaire(Questionnaire questionnaire) { this.questionnaire = questionnaire; }

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
