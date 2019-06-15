package com.theteam.bpmn.engine.enode.event;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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
    public void run(Elist l, String id)
    {
        System.out.println("\nExternal Event Node Running");

        String workflowName = l.sNodes.getName();
        String instanceID = id;

        ArrayList<String> processes = Workflow.processesRun.get(instanceID);

        if(processes == null)
        {
            ArrayList<String> tempList = new ArrayList<>();
            tempList.add(sNode.getNId());
            Workflow.processesRun.put(instanceID, tempList);
        }
        else
        {
            processes.add(sNode.getNId());
        }


        JsonObject jsonEle1 = new JsonObject();

        jsonEle1.addProperty("workflowName", workflowName);
        jsonEle1.addProperty("instanceID", instanceID);

        JsonArray jArray = new JsonArray();

        for (String var : Workflow.processesRun.get(instanceID)) {

            JsonObject jsonEle2 = new JsonObject();
            jsonEle2.addProperty("processID", var);
            jArray.add(jsonEle2);
        }

        jsonEle1.add("processes", jArray);
        Workflow.wo.updateVal(jsonEle1.toString());

        if(sExternal.getInput() != null)
        {
            EVariable i = list.getVariable(sExternal.getInput());
            System.out.println(i.getValue());
        }

        for(ENode n : l.eNodes)
        {
        
            if(n.getSNode().getNId().equals(  ((SExternalEvent)getSNode()).getConnectedEvent()  ))
            {
                n.run(l, instanceID);
            }
        }

        /*
        for(ENode n : l.eNodes)
        {
            if(n.getSNode().getNId().equals(getSNode().getNextNode()))
            {
                n.run(l);
                return;
            }
        }
        */

    }
}