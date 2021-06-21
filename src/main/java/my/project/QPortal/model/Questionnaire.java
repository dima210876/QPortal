package my.project.QPortal.model;

import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "questionnaires")
public class Questionnaire
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    @NotNull
    @NotBlank
    private int user_id;

    @OneToMany
    @JoinColumn(name = "questionnaire_id")
    private List<Field> fields;

    @OneToMany
    @JoinColumn(name = "questionnaire_id")
    private List<Response> responses;

    public int getId() { return id; }

    public int getUserId() { return user_id; }

    public List<Field> getFields() { return fields; }

    public List<Response> getResponses() { return responses; }

    public void setUser_id(int user_id) { this.user_id = user_id; }

    public void setFields(List<Field> fields) { this.fields = fields; }

    public void setResponses(List<Response> responses) { this.responses = responses; }

    public Questionnaire() { }

    public Questionnaire(int user_id) { this.user_id = user_id; }
}
