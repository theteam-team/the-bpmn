package com.theteam.snodes;

import java.io.FileOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import com.theteam.ElementsList;
import com.theteam.io.SVariablesList;
import com.theteam.io.SVariable;
import com.theteam.snodes.event.*;

public class SXML
{

    ElementsList elementsList;

    SNodeList nodeList;
    SVariablesList variableList;

    public SXML()
    {
        elementsList = new ElementsList();

        nodeList = new SNodeList();
        variableList = new SVariablesList();
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
        
        STaskNode t = new STaskNode(Types.NodeType(Types.NodeTypes.TASK), id);
        nodeList.addTaskNode(t);
    }

    public void addDBNode(String id)
    {
        
        SDBNode t = new SDBNode(Types.NodeType(Types.NodeTypes.DB), id);
        nodeList.addDBNode(t);
    }

    public void addExternalEventNode(String id)
    {
        
        SExternalEvent t = new SExternalEvent(Types.NodeType(Types.NodeTypes.EXTERNAL_EVENT), id);
        nodeList.addExternalEventNode(t);
    }

    public void addTimerEventNode(String id)
    {
        
        STimerEvent t = new STimerEvent(Types.NodeType(Types.NodeTypes.TIMER_EVENT), id);
        nodeList.addTimerEventNode(t);
    }

    public void addScriptNode(String id)
    {
        
        SScriptNode t = new SScriptNode(Types.NodeType(Types.NodeTypes.SCRIPT), id);
        nodeList.addScriptNode(t);
    }
    public void addTestNode(String id)
    {
        
        STestNode t = new STestNode(Types.NodeType(Types.NodeTypes.TEST), id);
        nodeList.addTestNode(t);
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

    public void removeNode(String node)
    {
        SNode n = nodeList.getNodeById(node);
        nodeList.removeNode(n);
    }

    public void setRestLinkProperty(String iid, String value)
    {

        // System.out.println(iid);

        SNode node = nodeList.getNodeById(iid);

        STaskNode taskNode = (STaskNode) node;

        taskNode.setRestLink(value);

    }

    public void setServiceTypeProperty(String iid, String value)
    {

        // System.out.println(iid);

        SNode node = nodeList.getNodeById(iid);

        STaskNode taskNode = (STaskNode) node;

        taskNode.setServiceType(value);

    }

    public void setSoapFuncProperty(String iid, String value)
    {

        // System.out.println(iid);

        SNode node = nodeList.getNodeById(iid);

        STaskNode taskNode = (STaskNode) node;

        taskNode.setSoapFunc(value);

    }

    public void setInput(String iid, String value)
    {

        // System.out.println(iid);

        SNode node = nodeList.getNodeById(iid);

        //STaskNode taskNode = (STaskNode) node;

        node.setInput(value);

    }

    public void setOutput(String iid, String value)
    {

        // System.out.println(iid);

        SNode node = nodeList.getNodeById(iid);

        //STaskNode taskNode = (STaskNode) node;

        node.setOutput(value);

    }

    public void addVariable(String id, String name, String type, String value)
    {

        SVariable variable = new SVariable(id, name, type, value);
        variableList.addVariable(variable);

    }

    public void setVariableName(String id, String newName)
    {
        System.out.println("Name Changed");
        SVariable var = variableList.getVariableById(id);
        var.setName(newName);
    }

    public void setVariableType(String id, String type)
    {
        SVariable var = variableList.getVariableById(id);
        var.setType(type);
    }

    public void setVariableValue(String id, String value)
    {
        SVariable var = variableList.getVariableById(id);
        var.setValue(value);

    }


    public void saveToXML(String path) throws Exception
    {

        System.out.println("Start Making Nodes ...");
		System.out.println("Finished Making Nodes ...");
		
        System.out.println("Start Making XML ...");
        
        elementsList.setNodeList(nodeList);
        elementsList.setVariableList(variableList);
        
        // path example
		// String path = "../xml/nodeXML.xml";
        
        JAXBContext contextObj = JAXBContext.newInstance(ElementsList.class);
        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        
        
        marshallerObj.marshal(elementsList, new FileOutputStream(path));

		System.out.println("Finished Making XML ...");
        System.out.println("Path to XML " + path);
    }
}