package com.theteam.bpmn.engine.enode.event;

import com.theteam.bpmn.engine.enode.ENode;
import com.theteam.snodes.SNode;
import com.theteam.snodes.event.SExternalEvent;

public class EExternalEvent extends ENode
{

    private SExternalEvent sExternal;

    public EExternalEvent(SNode sNode)
    {
        this.sNode = sNode;
    }

    @Override
    public void run()
    {
        System.out.println("External Event Node Running");
    }
}