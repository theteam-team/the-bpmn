package com.theteam;
import java.util.*;

import java.io.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.theteam.io.SVariablesList;
import com.theteam.positions.SPositionList;
import com.theteam.snodes.SNodeList;

@XmlRootElement(name = "bpm")
@XmlType(propOrder = { "nodes", "variables", "positions" })
public class ElementsList
{
    @XmlElement(name = "nodes")
    private SNodeList nodes;
    
    @XmlElement(name = "variables")
    private SVariablesList variables;

    @XmlElement(name = "positions")
    private SPositionList positions;

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

    public SPositionList gPositionsList() { return positions; }
    public void setPositionsList(SPositionList positions)
    {
        this.positions = positions;
    }

}

