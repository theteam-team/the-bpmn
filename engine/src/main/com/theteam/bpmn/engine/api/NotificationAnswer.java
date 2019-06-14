package com.theteam.bpmn.engine.api;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.theteam.bpmn.engine.Elist;
import com.theteam.bpmn.engine.Workflow;
import com.theteam.bpmn.engine.enode.*;
import com.theteam.bpmn.engine.io.EVariable;
import com.theteam.snodes.event.SExternalEvent;


import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;




/**
 * API End point /workflows
 * Get Notification Answer from User
 */

@Path("/notification")  
public class NotificationAnswer
{

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMsg()
    {

        return Response.status(200).entity("notification").build();
    }


    

}



