package my.project.QPortal.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "responses")
public class Response
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @NotBlank
    @Column(name = "questionnaire_id")
    private int questionnaire_id;

    @OneToMany
    @JoinColumn(name = "response_id")
    private List<ResponseField> responseFields;

    public int getQuestionnaire_id() { return questionnaire_id; }

    public void setQuestionnaire_id(int questionnaire_id) { this.questionnaire_id = questionnaire_id; }

    public List<ResponseField> getResponseFields() { return responseFields; }

    public void setResponseFields(List<ResponseField> responseFields) { this.responseFields = responseFields; }

    public Response(int questionnaire_id)
    {
        this.questionnaire_id = questionnaire_id;
    }

    public Response() { }
}
