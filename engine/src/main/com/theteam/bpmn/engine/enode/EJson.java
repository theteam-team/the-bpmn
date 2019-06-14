package com.theteam.bpmn.engine.enode;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonGenerator;
import com.google.api.client.json.JsonParser;
import com.google.api.client.json.webtoken.JsonWebSignature.Parser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.theteam.bpmn.engine.Elist;
import com.theteam.bpmn.engine.Workflow;
import com.theteam.bpmn.engine.io.EVariable;
import com.theteam.bpmn.engine.observers.WorkflowObserver;
import com.theteam.bpmn.engine.scan.Scan;
import com.theteam.jsondata.SJsonData;
import com.theteam.snodes.SJsonNode;
import com.theteam.snodes.SNode;
import com.theteam.snodes.SStartNode;

import org.json.JSONArray;
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
    public void run(Elist l)
    {
        System.out.println("\nJson Node Running");

        JsonObject obj = new JsonObject();

        obj.addProperty("workflowName", l.sNodes.getName());
        obj.addProperty("workflowID", l.getID());
        obj.addProperty("processName", sNode.getType());
        obj.addProperty("processID", sNode.getNId());

        Workflow.wo.updateVal(obj.toString());

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

            if(sJson.getInput() != null)
            {
                EVariable o = list.getVariable(sJson.getInput());

                Scan s = new Scan(o.getValue(), l);
                String j = s.getFinalString();

                //System.out.println("\n JJJJSONS");
                //System.out.println(o.getValue());
                //System.out.println(j);

                JSONObject orgJson = new JSONObject(j);

                for (SJsonData var : sJson.getJsonDData().getData())
                {

                    String os = orgJson.getString(var.getName());
                    //System.out.println("os");
                    //System.out.println(os);

                    EVariable eo = list.getVariable(var.getVal());
                    if(eo != null)
                        eo.setValue(os);

                    
                }
            }

        }

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