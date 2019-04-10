package com.theteam;
import java.util.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.theteam.io.SVariablesList;
import com.theteam.snodes.SNodeList;

@XmlRootElement(name = "bpm")
@XmlType(propOrder = { "nodes", "variables" })
public class ElementsList
{
    @XmlElement(name = "nodes")
    private SNodeList nodes;
    
    @XmlElement(name = "variables")
    private SVariablesList variables;
    

    public ElementsList ()
    {
        
    }

    public SNodeList gNodeList() { return nodes; }

    public void setNodeList(SNodeList nodeList)
    {
        this.nodes = nodeList;
    }

    public SVariablesList gVariableList() { return variables; }
    public void setVariableList(SVariablesList variableList)
    {
        this.variables = variableList;
    }

    
}

