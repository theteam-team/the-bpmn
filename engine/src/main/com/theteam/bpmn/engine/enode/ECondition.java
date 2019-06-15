package com.theteam.bpmn.engine.enode;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.theteam.bpmn.engine.Elist;
import com.theteam.bpmn.engine.Workflow;
import com.theteam.bpmn.engine.scan.Scan;
import com.theteam.snodes.SCondition;
import com.theteam.snodes.SNode;

import java.util.ArrayList;

import javax.script.*;

public class ECondition extends ENode
{

    private SCondition sCondition;
    

    public ECondition(SNode sNode, Elist list)
    {
        this.sNode = sNode;
        this.list = list;

        sCondition = (SCondition) this.sNode;
    }

    @Override
    public void run(Elist l, String id)
    {
        System.out.println("\nCondition Node Running");

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

        Scan s = new Scan(sCondition.getExpression(), l);
        String condition = s.getFinalString();

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");
        
        boolean conEvaluated;

        try
        {
            conEvaluated = (boolean) engine.eval(condition);

        } catch(Exception e)
        { 
            System.out.println(e);

            System.out.println("Condition wrong");
            return;
        }


        if(conEvaluated)
        {
            for(ENode n : l.eNodes)
            {
                if(n.getSNode().getNId().equals(getSNode().getNextNode()))
                {
                    n.run(l, instanceID);
                    return;
                }
            }
        }
        else
        {
            for(ENode n : l.eNodes)
            {
                if(n.getSNode().getNId().equals(getSNode().getNextNode1()))
                {
                    n.run(l, instanceID);
                    return;
                }
            }
        }
    }
}