package com.theteam.bpmn.engine.enode;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.theteam.bpmn.engine.Elist;
import com.theteam.bpmn.engine.Workflow;
import com.theteam.snodes.SNode;

public class EEnd extends ENode
{

    //private SEndNode sEnd;
    private String name;
    

    public EEnd(SNode sNode, Elist list)
    {
        this.sNode = sNode;
        this.list = list;
        this.name = list.sNodes.getName();
    }

    @Override
    public void run(Elist l, String id)
    {
        System.out.println("\nEnd Node Running");

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
        Workflow.workflowObserver.updateVal(jsonEle1.toString());

        ArrayList<String> workflowsinstances = Workflow.runningWorkflows.get(name);
        if(workflowsinstances != null)
        {
            workflowsinstances.remove(instanceID);
        }


        /*
        Logger logger = LoggerFactory.getLogger(EEnd.class);
        
        for (EVariable var : l.eVariables.values()) {
            
            String s = var.getName() + " : " + var.getValue();

            logger.info(s);
        }
        */
    }
}