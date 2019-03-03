package com.theteam.bpmn.design.dnode;

public class DEndNode extends DNode
{
    public DEndNode()
    {
        super(NodeImages.nodeImages.get("end"));
        this.type = "end";
    }
}