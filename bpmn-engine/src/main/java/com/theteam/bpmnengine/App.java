/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.theteam.bpmnengine;

import com.theteam.snodes.*;

import java.io.File;
import java.util.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

public class App {
	
	public static SNode currentNode;
    public static List<SNode> allNodes = new ArrayList<SNode>();
    public static HashMap<String, SNode> nodesMap = new HashMap<>();
	
    public String getGreeting() {
        return "Hello world.";
    }

    /*public static SNode getByNId (String id) {
        
        for (SNode node : allNodes)
            if (Objects.equals(id, node.getNId()))
                return node;
        
        return null;
    }*/
    
    
    public static SNode getByType (String type) {
        
        for (SNode node : allNodes)
            if (Objects.equals(type, node.getType()))
                return node;
        
        return null;
    }
    
    public static int temp() {
        
        System.out.println("\nEnter a number: ");
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        
        if (n > 0) {
            return 1;
        }
        else {
            return -1;
        }
    }
    
    public static void main(String[] args) throws Exception {
		
        System.out.println(new App().getGreeting());
        
        JAXBContext jaxbContext = JAXBContext.newInstance(SNodeList.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
     
        SNodeList nodes = (SNodeList) jaxbUnmarshaller.unmarshal( new File("../xml/nodeXML.xml") );
        
        allNodes = nodes.getAllNodes();
        nodesMap = nodes.createNodesMap();
        
        System.out.println("Nodes in the design:\n");

        for(SNode node : allNodes)
            System.out.println(node.getNId() + " " + node.getType());
        
        System.out.println();
   
        currentNode = getByType("START");
        
        while (! Objects.equals(currentNode.getNextNode(), null)) {
            
            System.out.println("\n" + currentNode.getType() + " " +  currentNode.getNId());
            
            if (Objects.equals("START", currentNode.getType())) {
                currentNode = nodesMap.get(currentNode.getNextNode());
            }
            else if (Objects.equals("TASK", currentNode.getType())) {
                int number = temp();
                
                if (number > 0) {
                    currentNode = nodesMap.get(currentNode.getNextNode());
                }
                else {
                    System.out.println("\nError at node " + currentNode.getNId());
                    currentNode = nodesMap.get(currentNode.getPreviousNode());
                }
            }
            else {
                //currentNode = nodes.getByNId(currentNode.getNextNode());
            }
        }
    }
}