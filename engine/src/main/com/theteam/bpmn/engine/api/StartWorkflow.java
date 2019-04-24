package com.theteam.bpmn.engine.api;

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
import javax.ws.rs.QueryParam;
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
    @GET
    public Response getMsg( @QueryParam("name") String name,
                            @QueryParam("var1") String var1,
                            @QueryParam("var2") String var2,
                            @QueryParam("var3") String var3)
    {
        Thread th = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                runWorkflow(name, var1, var2, var3);
            }
        });

        th.start();

        return Response.status(200).entity("Workflow Running").build();
    }


    public String runWorkflow(String name, String var1, String var2, String var3)
    {
        if(Workflow.workflows.containsKey(name))
        {
            Elist l = Workflow.workflows.get(name);

            EVariable o = l.getVariable("var1");
            if(o != null)
                o.setValue(var1);

            o = l.getVariable("var2");
            if(o != null)
                o.setValue(var2);

            o = l.getVariable("var3");
            if(o != null)
                o.setValue(var3);

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



