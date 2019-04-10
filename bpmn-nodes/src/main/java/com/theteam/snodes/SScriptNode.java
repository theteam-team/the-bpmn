package com.theteam.snodes;


import java.util.UUID;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement
@XmlType(propOrder = { "previousNode", "nextNode", "file"})
public class SScriptNode extends SNode
{

    private String file = null;

    private String previousNode = null;
    private String nextNode = null;


    public SScriptNode()
    {

    }

    public SScriptNode(String type, UUID id)
    {
        this.type = type;
        this.nId = id.toString();
        this.uuid = id;
    }

    public SScriptNode(String type, String id)
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

    @XmlElement(name = "file")
    public String getFile()
    {

        if(file != null)
            return file;
        return null;
    }

    public void setFile(String file) {
        this.file = file;
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

    public void setOutput(String output)
    {
        
    }

    public void setInput(String input)
    {
        
    }


}
