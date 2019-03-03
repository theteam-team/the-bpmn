package com.theteam.bpmn.design.dnode;

public class DStartNode extends DNode
{
    public DStartNode()
    {
        super(NodeImages.nodeImages.get("start"));
        this.type = "start";
    }
}