package com.theteam.snodes;

import java.io.IOException;
import java.util.UUID;
import java.util.Scanner;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement
@XmlType(propOrder = { "previousNode", "nextNode",})
public class STaskNode extends SNode {

    private String previousNode = null;
    private String nextNode = null;

    public STaskNode() {

    }

    public STaskNode(String type, UUID id) {
        this.type = type;
        this.nId = id.toString();
        this.uuid = id;
    }

    @XmlAttribute
    public String getNId() {
        return nId.toString();  
    }

    public void setNId(String id) {
        this.nId = id;
    }

    public UUID getUUId() {
        return uuid;  
    }

    @XmlAttribute
    public String getType() {
        return type;  
    }  

    public void setType(String type) {  
        this.type = type;  
    }


    @XmlElement(name = "previousNode")
    public String getPreviousNode() {

        if(previousNode != null)
            return previousNode.toString();
        return null;
    }

    public void setPreviousNode(String previousNode) {
        this.previousNode = previousNode;
    }

    @XmlElement(name = "nextNode")
    public String getNextNode() {

        if(nextNode != null)
            return nextNode.toString();
        return null;
    }

    public void setNextNode(String nextNode) {
        this.nextNode = nextNode;
    }


    @Override
    public int run() {
        
        return -1;
    }

}

