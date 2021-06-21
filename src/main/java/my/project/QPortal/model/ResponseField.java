package my.project.QPortal.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@IdClass(ResponseFieldId.class)
@Table(name = "responses_fields")
public class ResponseField
{
    @Id
    @NotNull
    @NotBlank
    @Column(name = "response_id")
    private int response_id;

    @Id
    @NotNull
    @NotBlank
    @Column(name = "field_id")
    private int field_id;

    @Column(name = "value")
    private String value;

    public int getResponse_id() { return response_id; }

    public void setResponse_id(int response_id) { this.response_id = response_id; }

    public int getField_id() { return field_id; }

    public void setField_id(int field_id) { this.field_id = field_id; }

    public String getValue() { return value; }

    public void setValue(String value) { this.value = value; }

    public ResponseField() { }

    public ResponseField(int response_id, int field_id, String value)
    {
        this.response_id = response_id;
        this.field_id = field_id;
        this.value = value;
    }

    public ResponseField(ResponseFieldId key, String value)
    {
        this.response_id = key.getResponse_id();
        this.field_id = key.getField_id();
        this.value = value;
    }
}
