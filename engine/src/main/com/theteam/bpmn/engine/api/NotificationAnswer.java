package com.theteam.bpmn.engine.api;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.theteam.bpmn.engine.Workflow;

import org.apache.commons.io.IOUtils;

/**
 * API End point /workflows
 * Get Notification Answer from User
 */

@Path("/notification")
public class NotificationAnswer
{

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMsg(@Context HttpServletRequest httpRequest)
    {

        try
        {
            String json = IOUtils.toString(httpRequest.getInputStream(), StandardCharsets.UTF_8);
            Workflow.serverObserver.updateVal(json);

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        return Response.status(200).entity("notification received").build();
    }


}

