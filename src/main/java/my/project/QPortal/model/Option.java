package my.project.QPortal.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "options")
public class Option
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @NotBlank
    @Column(name = "field_id")
    private int field_id;

    @NotNull
    @NotBlank
    @Column(name = "name")
    private String name;

    public int getId() { return id; }

    public int getField_id() { return field_id; }

    public String getName() { return name; }

    public void setField_id(int field_id) { this.field_id = field_id; }

    public void setName(String name) { this.name = name; }

    public Option() { }

    public Option(int field_id, String name)
    {
        this.field_id = field_id;
        this.name = name;
    }
}
