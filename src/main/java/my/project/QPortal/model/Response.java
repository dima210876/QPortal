package my.project.QPortal.model;

import javax.persistence.*;

@Entity
@Table(name = "responses")
public class Response
{
    @Id
    @Column(name = "questionnaire_id", nullable = false)
    private int questionnaire_id;

    @Id
    @Column(name = "field_id", nullable = false)
    private int field_id;

    @Column(name = "value")
    private String value;

    public int getQuestionnaire_id() { return questionnaire_id; }

    public void setQuestionnaire_id(int questionnaire_id) { this.questionnaire_id = questionnaire_id; }

    public int getField_id() { return field_id; }

    public void setField_id(int field_id) { this.field_id = field_id; }

    public String getValue() { return value; }

    public void setValue(String value) { this.value = value; }

    public Response(int questionnaire_id, int field_id, String value)
    {
        this.questionnaire_id = questionnaire_id;
        this.field_id = field_id;
        this.value = value;
    }

    public Response() { }

    public Response(Response response)
    {
        this.questionnaire_id = response.questionnaire_id;
        this.field_id = response.field_id;
        this.value = response.value;
    }
}
