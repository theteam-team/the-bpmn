package com.theteam.io;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = { "name", "type", "value" })
public class SVariable 
{
    String nId;

    String name;
    String type;
    String value;

    public SVariable()
    {

    }

    public SVariable(String id, String name, String type, String value)
    {
        this.nId = id;
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public SVariable(String name, String type, String value)
    {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    @XmlAttribute
    public String getNId() {return this.nId; }
    public void setNId(String id)
    {
        this.nId = id;
    };

    @XmlElement(name = "value")
    public String getValue() { return value; }
    public void setValue (String value)
    {
        this.value = value;
        
    }

    @XmlElement(name = "name")
    public String getName() { return name; }
    public void setName(String name) { this.name = name;}

    @XmlElement(name = "type")
    public String getType() { return type; }
    public void setType(String type) { this.type = type;}

}