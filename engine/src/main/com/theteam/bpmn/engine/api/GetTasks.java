package com.theteam.bpmn.engine.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.theteam.bpmn.engine.Workflow;


/**
 * API End point /workflows
 * getWorkflows
 */

@Path("/tasks")
public class GetTasks
{

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMsg()
    {

        JsonArray json = new JsonArray();

        for (JsonObject obj : Workflow.businessTasks)
        {
            json.add(obj);
        }

        return Response.status(200).entity(json.toString()).build();
    }

}

