package com.theteam.bpmn.design;

import com.theteam.snodes.*;

import java.io.FileOutputStream;
import java.util.UUID;

import javax.xml.bind.JAXBContext;  
import javax.xml.bind.Marshaller;

public class TestXML {
	
	SStartNode s;
    SEndNode e;
    STaskNode t;
    STaskNode t2;
    STaskNode t3;
    
    SNodeList nodeList;
	
    public void start() throws Exception {
		
        System.out.println("Start Making Nodes ...");
        makeNodes ();
        System.out.println("Finished Making Nodes ...");	
        
        System.out.println("Start Making XML ...");
		
        String path = "../xml/nodeXML.xml";
				
        toXMl(path);
		
        System.out.println("Finished Making XML ...");
        System.out.println("Path to XML " + path);
    }
	
    void makeNodes() {
	s = new SStartNode(Types.NodeType(Types.NodeTypes.START), UUID.randomUUID());
        e = new SEndNode(Types.NodeType(Types.NodeTypes.END), UUID.randomUUID());
        t = new STaskNode(Types.NodeType(Types.NodeTypes.TASK), UUID.randomUUID());
        t2 = new STaskNode(Types.NodeType(Types.NodeTypes.TASK), UUID.randomUUID());
        t3 = new STaskNode(Types.NodeType(Types.NodeTypes.TASK), UUID.randomUUID());
        
        s.setNextNode(t.getNId());
        t.setPreviousNode(s.getNId());
        t.setNextNode(t2.getNId());
        t2.setPreviousNode(t.getNId());
        t2.setNextNode(t3.getNId());
        t3.setPreviousNode(t2.getNId());
        t3.setNextNode(e.getNId());
        e.setPreviousNode(t3.getNId());
        
        nodeList = new SNodeList();
        
        nodeList.addStartNode(s);
        nodeList.addTaskNode(t);
        nodeList.addTaskNode(t2);
        nodeList.addTaskNode(t3);
        nodeList.addEndNode(e);
    }
	
    void toXMl(String path) throws Exception {
	JAXBContext contextObj = JAXBContext.newInstance(SNodeList.class);
        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);  
        marshallerObj.marshal(nodeList, new FileOutputStream(path));
    }
}