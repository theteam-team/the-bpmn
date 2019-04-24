package com.theteam.bpmn.engine.api;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;

import javax.ws.rs.GET;
//import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.theteam.ElementsList;
import com.theteam.bpmn.engine.Elist;
import com.theteam.bpmn.engine.Workflow;
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
@javax.ws.rs.Path("/load")  
public class Load {

    Unmarshaller jaxbUnmarshaller;

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
        jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        Consumer<Path> workflowConsumer = new Consumer<Path>()
        {
            public void accept(Path name)
            {
                try { makeWorkflow(name); }
                catch(Exception e) {}
            };
        };

        try
        {
            Files.walk(Paths.get("C:/work/bpm/the-bpmn/xml"))
                .filter(Files::isRegularFile)
                .forEach(workflowConsumer);

        } catch(Exception e) { System.out.println(e); }

    }
    
    void makeWorkflow(Path path)
    {
        Elist workflow = new Elist();

        //Elist.nodes = (SNodeList) jaxbUnmarshaller.unmarshal( new File("../xml/nodeXML.xml") );
        try { workflow.allList = (ElementsList) jaxbUnmarshaller.unmarshal( path.toFile() ); }
        catch(Exception e) {System.out.println(e);}

        
        workflow.sNodes = workflow.allList.gNodeList();
        
        workflow.variables = workflow.allList.gVariableList();
        
        workflow.eStart = (EStart) workflow.getStartNode();
        
        Workflow.workflows.put(workflow.sNodes.getName(), workflow);

        System.out.println("\nWorkflow : " + workflow.sNodes.getName() + "\n");

        System.out.println("\nVariables in the design " + workflow.sNodes.getName() + "\n");

        for(SVariable variable : workflow.variables.getVariablesList())
        {
            System.out.println(variable.getNId() + " " + variable.getName() + " " + variable.getValue());

            switch(variable.getType())
            {
                case "int":
                    EInteger i = new EInteger(variable);
                    workflow.addVariable(i);
                    break;

                case "string":
                    EString s = new EString(variable);
                    workflow.addVariable(s);
                    break;

                case "float":
                    EFloat f = new EFloat(variable);
                    workflow.addVariable(f);
                    break;

                case "bool":
                    EBool b = new EBool(variable);
                    workflow.addVariable(b);
                    break;

                default:
                    System.out.println("Making a Variable went wrong");
            }
        }

        System.out.println("\n\nNodes in the design " + workflow.sNodes.getName() + "\n");

        for(SNode node : workflow.sNodes.getAllNodes())
        {
            System.out.println(node.getNId() + " " + node.getType());

            
            switch(node.getType())
            {

                case "START":
                    EStart eStart = new EStart((SStartNode) node, workflow);
                    workflow.eNodes.add(eStart);
                    break;

                case "TASK":
                    EServiceTask eTask = new EServiceTask((STaskNode) node, workflow);
                    workflow.eNodes.add(eTask);
                    break;
                    
                case "END":
                    EEnd eEnd = new EEnd((SEndNode) node, workflow);
                    workflow.eNodes.add(eEnd);
                    break;

                case "DB":
                    EDB eDB = new EDB((SDBNode) node, workflow);
                    workflow.eNodes.add(eDB);
                    break;

                case "EXTERNAL_EVENT":
                    EExternalEvent eExternal = new EExternalEvent((SExternalEvent) node, workflow);
                    workflow.eNodes.add(eExternal);
                    break;

                case "TIMER_EVENT":
                    ETimerEvent eTimer = new ETimerEvent((STimerEvent) node, workflow);
                    workflow.eNodes.add(eTimer);
                    break;

                case "SCRIPT":
                    EScript eScript = new EScript((SScriptNode) node, workflow);
                    workflow.eNodes.add(eScript);
                    break;

                case "TEST":
                    ETest eTest = new ETest((STestNode) node, workflow);
                    workflow.eNodes.add(eTest);
                    break;

                case "CONDITION":
                    ECondition eCond = new ECondition((SCondition) node, workflow);
                    workflow.eNodes.add(eCond);
                    break;

                case "PARALLEL":
                    EParallel eParallel = new EParallel((SParallel) node, workflow);
                    workflow.eNodes.add(eParallel);
                    break;

                default:
                    System.out.println("Making a node went wrong");
            }
            
        }
    }
}
