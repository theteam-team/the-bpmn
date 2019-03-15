package com.theteam.bpmn.design.dnode;

import java.util.UUID;

import com.theteam.bpmn.design.loader.ImagesLoader;
import com.theteam.snodes.SXML;


/**
 * DStartNode ** Start **
 * All nodes are ImageView Nodes from the javafx library
 * So all dnodes can be rendered using javafx application
 * All dnodes are represented by images
 */
public class DStartNode extends DNode
{
    public DStartNode(SXML xmlWriter, UUID id, Boolean drawNode)
    {
        super(ImagesLoader.nodeImages.get("start"), id.toString());
        this.type = "start";

        this.drawNode = drawNode;

        if(drawNode)
        {
            this.xmlWriter = xmlWriter;
            xmlWriter.addStartNode(id.toString());

        }

    }
}