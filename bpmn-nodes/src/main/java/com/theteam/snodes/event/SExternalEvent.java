package com.theteam.snodes.event;


import java.util.UUID;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.theteam.snodes.SNode;


@XmlRootElement
@XmlType(propOrder = { "previousNode", "nextNode", "connectedEvent", "input", "output"})
public class SExternalEvent extends SNode
{

    private String connectedEvent = null;

    private String previousNode = null;
    private String nextNode = null;

    private String input = null;
    private String output = null;


    public SExternalEvent()
    {

    }

    public SExternalEvent(String type, UUID id)
    {
        this.type = type;
        this.nId = id.toString();
        this.uuid = id;
    }

    public SExternalEvent(String type, String id)
    {
        this.type = type;
        this.nId = id;
        uuid = UUID.fromString(id);
    }

    @XmlAttribute
    public String getNId() { return this.nId; }
    public void setNId(String id)
    {
        this.nId = id;
    };

    public UUID getUUId()
    {
        return uuid;  
    }

    @XmlAttribute
    public String getType()
    {
        return type;  
    }  

    public void setType(String type)
    {  
        this.type = type;  
    }

    @XmlElement(name = "previousNode")
    public String getPreviousNode()
    {

        if(previousNode != null)
            return previousNode;
        return null;
    }

    public void setPreviousNode(String previousNode) {
        this.previousNode = previousNode;
    }

    @XmlElement(name = "nextNode")
    public String getNextNode()
    {

        if(nextNode != null)
            return nextNode;
        return null;
    }

    public void setNextNode(String nextNode) {
        this.nextNode = nextNode;
    }

    @XmlElement(name = "connectedEvent")
    public String getConnectedEvent()
    {

        if(connectedEvent != null)
            return connectedEvent.toString();
        return null;
    }

    public void setConnectedEvent(String connectedEvent) {
        this.connectedEvent = connectedEvent;
    }

    @XmlElement(name = "input")
    public String getInput()
    {

        if(input != null)
            return input;
        return null;
        
    }

    public void setInput(String input)
    {
        this.input = input;
    }

    @XmlElement(name = "output")
    public String getOutput()
    {

        if(output != null)
            return output;
        return null;
        
    }

    public void setOutput(String output)
    {
        this.output = output;
    }

}
