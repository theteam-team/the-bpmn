/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.theteam.bpmnengine;

import com.theteam.snodes.*;

import java.io.File;

import javax.xml.bind.JAXBContext;  
import javax.xml.bind.Unmarshaller;

public class App {
	
    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) throws Exception{
        System.out.println(new App().getGreeting());
        
        JAXBContext jaxbContext = JAXBContext.newInstance(SNodeList.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
     
        SNodeList nodes = (SNodeList) jaxbUnmarshaller.unmarshal( new File("../xml/nodeXML") );
     
        System.out.println("Nodes in the design:\n");

        for(SNode node : nodes.getAllNodes())
        {
            System.out.println(node.getNId() + " " + node.getType());
        }
        
        
    }
}