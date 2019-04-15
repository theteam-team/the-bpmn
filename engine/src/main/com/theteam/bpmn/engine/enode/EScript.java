package com.theteam.bpmn.engine.enode;

import com.theteam.bpmn.engine.Elist;
import com.theteam.bpmn.engine.Workflow;
import com.theteam.snodes.SNode;
import com.theteam.snodes.SScriptNode;

public class EScript extends ENode
{

    private SScriptNode sDB;

    public EScript(SNode sNode, Elist list)
    {
        this.sNode = sNode;
        this.list = list;
    }

    @Override
    public void run()
    {
        System.out.println("Script Node Running");
    }
}