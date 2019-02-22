package com.theteam.snodes;

import java.io.FileOutputStream;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

public class SXML
{

    SNodeList nodeList;

    public SXML()
    {
        nodeList = new SNodeList();
    }

    public void addStartNode()
    {
        SStartNode s = new SStartNode(Types.NodeType(Types.NodeTypes.START), UUID.randomUUID());
        nodeList.addStartNode(s);
    }

    public void addEndNode()
    {
        SEndNode e = new SEndNode(Types.NodeType(Types.NodeTypes.END), UUID.randomUUID());
        nodeList.addEndNode(e);
    }
    public void addTaskNode()
    {
        STaskNode t = new STaskNode(Types.NodeType(Types.NodeTypes.TASK), UUID.randomUUID());
        nodeList.addTaskNode(t);
    }

    public void setNextNode(SNode currNode, SNode nextNode)
    {
        currNode.setNextNode(nextNode.getNId());
    }

    public void setPrevNode(SNode currNode, SNode prevNode)
    {
        currNode.setPreviousNode(prevNode.getNId());
    }


    public void saveToXML(String path) throws Exception
    {

        System.out.println("Start Making Nodes ...");
		System.out.println("Finished Making Nodes ...");
		
		System.out.println("Start Making XML ...");
        
        // path example
		// String path = "../xml/nodeXML.xml";
        
        JAXBContext contextObj = JAXBContext.newInstance(SNodeList.class);
        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        
        
        marshallerObj.marshal(nodeList, new FileOutputStream(path));

		System.out.println("Finished Making XML ...");
        System.out.println("Path to XML " + path);
    }
}