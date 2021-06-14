package my.project.QPortal.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "questionnaires")
public class Questionnaire
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany
    @JoinColumn(name = "questionnaire_id")
    private List<Response> responses;

    public int getId() { return id; }

    public List<Response> getResponses() { return responses; }

    public Questionnaire() { }
}
