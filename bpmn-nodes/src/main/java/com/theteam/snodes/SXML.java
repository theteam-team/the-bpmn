package com.theteam.snodes;

import java.io.FileOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import com.theteam.ElementsList;
import com.theteam.io.SVariablesList;
import com.theteam.jsondata.SJsonData;
import com.theteam.jsondata.SJsonDataList;
import com.theteam.positions.SPosition;
import com.theteam.positions.SPositionList;
import com.theteam.io.SVariable;
import com.theteam.snodes.event.*;

public class SXML
{

    ElementsList elementsList;

    SNodeList nodeList;
    SVariablesList variableList;
    SPositionList poistionList;

    public SXML()
    {
        elementsList = new ElementsList();

        nodeList = new SNodeList();
        variableList = new SVariablesList();
        poistionList = new SPositionList();
    }

    public void setWorkflowName(String name)
    {
        nodeList.setName(name);
    }

    public void setNextNode(String currNode, String nextNode)
    {
        SNode node = nodeList.getNodeById(currNode);
        node.setNextNode(nextNode);
    }

    public void setNextNode1(String currNode, String nextNode)
    {
        SNode node = nodeList.getNodeById(currNode);
        node.setNextNode1(nextNode);
    }

    public void setPrevNode(String currNode, String prevNode)
    {
        SNode node = nodeList.getNodeById(currNode);
        node.setPreviousNode(prevNode);
    }

