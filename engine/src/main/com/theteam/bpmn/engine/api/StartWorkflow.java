package com.theteam.bpmn.engine.api;

import com.theteam.bpmn.engine.Elist;
import com.theteam.bpmn.engine.Workflow;
import com.theteam.bpmn.engine.enode.*;
import com.theteam.bpmn.engine.io.EVariable;
import com.theteam.snodes.event.SExternalEvent;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;




/**
 * API End point /workflow
 * Start caertain workflow with a specific ID
 */
@Path("/workflow")  
public class StartWorkflow
{  
    //@GET  
    //@Path("/{param}")
    //@PathParam("param") String name
    @POST
    public Response getMsg( @QueryParam("name") String name, @Context HttpServletRequest httpRequest)
    {


        try {
            String json = IOUtils.toString(httpRequest.getInputStream(), StandardCharsets.UTF_8);
            //System.out.println("\n Json Start");
            //System.out.println(json);

            EVariable o = Workflow.getWorkflow(name).getVariable("sVar");
            if(o != null)
                o.setValue(json);

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        Thread th = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                runWorkflow(name);
            }
        });

        th.start();

        return Response.status(200).entity("Workflow Running").build();
    }


    public String runWorkflow(String name)
    {
        if(Workflow.workflows.containsKey(name))
        {

            if(Workflow.runningWorkflows.get(name) == null)
                Workflow.runningWorkflows.put(name, 1);
            else
                Workflow.runningWorkflows.put(name, Workflow.runningWorkflows.get(name)+1);


            Elist l = Workflow.workflows.get(name);

            ENode node = l.getStartNode();

            node.run(l);

            /*
            while(node != null)
            {

                System.out.println();
                
                found = false;

                if(node.getSNode().getType().equals("EXTERNAL_EVENT"))
                {
                    for(ENode n : l.eNodes)
                    {
                    
                        if(n.getSNode().getNId().equals(  ((SExternalEvent)node.getSNode()).getConnectedEvent()  ))
                        {
                            n.run();
                        }

                    }
                }
                else
                {
                    node.run();
                }

                for(ENode n : l.eNodes)
                {
                    
                    if(n.getSNode().getNId().equals(node.getSNode().getNextNode()))
                    {
                        found = true;
                        node = n;
                        break;
                    }

                }

                if(!found)
                    node = null;
                
            }
            */

            return "Workflow found and played";
        }

        return "Workflow Not Found";

    }

}



