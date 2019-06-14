package com.theteam.bpmn.engine.api;

import com.theteam.bpmn.engine.Elist;
import com.theteam.bpmn.engine.Workflow;
import com.theteam.bpmn.engine.enode.*;
import com.theteam.bpmn.engine.io.EVariable;
import com.theteam.snodes.event.SExternalEvent;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;




/**
 * API End point /workflow
 * Start caertain workflow with a specific ID
 */
@Path("/workflowbody")  
public class GetWorkflowBody
{  
    //@GET  
    //@Path("/{param}")
    //@PathParam("param") String name
    @GET
    @Produces(MediaType.TEXT_XML)
    public Response getMsg( @QueryParam("name") String name)
    {

        String data = "<?xml version=\"1.0\"?>" + "<load> Error" + "</load>";
        try{


            System.out.println("/xml/"+name+".xml");
            String s = getClass().getResource("/xml/"+name+".xml").getFile();
            s = s.substring(1);
            System.out.println(s);

            //String data = readFileAsString("C:\\Users\\pankaj\\Desktop\\test.java"); 
            data = readFileAsString(s); 
            System.out.println(data);

        } catch(Exception e) { System.out.println(e); }

        return Response.status(200).entity(data).build();
    }

    public static String readFileAsString(String fileName) throws Exception 
    { 
        String data = ""; 
        data = new String(Files.readAllBytes(Paths.get(fileName))); 
        return data; 
    } 

}