    public void setPrevNode1(String currNode, String prevNode)
    {
        SNode node = nodeList.getNodeById(currNode);
        node.setPreviousNode1(prevNode);
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

    public void removeNode(String node)
    {
        SNode n = nodeList.getNodeById(node);
        nodeList.removeNode(n);
        
    }

    public void clear()
    {
        nodeList.removeAllNodes();
        poistionList.clear();
    }

    //
    //
    //
    //
    // Variables
    
    public void addVariable(String id, String name, String type, String value)
    {

        SVariable variable = new SVariable(id, name, type, value);
        variableList.addVariable(variable);

    }

    public void setVariableName(String id, String newName)
    {
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

    //
    //
    //
    //
    // Positions

    public void addPosition(String id, String nodeId, String x, String y)
    {

        SPosition pos = new SPosition(id, nodeId, x, y);
        poistionList.addPosition(pos);
    }

    public void setPositionNodeID(String id, String nodeId)
    {
        SPosition pos = poistionList.getPositionById(id);
        pos.setNodeId(nodeId);
    }

    public void setPositionX(String id, String x)
    {
        SPosition pos = poistionList.getPositionById(id);
        pos.setX(x);
    }

    public void setPositionY(String id, String y)
    {
        SPosition pos = poistionList.getPositionById(id);
        pos.setY(y);
    }

    public void removePosition(String nodeId)
    {
        poistionList.removePosition(nodeId);
    }

    public void updatePosition(String nodeId, String x, String y)
    {
        poistionList.updatePosition(nodeId, x, y);
    }


    //
    //
    //
    //
    // START

    public void addStartNode(String id)
    {
        // System.out.println("Hello " + id);
        SStartNode s = new SStartNode(Types.NodeType(Types.NodeTypes.START), id);
        nodeList.addStartNode(s);
    }


    //
    //
    //
    //
    // END

    public void addEndNode(String id)
    {
        
        SEndNode e = new SEndNode(Types.NodeType(Types.NodeTypes.END), id);
        nodeList.addEndNode(e);
    }

    //
    //
    //
    //
    // TASK


    public void addTaskNode(String id)
    {
        
        STaskNode t = new STaskNode(Types.NodeType(Types.NodeTypes.TASK), id);
        nodeList.addTaskNode(t);
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

    //
    //
    //
    //
    // DB

    public void addDBNode(String id)
    {
        
        SDBNode t = new SDBNode(Types.NodeType(Types.NodeTypes.DB), id);
        nodeList.addDBNode(t);
    }

    public void setDBConnectionStringProperty(String iid, String value)
    {

        // System.out.println(iid);

        SNode node = nodeList.getNodeById(iid);

        SDBNode db = (SDBNode) node;

        db.setConnectionString(value);

    }
    public void setDBConnectedEvent(String iid, String value)
    {

        // System.out.println(iid);

        SNode node = nodeList.getNodeById(iid);

        SDBNode db = (SDBNode) node;

        db.setConnectedEvent(value);

    }

    public void setDBSelectStatmentProperty(String iid, String value)
    {

        // System.out.println(iid);

        SNode node = nodeList.getNodeById(iid);

        SDBNode db = (SDBNode) node;

        db.setSelectStatement(value);

    }

    public void setDBUserNameProperty(String iid, String value)
    {

        // System.out.println(iid);

        SNode node = nodeList.getNodeById(iid);

        SDBNode db = (SDBNode) node;

        db.setUserName(value);

    }
    public void setDBPasswordProperty(String iid, String value)
    {

        // System.out.println(iid);

        SNode node = nodeList.getNodeById(iid);

        SDBNode db = (SDBNode) node;

        db.setPassword(value);

    }


    //
    //
    //
    //
    // EXTERNAL

    public void addExternalEventNode(String id)
    {
        
        SExternalEvent t = new SExternalEvent(Types.NodeType(Types.NodeTypes.EXTERNAL_EVENT), id);
        nodeList.addExternalEventNode(t);
    }

    public void setExternalConnectedEvent(String iid, String value)
    {

        // System.out.println(iid);

        SNode node = nodeList.getNodeById(iid);

        SExternalEvent ext = (SExternalEvent) node;

        ext.setConnectedEvent(value);

    }

    //
    //
    //
    //
    // TIMER

    public void addTimerEventNode(String id)
    {
        
        STimerEvent t = new STimerEvent(Types.NodeType(Types.NodeTypes.TIMER_EVENT), id);
        nodeList.addTimerEventNode(t);
    }

    public void setTimerProperty(String iid, String value)
    {

        // System.out.println(iid);

        SNode node = nodeList.getNodeById(iid);

        STimerEvent t = (STimerEvent) node;

        t.setTime(value);

    }

    //
    //
    //
    //
    // SCRIPT

    public void addScriptNode(String id)
    {
        
        SScriptNode t = new SScriptNode(Types.NodeType(Types.NodeTypes.SCRIPT), id);
        nodeList.addScriptNode(t);
    }


    //
    //
    //
    //
    // JSON

    public void addJSONNode(String id)
    {
        SJsonNode t = new SJsonNode(Types.NodeType(Types.NodeTypes.JSON), id);

        SJsonDataList jsonDataList = new SJsonDataList();
        t.setJsonData(jsonDataList);

        nodeList.addJsonNode(t);
    }

    public void setJsonActionProperty(String iid, String value)
    {

        // System.out.println(iid);

        SNode node = nodeList.getNodeById(iid);

        SJsonNode json = (SJsonNode) node;

        json.setAction(value);

    }

    public void addJsonData(String nodeId, String jsonDataId, String name, String val)
    {

        // System.out.println(iid);

        SNode node = nodeList.getNodeById(nodeId);

        SJsonNode json = (SJsonNode) node;

        SJsonData jsonData = new SJsonData(jsonDataId, name, val);

        json.addJsonData(jsonData);

    }

    public void removeJsonData(String nodeId, String jsonDataId)
    {

        // System.out.println(iid);

        SNode node = nodeList.getNodeById(nodeId);

        SJsonNode json = (SJsonNode) node;

        json.removeJsonData(jsonDataId);

    }


    //
    //
    //
    //
    // TEST

    public void addTestNode(String id)
    {
        
        STestNode t = new STestNode(Types.NodeType(Types.NodeTypes.TEST), id);
        nodeList.addTestNode(t);
    }


    //
    //
    //
    //
    // PARALLEL

    public void addParallelNode(String id)
    {
        // System.out.println("Hello " + id);
        SParallel p = new SParallel(Types.NodeType(Types.NodeTypes.PARALLEL), id);
        nodeList.addParallelNode(p);
    }


    //
    //
    //
    //
    // CONDITION

    public void addConditionNode(String id)
    {
        // System.out.println("Hello " + id);
        SCondition c = new SCondition(Types.NodeType(Types.NodeTypes.CONDITION), id);
        nodeList.addConditionNode(c);
    }

    public void setConditionProperty(String iid, String value)
    {

        // System.out.println(iid);

        SNode node = nodeList.getNodeById(iid);

        SCondition condition = (SCondition) node;

        condition.setExpression(value);

    }

    //
    //
    //
    //
    // SAVE


    public void saveToXML(String path) throws Exception
    {

        //System.out.println("Start Making Nodes ...");
		//System.out.println("Finished Making Nodes ...");
		
        //System.out.println("Start Making XML ...");
        
        elementsList.setNodeList(nodeList);
        elementsList.setVariableList(variableList);
        elementsList.setPositionsList(poistionList);
        
        // path example
		// String path = "../xml/nodeXML.xml";
        
        JAXBContext contextObj = JAXBContext.newInstance(ElementsList.class);
        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        
        
        marshallerObj.marshal(elementsList, new FileOutputStream(path));

		//System.out.println("Finished Making XML ...");
        //System.out.println("Path to XML " + path);
    }
}