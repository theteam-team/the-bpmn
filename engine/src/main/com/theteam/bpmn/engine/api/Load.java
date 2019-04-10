package com.theteam.bpmn.engine.api;

import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.theteam.ElementsList;
import com.theteam.bpmn.engine.Elist;
import com.theteam.bpmn.engine.enode.*;
import com.theteam.bpmn.engine.enode.event.*;
import com.theteam.bpmn.engine.io.*;

import com.theteam.io.SVariable;
import com.theteam.snodes.*;
import com.theteam.snodes.event.*;


/**
 * API End point /load
 * Load xml file to engine
 */
@Path("/load")  
public class Load {  

    // This method is called if HTML and XML is not requested  
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayPlainTextHello() throws JAXBException
    {
        loadNodes();
        return "Loading XML Finished";
    }

    // This method is called if XML is requested  
    @GET  
    @Produces(MediaType.TEXT_XML)
    public String sayXMLHello() throws JAXBException
    {
        loadNodes();
        return "<?xml version=\"1.0\"?>" + "<load> loading XML Finished" + "</load>";  
    }

    // This method is called if HTML is requested  
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String sayHtmlHello() throws JAXBException
    {
        loadNodes();
        return "<html> " + "<title>" + "loadXML" + "</title>"  
            + "<body><h1>" + "loading XML Finished" + "</h1></body>" + "</html> ";  
    }

    public void loadNodes() throws JAXBException
    {
        System.out.println("Loading");

        JAXBContext jaxbContext = JAXBContext.newInstance(ElementsList.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        //Elist.nodes = (SNodeList) jaxbUnmarshaller.unmarshal( new File("../xml/nodeXML.xml") );
        Elist.allList = (ElementsList) jaxbUnmarshaller.unmarshal( new File("../xml/nodeXML.xml") );
        Elist.sNodes = Elist.allList.gNodeList();
        Elist.variables = Elist.allList.gVariableList();

        Elist.eStart = (EStart) Elist.getStartNode();

        System.out.println("\nVariables in the design:\n");

        for(SVariable variable : Elist.variables.getVariablesList())
        {
            System.out.println(variable.getNId() + " " + variable.getName() + " " + variable.getValue());

            switch(variable.getType())
            {
                case "int":
                    EInteger i = new EInteger(variable);
                    Elist.addVariable(i);
                    break;

                case "string":
                    EString s = new EString(variable);
                    Elist.addVariable(s);
                    break;

                case "float":
                    EFloat f = new EFloat(variable);
                    Elist.addVariable(f);
                    break;

                case "bool":
                    EBool b = new EBool(variable);
                    Elist.addVariable(b);
                    break;

                default:
                    System.out.println("Making a Variable went wrong");
            }
        }

        System.out.println("\n\nNodes in the design:\n");

        for(SNode node : Elist.sNodes.getAllNodes())
        {
            System.out.println(node.getNId() + " " + node.getType());

            switch(node.getType())
            {

                case "START":
                    EStart eStart = new EStart((SStartNode) node);
                    Elist.eNodes.add(eStart);
                    break;

                case "TASK":
                    EServiceTask eTask = new EServiceTask((STaskNode) node);
                    Elist.eNodes.add(eTask);
                    break;
                    
                case "END":
                    EEnd eEnd = new EEnd((SEndNode) node);
                    Elist.eNodes.add(eEnd);
                    break;

                case "DB":
                    EDB eDB = new EDB((SDBNode) node);
                    Elist.eNodes.add(eDB);
                    break;

                case "EXTERNAL_EVENT":
                    EExternalEvent eExternal = new EExternalEvent((SExternalEvent) node);
                    Elist.eNodes.add(eExternal);
                    break;

                case "TIMER_EVENT":
                    ETimerEvent eTimer = new ETimerEvent((STimerEvent) node);
                    Elist.eNodes.add(eTimer);
                    break;

                case "SCRIPT":
                    EScript eScript = new EScript((SScriptNode) node);
                    Elist.eNodes.add(eScript);
                    break;

                case "TEST":
                    ETest eTest = new ETest((STestNode) node);
                    Elist.eNodes.add(eTest);
                    break;

                default:
                    System.out.println("Making a node went wrong");
            }
        }

        

    }
}
