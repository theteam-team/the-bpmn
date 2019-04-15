package com.theteam.bpmn.engine.api;

import com.theteam.bpmn.engine.Elist;
import com.theteam.bpmn.engine.Workflow;
import com.theteam.bpmn.engine.enode.*;
import com.theteam.snodes.event.SExternalEvent;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;


/**
 * API End point /workflow
 * Start caertain workflow with a specific ID
 */
@Path("/workflow")  
public class StartWorkflow
{  
    @GET  
    @Path("/{param}")  
    public Response getMsg(@PathParam("param") String name)
    {
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
            Elist l = Workflow.workflows.get(name);

            ENode node = l.getStartNode();
            Boolean found;

            while(node != null)
            {

                System.out.println();
                
                found = false;

                if(node.getSNode().getType().equals("EXTERNAL_EVENT"))
                {
                    for(ENode n : l.eNodes)
                    {
                    
                        if(n.getSNode().getNId().equals(   ((SExternalEvent)node.getSNode()).getConnectedEvent()  ))
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

            return "Workflow found and played";
        }

        return "Workflow Not Found";

    }

}



