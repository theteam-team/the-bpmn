package com.theteam.snodes;
import java.util.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.theteam.snodes.event.*;


@XmlType(propOrder = { "start", "serviceTask", "db", "script", "externalEvent", "timerEvent", "test", "end"})
public class SNodeList
{
    @XmlElement(name = "start")
    private SStartNode start;

    @XmlElement(name = "end")
    private SEndNode end;

    @XmlElement(name = "serviceTask")
    private List<STaskNode> serviceTask;

    @XmlElement(name = "db")
    private List<SDBNode> db;

    @XmlElement(name = "script")
    private List<SScriptNode> script;

    @XmlElement(name = "externalEvent")
    private List<SExternalEvent> externalEvent;

    @XmlElement(name = "timerEvent")
    private List<STimerEvent> timerEvent;

    @XmlElement(name = "test")
    private List<STestNode> test;

    HashMap<String, SNode> mapNodes;
    

    public SNodeList ()
    {
        serviceTask = new ArrayList<STaskNode>();
        db = new ArrayList<SDBNode>();
        script = new ArrayList<SScriptNode>();
        externalEvent = new ArrayList<SExternalEvent>();
        timerEvent = new ArrayList<STimerEvent>();
        test = new ArrayList<STestNode>();
        
        mapNodes = new HashMap<>();
        
    }

    public List<SNode> getAllNodes()
    {
        List<SNode> allNodes = new ArrayList<SNode>();

        allNodes.add(start);
        
        for(STaskNode node : serviceTask)
        {
            allNodes.add(node);
            
        }
        for(SDBNode node : db)
        {
            allNodes.add(node);
            
        }
        for(SScriptNode node : script)
        {
            allNodes.add(node);
            
        }
        for(SExternalEvent node : externalEvent)
        {
            allNodes.add(node);
            
        }
        for(STimerEvent node : timerEvent)
        {
            allNodes.add(node);
            
        }
        for(STestNode node : test)
        {
            allNodes.add(node);
            
        }

        allNodes.add(end);

        return allNodes;
    }

    /*
    public void setCircleNodes(List<STaskNode> taskNodes) {
        this.serviceTask = taskNodes;
    }
    */
    
    public List<STaskNode> getTaskList()
    {
        return serviceTask;
    }
    
    public void addTaskNode(STaskNode node)
    {
        
        mapNodes.put(node.getNId(), node);
        serviceTask.add(node);
    }

    public List<SDBNode> getDBList()
    {
        return db;
    }
    
    public void addDBNode(SDBNode node)
    {
        
        mapNodes.put(node.getNId(), node);
        db.add(node);
    }

    public List<SExternalEvent> getExternalEventList()
    {
        return externalEvent;
    }
    
    public void addExternalEventNode(SExternalEvent node)
    {
        
        mapNodes.put(node.getNId(), node);
        externalEvent.add(node);
    }

    public List<STimerEvent> getTimerList()
    {
        return timerEvent;
    }
    
    public void addTimerEventNode(STimerEvent node)
    {
        
        mapNodes.put(node.getNId(), node);
        timerEvent.add(node);
    }

    public List<SScriptNode> getScriptList()
    {
        return script;
    }
    
    public void addScriptNode(SScriptNode node)
    {
        
        mapNodes.put(node.getNId(), node);
        script.add(node);
    }

    public List<STestNode> getTestList()
    {
        return test;
    }
    
    public void addTestNode(STestNode node)
    {
        
        mapNodes.put(node.getNId(), node);
        test.add(node);
    }


    public void addStartNode(SStartNode node)
    {

        mapNodes.put(node.getNId(), node);
        start = node;
    }

    public SNode getStartNode()
    {
        return start;
    }
    
    public void addEndNode(SEndNode node)
    {

        mapNodes.put(node.getNId(), node);
        end = node;
    }

    public SNode getEndNode()
    {
        return end;
    }


    public void removeNode(SNode node)
    {
        mapNodes.remove(node.getNId());
        
        if (node.getType() == Types.NodeType(Types.NodeTypes.START))
            start = null;

        else if (node.getType() == Types.NodeType(Types.NodeTypes.END))
            end = null;
            
        else if (node.getType() == Types.NodeType(Types.NodeTypes.TASK))
            serviceTask.remove(node);

        else if (node.getType() == Types.NodeType(Types.NodeTypes.DB))
            db.remove(node);

        else if (node.getType() == Types.NodeType(Types.NodeTypes.EXTERNAL_EVENT))
            externalEvent.remove(node);

        else if (node.getType() == Types.NodeType(Types.NodeTypes.TIMER_EVENT))
            timerEvent.remove(node);

        else if (node.getType() == Types.NodeType(Types.NodeTypes.SCRIPT))
            script.remove(node);

        else if (node.getType() == Types.NodeType(Types.NodeTypes.TEST))
            test.remove(node);

    }


    public SNode getNodeById(String iid)
    {
        return mapNodes.get(iid);
    }

    public void removeAllNodes()
    {
        System.out.println("remove all nodes");

        start = null;
        end = null;

        serviceTask.clear();
        db.clear();
        externalEvent.clear();
        timerEvent.clear();
        script.clear();
        test.clear();
    }
}

