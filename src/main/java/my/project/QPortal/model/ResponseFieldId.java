package my.project.QPortal.model;
import java.io.Serializable;
import java.util.Objects;

public class ResponseFieldId implements Serializable
{
    private int response_id;

    private int field_id;

    public int getResponse_id() { return response_id; }

    public void setResponse_id(int response_id) { this.response_id = response_id; }

    public int getField_id() { return field_id; }

    public void setField_id(int field_id) { this.field_id = field_id; }

    public ResponseFieldId() { }

    public ResponseFieldId(int response_id, int field_id)
    {
        this.response_id = response_id;
        this.field_id = field_id;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseFieldId that = (ResponseFieldId) o;
        return response_id == that.response_id && field_id == that.field_id;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(response_id, field_id);
    }
}