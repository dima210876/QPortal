package my.project.QPortal.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "questionnaires")
public class Questionnaire implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "questionnaire_id")
    private List<Field> fields;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "questionnaire_id")
    private List<Response> responses;

    public int getId() { return id; }

    public List<Field> getFields() { return fields; }

    public List<Response> getResponses() { return responses; }

    public void setFields(List<Field> fields) { this.fields = fields; }

    public void setResponses(List<Response> responses) { this.responses = responses; }

    public Questionnaire() { }


}
