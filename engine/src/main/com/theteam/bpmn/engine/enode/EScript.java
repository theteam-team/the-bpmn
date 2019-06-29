package com.theteam.bpmn.engine.enode;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.theteam.bpmn.engine.Elist;
import com.theteam.bpmn.engine.Workflow;
import com.theteam.snodes.SNode;
import com.theteam.snodes.SScriptNode;

public class EScript extends ENode
{

    private SScriptNode sScript;

    public EScript(SNode sNode, Elist list)
    {
        this.sNode = sNode;

        this.sScript = (SScriptNode)sNode;

        this.list = list;
    }

    @Override
    public void run(Elist l, String id)
    {
        System.out.println("\nScript Node Running");

        String workflowName = l.sNodes.getName();
        String instanceId = id;

        ArrayList<String> processes = Workflow.processesRun.get(instanceId);

        if(processes == null)
        {
            ArrayList<String> tempList = new ArrayList<>();
            tempList.add(sNode.getNId());
            Workflow.processesRun.put(instanceId, tempList);
        }
        else
        {
            processes.add(instanceId);
        }


        JsonObject jsonEle1 = new JsonObject();

        jsonEle1.addProperty("workflowName", workflowName);
        jsonEle1.addProperty("instanceID", instanceId);

        JsonArray jArray = new JsonArray();

        for (String var : Workflow.processesRun.get(instanceId)) {

            JsonObject jsonEle2 = new JsonObject();
            jsonEle2.addProperty("processID", var);
            jArray.add(jsonEle2);
        }

        jsonEle1.add("processes", jArray);
        Workflow.workflowObserver.updateVal(jsonEle1.toString());


        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");

        String SERVER_UPLOAD_LOCATION_FOLDER = getClass().getResource("/files").getPath().substring(1);

        String filePath = SERVER_UPLOAD_LOCATION_FOLDER + sScript.getFile();

        try
        {
            FileReader fr = new FileReader(filePath);

            engine.eval(fr);
            

        } catch(Exception e)
        { 
            System.out.println(e);

            System.out.println("Script Exception");
            return;
        }


        for(ENode n : l.eNodes)
        {
            if(n.getSNode().getNId().equals(getSNode().getNextNode()))
            {
                n.run(l, instanceId);
                return;
            }
        }
    }
}