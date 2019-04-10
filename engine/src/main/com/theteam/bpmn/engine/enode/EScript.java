package com.theteam.bpmn.engine.enode;

import com.theteam.snodes.SNode;
import com.theteam.snodes.SScriptNode;

public class EScript extends ENode
{

    private SScriptNode sDB;

    public EScript(SNode sNode)
    {
        this.sNode = sNode;
    }

    @Override
    public void run()
    {
        System.out.println("Script Node Running");
    }
}