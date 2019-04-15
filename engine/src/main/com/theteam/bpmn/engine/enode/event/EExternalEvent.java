package com.theteam.bpmn.engine.enode.event;

import com.theteam.bpmn.engine.Elist;
import com.theteam.bpmn.engine.Workflow;
import com.theteam.bpmn.engine.enode.ENode;
import com.theteam.bpmn.engine.io.EVariable;
import com.theteam.snodes.SNode;
import com.theteam.snodes.event.SExternalEvent;

public class EExternalEvent extends ENode
{

    private SExternalEvent sExternal;

    public EExternalEvent(SNode sNode, Elist list)
    {
        this.sNode = sNode;

        sExternal = (SExternalEvent) this.sNode;

        this.list = list;
    }

    @Override
    public void run()
    {
        System.out.println("External Event Node Running");

        if(sExternal.getInput() != null)
        {
            EVariable i = list.getVariable(sExternal.getInput());
            System.out.println(i.getValue());
        }
    }
}