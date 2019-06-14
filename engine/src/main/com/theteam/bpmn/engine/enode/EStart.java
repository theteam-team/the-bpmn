package com.theteam.bpmn.engine.enode;

import com.google.gson.JsonObject;
import com.theteam.bpmn.engine.Elist;
import com.theteam.bpmn.engine.Workflow;
import com.theteam.bpmn.engine.observers.WorkflowObserver;
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

        JsonObject obj = new JsonObject();

        obj.addProperty("workflowName", l.sNodes.getName());
        obj.addProperty("processName", sNode.getType());
        obj.addProperty("processID", sNode.getNId());

        Workflow.wo.updateVal(obj.toString());

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