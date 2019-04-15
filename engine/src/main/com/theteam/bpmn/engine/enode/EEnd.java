package com.theteam.bpmn.engine.enode;

import com.theteam.bpmn.engine.Elist;
import com.theteam.bpmn.engine.Workflow;
import com.theteam.snodes.SEndNode;
import com.theteam.snodes.SNode;

public class EEnd extends ENode
{

    private SEndNode sEnd;
    

    public EEnd(SNode sNode, Elist list)
    {
        this.sNode = sNode;

        this.list = list;
    }

    @Override
    public void run()
    {
        System.out.println("End Node Running");
    }
}