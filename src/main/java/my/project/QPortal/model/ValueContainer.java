package my.project.QPortal.model;

import java.io.Serializable;

public class ValueContainer implements Serializable
{
    private int intValue;
    private String stringValue;

    public int getIntValue() { return intValue; }

    public void setIntValue(int intValue) { this.intValue = intValue; }

    public String getStringValue() { return stringValue; }

    public void setStringValue(String stringValue) { this.stringValue = stringValue; }

    public ValueContainer() {}
}
