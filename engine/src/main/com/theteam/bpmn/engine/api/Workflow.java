package com.theteam.bpmn.engine.api;

import com.theteam.bpmn.engine.Elist;
import com.theteam.snodes.SNode;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;


/**
 * API End point /workflow
 * Start caertain workflow with a specific ID
 */
@Path("/workflow")  
public class Workflow
{  
    @GET  
    @Path("/{param}")  
    public Response getMsg(@PathParam("param") String id)
    {
        String message = runWorkflow(id);

        String output = message;  
        return Response.status(200).entity(output).build();
    }


    public String runWorkflow(String id)
    {
        
        if(Elist.nodes.getStartNode().getNId().equals(id))
        {
            System.out.println("\nStart WorkFlow \n" );

            SNode node = Elist.nodes.getStartNode();
            Boolean found;

            while(node != null)
            {

                System.out.println();
                
                found = false;
                node.run();

                for(SNode n : Elist.nodes.getAllNodes())
                {
                    
                    if(n.getNId().equals(node.getNextNode()))
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


