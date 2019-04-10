package com.theteam.bpmn.engine.enode;

import com.theteam.snodes.SNode;
import com.theteam.snodes.SStartNode;

public class EStart extends ENode
{

    private SStartNode sStart;
    public EStart(SNode sNode)
    {
        this.sNode = sNode;
    }

    @Override
    public void run()
    {
        System.out.println("Start Node Running");
    }
}