
package com.theteam.snodes;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement
@XmlType(propOrder = { "previousNode", "nextNode", "connectedAction"})
public class SEndNode extends SNode
{

    private String connectedAction = null;

    private String previousNode = null;
    private String nextNode = null;


    public SEndNode()
    {

    }

    public SEndNode(String type,  UUID id)
    {
        this.type = type;
        this.nId = id.toString();

        this.uuid = id;
    }

    public SEndNode(String type, String id)
    {
        this.type = type;
        this.nId = id;
        this.uuid = UUID.fromString(id);
    }

    @XmlAttribute
    public String getNId()
    {
        return nId;  
    }

    public void setNId(String id)
    {
        this.nId = id;
    }

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

    @XmlElement(name = "connectedAction")
    public String getConnectedAction()
    {

        if(connectedAction != null)
            return connectedAction.toString();
        return null;
    }

    public void setConnectedAction(String connectedAction) {
        this.connectedAction = connectedAction;
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

    }

    public void setOutput(String output)
    {
        
    }

    public void setInput(String input)
    {
        
    }

}
