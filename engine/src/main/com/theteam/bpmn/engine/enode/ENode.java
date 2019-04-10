package com.theteam.bpmn.engine.enode;

import com.theteam.snodes.SNode;

public class ENode
{
    private boolean trigger = false;
    protected SNode sNode = null;

    public SNode getSNode() { return sNode;}
    public void setSNode(SNode sNode)
    {
        this.sNode = sNode;
    }

    public void run()
    {
        
    }
}