package com.theteam.bpmn.engine.enode;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.theteam.bpmn.engine.Elist;
import com.theteam.bpmn.engine.Workflow;
import com.theteam.bpmn.engine.io.EVariable;
import com.theteam.bpmn.engine.scan.Scan;
import com.theteam.jsondata.SJsonData;
import com.theteam.snodes.SJsonNode;
import com.theteam.snodes.SNode;

import org.json.JSONObject;

public class EJson extends ENode
{

    private SJsonNode sJson;

    public EJson(SNode sNode, Elist list)
    {
        this.sNode = sNode;
        sJson = (SJsonNode) sNode;

        this.list = list;
    }

    @Override
    public void run(Elist l, String id)
    {
        System.out.println("\nJson Node Running");

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



        //
        //
        //
        //
        // Start

        //System.out.println("\naa");
        if(sJson.getAction().equals("make"))
        {
            JsonObject ob = new JsonObject();

            for (SJsonData var : sJson.getJsonDData().getData()) {
                ob.addProperty(var.getName(), var.getVal());
            }

            if(sJson.getOutput() != null)
            {
                EVariable o = list.getVariable(sJson.getOutput());
                o.setValue(ob.toString());
            }
        }

        else
        {

            //System.out.println("\naa0023");
            if(sJson.getInput() != null)
            {

                //System.out.println("\naa23");
                EVariable o = list.getVariable(sJson.getInput());

                //System.out.println("\naa34");
                
                Scan s = new Scan(o.getValue(), l);
                String j = s.getFinalString();
                
                //System.out.println("\naa45");
                //System.out.println("\n JJJJSONS");
                //System.out.println(o.getValue());
                //System.out.println(j);
                
                //System.out.println("\naa4563");
                JSONObject orgJson = new JSONObject(j);
                
                //System.out.println("\naa4563aa");
                for (SJsonData var : sJson.getJsonDData().getData())
                {
                    
                    //System.out.println("\naa4563bb");
                    String os = orgJson.getString(var.getName());

                    System.out.println(os);
                    //System.out.println(os);
                    
                    EVariable eo = list.getVariable(var.getVal());
                    if(eo != null)
                    eo.setValue(os);
                    
                    
                    //System.out.println("\naa289");
                }
                //System.out.println("\naa147");
            }

            //System.out.println("\n1230asd23");

        }

        //System.out.println("\naa2");

        for(ENode n : l.eNodes)
        {
            if(n.getSNode().getNId().equals(getSNode().getNextNode()))
            {
                //System.out.println("\naa3");
                n.run(l, instanceID);
                return;
            }
        }

    }
}