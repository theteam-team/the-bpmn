package com.theteam.snodes;

import java.util.UUID;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement
@XmlType(propOrder = { "previousNode", "nextNode", "input", "output", "serviceType", "restLink", "soapFunc"})
public class STaskNode extends SNode
{

    private String previousNode = null;
    private String nextNode = null;

    private String serviceType = null;
    private String restLink = null;
    private String soapFunc = null;

    private String input = null;
    private String output = null;

    public STaskNode()
    {

    }

    public STaskNode(String type, UUID id)
    {
        this.type = type;
        this.nId = id.toString();
        this.uuid = id;
    }

    public STaskNode(String type, String id)
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


    @XmlElement(name = "previousNode")
    public String getPreviousNode()
    {

        if(previousNode != null)
            return previousNode;
        return null;
        
    }

    public void setPreviousNode(String previousNode)
    {
        this.previousNode = previousNode;
    }

    @XmlElement(name = "nextNode")
    public String getNextNode()
    {

        if(nextNode != null)
            return nextNode;
        return null;
    }

    public void setNextNode(String nextNode)
    {
        this.nextNode = nextNode;
    }


    @XmlElement(name = "ServiceType")
    public String getServiceType()
    {

        if(serviceType != null)
            return serviceType;
        return null;
        
    }

    public void setServiceType(String serviceType)
    {
        this.serviceType = serviceType;
    }


    @XmlElement(name = "restLink")
    public String getRestLink()
    {

        if(restLink != null)
            return restLink;
        return null;
        
    }

    public void setRestLink(String restLink)
    {
        this.restLink = restLink;
    }


    @XmlElement(name = "soapFunc")
    public String getSoapFunc()
    {

        if(soapFunc != null)
            return soapFunc;
        return null;
        
    }

    public void setSoapFunc(String soapFunc)
    {
        this.soapFunc = soapFunc;
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
