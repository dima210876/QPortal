package my.project.QPortal.model;

import javax.persistence.*;

@Entity
@Table(name = "options")
public class Option
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "field_id", nullable = false)
    private int field_id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    public int getId() { return id; }

    public int getField_id() { return field_id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Option() { }

    public Option(int field_id, String name)
    {
        this.field_id = field_id;
        this.name = name;
    }

    public Option(Option option)
    {
        this.field_id = option.field_id;
        this.name = option.name;
    }
}
