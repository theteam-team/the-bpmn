package com.theteam.snodes;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement
@XmlType(propOrder = { "previousNode", "previousNode1", "nextNode", "nextNode1", "action"})
public class SStartNode extends SNode
{

    private String action = null;

    private String previousNode = null;
    private String nextNode = null;

    public SStartNode()
    {
        
    }

    public SStartNode(String type, String id)
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

    @XmlElement(name = "action")
    public String getAction()
    {

        if(action != null)
            return action.toString();
        return null;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @XmlElement(name = "previousNode")
    public String getPreviousNode()
    {

        if(previousNode != null)
            return previousNode;
        return null;
        
    }

    public void setPreviousNode(String previousNode) {
    
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


    public void setOutput(String output)
    {
        
    }

    public void setInput(String input)
    {
        
    }

    public SNode gOnLoadedNode()
    {
        if(getAction().equals("OnLoaded"))
        {
            return this;
        }

        return null;
    }
    
    public SNode gOnAwakedNode()
    {
        if(getAction().equals("OnAwaked"))
        {
            return this;
        }

        return null;
    }

    public SNode gOnStartedNode()
    {
        if(getAction().equals("OnStarted"))
        {
            return this;
        }

        return null;
    }


}
