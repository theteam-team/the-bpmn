package com.theteam.bpmn.design.dnode;

public class DServiceTaskNode extends DNode
{
    public DServiceTaskNode()
    {
        super(NodeImages.nodeImages.get("service_task"));
        this.type = "service_task";
    }
}