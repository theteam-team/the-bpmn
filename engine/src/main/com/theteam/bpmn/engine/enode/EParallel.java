package com.theteam.bpmn.engine.enode;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.theteam.bpmn.engine.Elist;
import com.theteam.bpmn.engine.Workflow;
import com.theteam.snodes.SNode;

public class EParallel extends ENode
{

    //private SParallel sParallel;
    

    public EParallel(SNode sNode, Elist list)
    {
        this.sNode = sNode;
        this.list = list;
    }

    @Override
    public void run(Elist l, String id)
    {
        System.out.println("\nParallel Node Running");

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

        for(ENode n : l.eNodes)
        {
            if(n.getSNode().getNId().equals(getSNode().getNextNode()))
            {
                Thread th = new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        n.run(l, instanceID);
                    }
                });

                th.start();
                
            }

            if(n.getSNode().getNId().equals(getSNode().getNextNode1()))
            {
                Thread th = new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        n.run(l, instanceID);
                    }
                });

                th.start();
            }
        }
    }
}