package com.theteam.bpmn.engine.enode.event;

import com.theteam.bpmn.engine.Elist;
import com.theteam.bpmn.engine.Workflow;
import com.theteam.bpmn.engine.enode.ENode;
import com.theteam.snodes.SNode;
import com.theteam.snodes.event.STimerEvent;

public class ETimerEvent extends ENode
{

    private STimerEvent sTimer;


    public ETimerEvent(SNode sNode, Elist list)
    {
        this.sNode = sNode;

        this.list = list;
    }

    @Override
    public void run(Elist l)
    {
        System.out.println("\nTimer Event Node Running");

        for(ENode n : l.eNodes)
        {
            if(n.getSNode().getNId().equals(getSNode().getNextNode()))
            {
                n.run(l);
                return;
            }
        }
    }
}