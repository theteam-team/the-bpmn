package com.theteam.bpmn.engine.enode.event;

import com.theteam.bpmn.engine.enode.ENode;
import com.theteam.snodes.SNode;
import com.theteam.snodes.event.STimerEvent;

public class ETimerEvent extends ENode
{

    private STimerEvent sTimer;

    public ETimerEvent(SNode sNode)
    {
        this.sNode = sNode;
    }

    @Override
    public void run()
    {
        System.out.println("Timer Event Node Running");
    }
}