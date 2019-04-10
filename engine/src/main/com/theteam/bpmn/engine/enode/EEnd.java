package com.theteam.bpmn.engine.enode;

import com.theteam.snodes.SEndNode;
import com.theteam.snodes.SNode;

public class EEnd extends ENode
{

    private SEndNode sEnd;

    public EEnd(SNode sNode)
    {
        this.sNode = sNode;
    }

    @Override
    public void run()
    {
        System.out.println("End Node Running");
    }
}