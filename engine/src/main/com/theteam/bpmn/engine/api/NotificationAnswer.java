package com.theteam.bpmn.engine.api;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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

