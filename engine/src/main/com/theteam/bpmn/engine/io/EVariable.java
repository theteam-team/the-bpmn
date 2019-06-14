package com.theteam.bpmn.engine.io;

import com.theteam.io.SVariable;
import com.theteam.snodes.SNode;

public class EVariable
{
    protected SVariable sVariable = null;

    public SVariable getSVariable() { return sVariable; }
    public void setSVariable(SVariable sVariable)
    {
        this.sVariable = sVariable;
    }

    public String getName()
    {
        return sVariable.getName();
    }

    public String getValue()
    {
        return sVariable.getValue();
    }

    public void setValue(String value) 
    {
        sVariable.setValue(value);
    }

}