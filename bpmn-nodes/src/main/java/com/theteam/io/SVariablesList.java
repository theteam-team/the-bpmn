package com.theteam.io;

import java.util.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "variable" })
public class SVariablesList
{
    @XmlElement(name = "variable")
    private List<SVariable> variable;

    HashMap<String, SVariable> mapNodes;

    public SVariablesList()
    {
        variable = new ArrayList<SVariable>();
        mapNodes = new HashMap<>();
    }

    public List<SVariable> getVariablesList()
    {
        return variable;
    }
    
    public void addVariable(SVariable var)
    {
        mapNodes.put(var.getNId(), var);
        variable.add(var);
    }


    public SVariable getVariableById(String id)
    {
        return mapNodes.get(id);
    }

}

