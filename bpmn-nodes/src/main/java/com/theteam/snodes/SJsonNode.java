package com.theteam.snodes;


import java.util.UUID;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.theteam.jsondata.SJsonData;
import com.theteam.jsondata.SJsonDataList;


@XmlRootElement
@XmlType(propOrder = { "previousNode", "previousNode1", "nextNode", "nextNode1", "input", "output", "action", "jsonDData"})
public class SJsonNode extends SNode
{

    private String action = null;

    private String input = null;
    private String output = null;

    private String previousNode = null;
    private String nextNode = null;

    @XmlElement(name = "jsonDData")
    private SJsonDataList jsonDData;

    public SJsonNode()
    {
        
    }

    public SJsonNode(String type, String id)
    {
        this.type = type;
        this.nId = id;
    }

    public SJsonDataList getJsonDData() { return jsonDData; }
    public void setJsonData(SJsonDataList jsonDData)
    {
        this.jsonDData = jsonDData;
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

    public void addJsonData(SJsonData j)
    {
        jsonDData.addJsonData(j);
    }

    public void removeJsonData(String jsonDataId)
    {
        
    }

    public void resetJsonData()
    {
        jsonDData.reset();
    }


}
