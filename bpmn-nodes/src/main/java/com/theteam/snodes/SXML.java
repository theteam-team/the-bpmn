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

    public void addStartNode(String id)
    {
        // System.out.println("Hello " + id);
        SStartNode s = new SStartNode(Types.NodeType(Types.NodeTypes.START), id);
        nodeList.addStartNode(s);
    }

    public void addEndNode(String id)
    {
        
        SEndNode e = new SEndNode(Types.NodeType(Types.NodeTypes.END), id);
        nodeList.addEndNode(e);
    }
    public void addTaskNode(String id)
    {
        // out.println(id);
        STaskNode t = new STaskNode(Types.NodeType(Types.NodeTypes.TASK), id);
        nodeList.addTaskNode(t);
    }

    public void setNextNode(String currNode, String nextNode)
    {
        SNode node = nodeList.getNodeById(currNode);
        node.setNextNode(nextNode);
    }

    public void setPrevNode(String currNode, String prevNode)
    {
        SNode node = nodeList.getNodeById(currNode);
        node.setPreviousNode(prevNode);
    }

    public void setRestProperty(String iid, String value)
    {

        // System.out.println(iid);

        SNode node = nodeList.getNodeById(iid);

        STaskNode taskNode = (STaskNode) node;

        taskNode.setRestLink(value);

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