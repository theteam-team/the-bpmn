package com.theteam.snodes;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.theteam.taskdata.STaskData;
import com.theteam.taskdata.STaskDataList;


@XmlRootElement
@XmlType(propOrder = { "previousNode", "previousNode1", "nextNode", "nextNode1", "input", "output", "serviceType", "restLink", "soapFunc", "messageOut", "messageIn","taskType", "taskName", "taskDData"})
public class STaskNode extends SNode
{

    private String previousNode = null;
    private String nextNode = null;

    private String messageOut = null;
    private String messageIn = null;

    private String serviceType = null;
    private String restLink = null;
    private String soapFunc = null;

    private String taskType = null;
    private String taskName = null;

    private String input = null;
    private String output = null;

    @XmlElement(name = "taskDData")
    private STaskDataList taskDData;


    

    public STaskNode()
    {
        
    }
    public STaskNode(String type, String id)
    {
        this.type = type;
        this.nId = id;
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

    public STaskDataList getTaskDData() { return taskDData; }
    public void setTaskData(STaskDataList taskDData)
    {
        this.taskDData = taskDData;
    }

    public void addTaskData(STaskData j)
    {
        taskDData.addJsonData(j);
    }

    public void removeTaskData(String taskDataId)
    {
        
    }

    public void resetTaskData()
    {
        taskDData.reset();
    }

    @XmlElement(name = "messageIn")
    public String getMessageIn()
    {

        if(messageIn != null)
            return messageIn;
        return null;
    }

    public void setMessageIn(String messageIn) {
        this.messageIn = messageIn;
    }

    @XmlElement(name = "messageOut")
    public String getMessageOut()
    {

        if(messageOut != null)
            return messageOut;
        return null;
    }

    public void setMessageOut(String messageOut) {
        this.messageOut = messageOut;
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

    public String getPreviousNode1()
    {
        return null;
    }

    @XmlElement(name = "previousNode1")
    public void setPreviousNode1(String previousNode) {
        this.previousNode = previousNode;
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

    @XmlElement(name = "nextNode1")
    public String getNextNode1()
    {
        return null;
    }

    public void setNextNode1(String nextNode)
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

    @XmlElement(name = "taskType")
    public String getTaskType()
    {

        if(taskType != null)
            return taskType;
        return null;
        
    }

    public void setTaskType(String taskType)
    {
        this.taskType = taskType;
    }

    @XmlElement(name = "taskName")
    public String getTaskName()
    {

        if(taskName != null)
            return taskName;
        return null;
        
    }

    public void setTaskName(String taskName)
    {
        this.taskName = taskName;
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
