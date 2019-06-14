package com.theteam.jsondata;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = { "name", "val" })
public class SJsonData
{
    String nId;

    String name;
    String val;

    public SJsonData()
    {

    }

    public SJsonData(String id, String name, String val)
    {
        this.nId = id;

        this.name = name;
        this.val = val;
    }

    public SJsonData(String name, String val)
    {
        this.name = name;
        this.val = val;
    }

    @XmlAttribute
    public String getNId() {return this.nId; }
    public void setNId(String id)
    {
        this.nId = id;
    };

    @XmlElement(name = "name")
    public String getName() { return name; }
    public void setName(String name) { this.name = name;}

    @XmlElement(name = "val")
    public String getVal() { return val; }
    public void setVal(String val) { this.val = val;}

}