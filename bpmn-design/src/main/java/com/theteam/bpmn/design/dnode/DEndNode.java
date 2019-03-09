package com.theteam.bpmn.design.dnode;

import java.util.UUID;

import com.theteam.snodes.SXML;

public class DEndNode extends DNode
{
    public DEndNode(SXML xmlWriter, UUID id, Boolean drawNode)
    {
        super(NodeImages.nodeImages.get("end"), id.toString());
        this.type = "end";

        this.drawNode = drawNode;

        setId(id.toString());
        idProperty().set(id.toString());

        if(drawNode)
        {
            this.xmlWriter = xmlWriter;
            xmlWriter.addEndNode(id.toString());
        }
    }
}