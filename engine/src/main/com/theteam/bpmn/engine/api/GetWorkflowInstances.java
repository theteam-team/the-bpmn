package com.theteam.bpmn.engine.api;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.theteam.bpmn.engine.Workflow;


/**
 * API End point /workflows
 * getWorkflows
 */

@Path("/workflowinstance")
public class GetWorkflowInstances
{

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMsg(@QueryParam("name") String name)
    {

        JsonObject obj = new JsonObject();
        obj.addProperty("name", name);

        ArrayList<String> workflowINstances = Workflow.runningWorkflows.get(name);

            if( workflowINstances == null)
            {
                ArrayList<String> tempList = new ArrayList<>();
                Workflow.runningWorkflows.put(name, tempList);
            }
            
            obj.addProperty("runningInstances", Workflow.runningWorkflows.get(name).size());

            JsonArray jArray = new JsonArray();

            for (String var : Workflow.runningWorkflows.get(name)) {
    
                JsonObject jsonEle2 = new JsonObject();
                jsonEle2.addProperty("instanceID", var);
                jArray.add(jsonEle2);
            }
    
            obj.add("instances", jArray);


        return Response.status(200).entity(obj.toString()).build();
    }


    

}



