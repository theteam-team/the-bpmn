package com.theteam.bpmn.engine.enode;

import com.theteam.snodes.SDBNode;
import com.theteam.snodes.SNode;

public class EDB extends ENode
{

    private SDBNode sDB;

    public EDB(SNode sNode)
    {
        this.sNode = sNode;
    }

    @Override
    public void run()
    {
        System.out.println("DB Node Running");
    }
}