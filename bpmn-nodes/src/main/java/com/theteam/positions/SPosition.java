package com.theteam.positions;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = { "nodeId", "x", "y" })
public class SPosition 
{
    String nId;

    String nodeId;
    String x;
    String y;

    public SPosition()
    {

    }

    public SPosition(String id, String nodeId, String x, String y)
    {
        this.nId = id;

        this.nodeId = nodeId;
        this.x = x;
        this.y = y;
    }

    public SPosition(String nodeId, String x, String y)
    {
        this.nodeId = nodeId;
        this.x = x;
        this.y = y;
    }

    /*
    @XmlAttribute
    public String getNId() {return this.nId; }
    public void setNId(String id)
    {
        this.nId = id;
    };
    */

    @XmlElement(name = "nodeId")
    public String getNodeId() { return nodeId; }
    public void setNodeId (String nodeId) { this.nodeId = nodeId; }

    @XmlElement(name = "x")
    public String getX() { return x; }
    public void setX(String x) { this.x = x;}

    @XmlElement(name = "y")
    public String getY() { return y; }
    public void setY(String y) { this.y = y;}

}