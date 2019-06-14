package com.theteam.bpmn.engine.enode;

import com.google.gson.JsonObject;
import com.theteam.bpmn.engine.Elist;
import com.theteam.bpmn.engine.Workflow;
import com.theteam.bpmn.engine.io.EVariable;
import com.theteam.bpmn.engine.observers.WorkflowObserver;
import com.theteam.io.SVariable;
import com.theteam.snodes.SEndNode;
import com.theteam.snodes.SNode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EEnd extends ENode
{

    private SEndNode sEnd;
    private String name;
    

    public EEnd(SNode sNode, Elist list)
    {
        this.sNode = sNode;
        this.list = list;
        this.name = list.sNodes.getName();
    }

    @Override
    public void run(Elist l)
    {
        System.out.println("\nEnd Node Running");

        JsonObject obj = new JsonObject();

        obj.addProperty("workflowName", l.sNodes.getName());
        obj.addProperty("workflowID", l.getID());
        obj.addProperty("processName", sNode.getType());
        obj.addProperty("processID", sNode.getNId());

        Workflow.wo.updateVal(obj.toString());

        Workflow.runningWorkflows.put(name, Workflow.runningWorkflows.get(name)-1);


        Logger logger = LoggerFactory.getLogger(EEnd.class);
        
        for (EVariable var : l.eVariables.values()) {
            
            String s = var.getName() + " : " + var.getValue();

            logger.info(s);
        }
    }
}