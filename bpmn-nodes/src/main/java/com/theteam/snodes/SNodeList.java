package com.theteam.snodes;

import java.util.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "nodes")
@XmlType(propOrder = { "startNode", "taskNodes", "endNode"})
public class SNodeList {
    
    @XmlElement(name = "startNode")
    private SStartNode startNode;

    @XmlElement(name = "endNode")
    private SEndNode endNode;

    @XmlElement(name = "taskNode")
    private List<STaskNode> taskNodes;
    private List<SNode> allNodes;
    private HashMap<String, SNode> nodesMap;
    
    public SNodeList () {
        taskNodes = new ArrayList<STaskNode>();
        allNodes = new ArrayList<SNode>();
        nodesMap = new HashMap<>();
    }

    public List<SNode> getAllNodes() {

        allNodes.add(startNode);
        
        for(STaskNode node : taskNodes)
            allNodes.add(node);

        allNodes.add(endNode);

        return allNodes;
    }
    
    public HashMap<String, SNode> createNodesMap() {
        nodesMap.put(startNode.getNId(), startNode);
        
        for(STaskNode node : taskNodes)
            nodesMap.put(node.getNId(), node);

        nodesMap.put(endNode.getNId(), endNode);

        return nodesMap;
    }

    public void setCircleNodes(List<STaskNode> taskNodes) {
        this.taskNodes = taskNodes;
    }
    
    public List<STaskNode> getTaskList() {
        return taskNodes;
    }
    
    public void addTaskNode(STaskNode node) {
        taskNodes.add(node);
    }


    public void addStartNode(SStartNode node) {
        startNode = node;
    }

    public SNode getStartNode() {
        return startNode;
    }
    
    public void addEndNode(SEndNode node) {
        endNode = node;
    }
    
}