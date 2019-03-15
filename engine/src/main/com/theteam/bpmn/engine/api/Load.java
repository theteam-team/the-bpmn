package com.theteam.bpmn.engine.api;

import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.theteam.bpmn.engine.Elist;
import com.theteam.snodes.SNode;
import com.theteam.snodes.SNodeList;

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

        JAXBContext jaxbContext = JAXBContext.newInstance(SNodeList.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        //Elist.nodes = (SNodeList) jaxbUnmarshaller.unmarshal( new File("../xml/nodeXML.xml") );
        Elist.nodes = (SNodeList) jaxbUnmarshaller.unmarshal( new File("C:/work/the-bpmn/xml/nodeXML.xml") );

        System.out.println("Nodes in the design:\n");

        for(SNode node : Elist.nodes.getAllNodes())
        {
            System.out.println(node.getNId() + " " + node.getType());
        }

    }
}
