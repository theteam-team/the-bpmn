package com.theteam.snodes;


import java.util.UUID;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement
@XmlType(propOrder = { "previousNode", "previousNode1", "nextNode", "nextNode1"})
public class SParallel extends SNode
{

    private String previousNode = null;
    private String previousNode1 = null;

    private String nextNode = null;
    private String nextNode1 = null;

    public SParallel()
    {
       
    }

    public SParallel(String type, String id)
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

        if(previousNode1 != null)
            return previousNode1;
        return null;
    }

    public void setPreviousNode1(String previousNode) {
        this.previousNode1 = previousNode;
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

        if(nextNode1 != null)
            return nextNode1;
        return null;
    }

    public void setNextNode1(String nextNode1) {
        this.nextNode1 = nextNode1;
    }

    public void setOutput(String output)
    {
        
    }

    public void setInput(String input)
    {
        
    }


}
