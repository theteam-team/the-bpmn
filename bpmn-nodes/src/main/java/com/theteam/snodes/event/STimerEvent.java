package com.theteam.snodes.event;


import java.util.UUID;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.theteam.snodes.SNode;


@XmlRootElement
@XmlType(propOrder = { "previousNode", "previousNode1", "nextNode", "nextNode1", "time", "input", "output"})
public class STimerEvent extends SNode
{

    
    private String previousNode = null;
    private String nextNode = null;

    private String time = null;

    private String input = null;
    private String output = null;


    public STimerEvent()
    {
        
    }

    public STimerEvent(String type, String id)
    {
        this.type = type;
        this.nId = id;
    }

    @XmlAttribute
    public String getNId() { return this.nId; }
    public void setNId(String id)
    {
        this.nId = id;
    };

    @XmlAttribute
    public String getType()
    {
        return type;  
    }  

    public void setType(String type)
    {  
        this.type = type;  
    }

    @XmlElement(name = "time")
    public String getTime()
    {

        if(time != null)
            return time;
        return null;
    }

    public void setTime(String time) {
        this.time = time;
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

    @XmlElement(name = "previousNode1")
    public String getPreviousNode1()
    {
        return null;
    }

    public void setPreviousNode1(String previousNode) {
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

    @XmlElement(name = "nextNode1")
    public String getNextNode1()
    {
        return null;
    }

    public void setNextNode1(String nextNode)
    {
        this.nextNode = nextNode;
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
