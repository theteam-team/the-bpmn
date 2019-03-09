package com.theteam.bpmn.design.dnode;

import java.util.UUID;

import com.theteam.snodes.SXML;

public class DStartNode extends DNode
{
    public DStartNode(SXML xmlWriter, UUID id, Boolean drawNode)
    {
        super(NodeImages.nodeImages.get("start"), id.toString());
        this.type = "start";

        this.drawNode = drawNode;

        if(drawNode)
        {
            this.xmlWriter = xmlWriter;
            xmlWriter.addStartNode(id.toString());

        }

    }
}