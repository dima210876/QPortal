package my.project.QPortal.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "fields")
public class Field
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "label", length = 50, nullable = false)
    private String label;

    @Column(name = "type", length = 50, nullable = false)
    private String type;

    @Column(name = "required", nullable = false)
    private boolean required;

    @Column(name = "isActive", nullable = false)
    private boolean isActive;

    @OneToMany
    @JoinColumn(name = "field_id")
    private List<Option> options;

    public int getId() { return id; }

    public String getLabel() { return label; }

    public void setLabel(String label) { this.label = label; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public boolean isRequired() { return required; }

    public void setRequired(boolean required) { this.required = required; }

    public boolean isActive() { return isActive; }

    public void setActive(boolean active) { isActive = active; }

    public List<Option> getOptions() { return options; }

    public Field() { }

    public Field(String label, String type, boolean required, boolean isActive)
    {
        if (!label.isEmpty() && !label.isBlank()) this.label = label;
        if (!type.isEmpty() && !type.isBlank()) this.type = type;
        this.required = required;
        this.isActive = isActive;
    }

    public Field(Field field)
    {
        this.id = field.id;
        this.label = field.label;
        this.type = field.type;
        this.required = field.required;
        this.isActive = field.isActive;
    }
}
