package com.theteam.snodes;
import java.util.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "nodes")
@XmlType(propOrder = { "startNode", "taskNodes", "endNode"})
public class SNodeList
{
    @XmlElement(name = "startNode")
    private SStartNode startNode;

    @XmlElement(name = "endNode")
    private SEndNode endNode;

    @XmlElement(name = "taskNode")
    private List<STaskNode> taskNodes;

    HashMap<String, SNode> mapNodes;
    

    public SNodeList ()
    {
        taskNodes = new ArrayList<STaskNode>();
        mapNodes = new HashMap<>();
        
    }

    public List<SNode> getAllNodes()
    {
        List<SNode> allNodes = new ArrayList<SNode>();

        allNodes.add(startNode);
        
        for(STaskNode node : taskNodes)
        {
            allNodes.add(node);
            
        }

        allNodes.add(endNode);

        return allNodes;
    }

    public void setCircleNodes(List<STaskNode> taskNodes) {
        this.taskNodes = taskNodes;
    }
    
    public List<STaskNode> getTaskList()
    {
        return taskNodes;
    }
    
    public void addTaskNode(STaskNode node)
    {
        
        mapNodes.put(node.getNId(), node);
        taskNodes.add(node);
    }


    public void addStartNode(SStartNode node)
    {

        mapNodes.put(node.getNId(), node);
        startNode = node;
    }

    public SNode getStartNode()
    {
        return startNode;
    }
    
    public void addEndNode(SEndNode node)
    {

        mapNodes.put(node.getNId(), node);
        endNode = node;
    }

    public SNode getEndNode()
    {
        return endNode;
    }

    public SNode getListNode(String id)
    {
        return endNode;
    }

    public void removeNode(SNode node)
    {
        if (node.getType() == Types.NodeType(Types.NodeTypes.START))
            startNode = null;

        else if (node.getType() == Types.NodeType(Types.NodeTypes.END))
            endNode = null;

        else
            taskNodes.remove(node);
        
    }

    public SNode getNodeById(String iid)
    {
        return mapNodes.get(iid);
    }

    /*public void removeAllNodes()
    {
        System.out.println("remove all nodes");

        startNode = null;
        endNode = null;
        taskNodes.clear();
    }*/
}

