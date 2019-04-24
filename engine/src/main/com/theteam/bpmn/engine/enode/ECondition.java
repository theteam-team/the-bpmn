package com.theteam.bpmn.engine.enode;

import com.theteam.bpmn.engine.Elist;
import com.theteam.bpmn.engine.Workflow;
import com.theteam.snodes.SCondition;
import com.theteam.snodes.SNode;

public class ECondition extends ENode
{

    private SCondition sCondition;
    

    public ECondition(SNode sNode, Elist list)
    {
        this.sNode = sNode;
        this.list = list;

        sCondition = (SCondition) this.sNode;
    }

    @Override
    public void run(Elist l)
    {
        System.out.println("\nCondition Node Running");

        if(sCondition.getExpression().equals("true"))
        {
            for(ENode n : l.eNodes)
            {
                if(n.getSNode().getNId().equals(getSNode().getNextNode()))
                {
                    n.run(l);
                    return;
                }
            }
        }
        else
        {
            for(ENode n : l.eNodes)
            {
                if(n.getSNode().getNId().equals(getSNode().getNextNode1()))
                {
                    n.run(l);
                    return;
                }
            }
        }
    }
}