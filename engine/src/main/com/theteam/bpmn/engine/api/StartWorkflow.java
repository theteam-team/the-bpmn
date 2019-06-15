package com.theteam.bpmn.engine.api;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import com.theteam.bpmn.engine.Elist;
import com.theteam.bpmn.engine.Workflow;
import com.theteam.bpmn.engine.enode.ENode;
import com.theteam.bpmn.engine.io.EVariable;

import org.apache.commons.io.IOUtils;




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

            String instanceId = UUID.randomUUID().toString();

            ArrayList<String> workflowINstances = Workflow.runningWorkflows.get(name);

            if( workflowINstances == null)
            {
                ArrayList<String> tempList = new ArrayList<>();
                tempList.add(instanceId);
                Workflow.runningWorkflows.put(name, tempList);

            }
            else
            {
                workflowINstances.add(instanceId);
            }


            Elist l = Workflow.workflows.get(name);

            ENode node = l.getStartNodeOnStarted();

            System.out.println("\n------------ Workflow OnStarted Executing-------------");
            node.run(l, instanceId);
            System.out.println("\n------------ Workflow OnStarted Ended--------------");

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



