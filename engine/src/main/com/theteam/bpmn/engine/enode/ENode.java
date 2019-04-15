package com.theteam.bpmn.engine.enode;

import com.theteam.bpmn.engine.Elist;
import com.theteam.bpmn.engine.Workflow;
import com.theteam.snodes.SNode;

public class ENode
{
    protected boolean trigger = false;
    protected SNode sNode = null;

    protected Elist list;

    public SNode getSNode() { return sNode;}
    public void setSNode(SNode sNode)
    {
        this.sNode = sNode;
    }

    public void run()
    {
        
    }
}