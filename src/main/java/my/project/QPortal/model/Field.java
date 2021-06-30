package my.project.QPortal.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "fields")
public class Field implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(name = "questionnaire_id")
    private int questionnaire_id;

    @NotNull
    @NotBlank
    @Column(name = "label")
    private String label;

    @NotNull
    @NotBlank
    @Column(name = "type")
    private String type;

    @NotNull
    @Column(name = "required")
    private boolean required;

    @NotNull
    @Column(name = "isactive")
    private boolean isactive;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "field_id")
    private List<Option> options;

    public int getId() { return id; }

    public int getQuestionnaire_id() { return questionnaire_id; }

    public void setQuestionnaire_id(int questionnaire_id) { this.questionnaire_id = questionnaire_id; }

    public String getLabel() { return label; }

    public void setLabel(String label) { this.label = label; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public boolean getRequired() { return required; }

    public void setRequired(boolean required) { this.required = required; }

    public boolean getIsactive() { return isactive; }

    public void setIsactive(boolean isactive) { this.isactive = isactive; }

    public List<Option> getOptions() { return options; }

    public void setOptions(List<Option> options) { this.options = options; }

    public Field() { }

    public Field(int id,
                 int questionnaire_id,
                 String label,
                 String type,
                 boolean required,
                 boolean isactive)
    {
        this.id = id;
        this.questionnaire_id = questionnaire_id;
        this.label = label;
        this.type = type;
        this.required = required;
        this.isactive = isactive;
    }

    public Field(Field field)
    {
        this.id = field.id;
        this.questionnaire_id = field.questionnaire_id;
        this.label = field.label;
        this.type = field.type;
        this.required = field.required;
        this.isactive = field.isactive;
    }
}
