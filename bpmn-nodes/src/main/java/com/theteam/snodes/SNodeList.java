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
    

    public SNodeList ()
    {
        taskNodes = new ArrayList<STaskNode>();
        
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
        taskNodes.add(node);
    }


    public void addStartNode(SStartNode node)
    {
        startNode = node;
    }

    public SNode getStartNode()
    {
        return startNode;
    }
    
    public void addEndNode(SEndNode node)
    {
        
        endNode = node;
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

    /*public void removeAllNodes()
    {
        System.out.println("remove all nodes");

        startNode = null;
        endNode = null;
        taskNodes.clear();
    }*/
}

