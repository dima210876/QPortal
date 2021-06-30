package my.project.QPortal.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "options")
public class Option implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "field_id")
    private int field_id;

    @Column(name = "name")
    private String name;

    public int getId() { return id; }

    public int getField_id() { return field_id; }

    public String getName() { return name; }

    public void setField_id(int fieldId) { this.field_id = fieldId; }

    public void setName(String name) { this.name = name; }

    public Option() { }

    public Option(int field_id, String name)
    {
        this.field_id = field_id;
        this.name = name;
    }
}
