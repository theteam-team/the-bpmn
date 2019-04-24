package com.theteam.bpmn.engine.enode;

import com.theteam.bpmn.engine.Elist;
import com.theteam.snodes.SNode;
import com.theteam.snodes.SStartNode;

public class EStart extends ENode
{

    private SStartNode sStart;
    public EStart(SNode sNode, Elist list)
    {
        this.sNode = sNode;
        this.list = list;
    }

    @Override
    public void run(Elist l)
    {
        System.out.println("\nStart Node Running");

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