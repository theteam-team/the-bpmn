package com.theteam.bpmn.design.dnode;

import java.util.UUID;

import com.theteam.bpmn.design.loader.ImagesLoader;
import com.theteam.snodes.SXML;


/**
 * DEndNode ** End **
 * All nodes are ImageView Nodes from the javafx library
 * So all dnodes can be rendered using javafx application
 * All dnodes are represented by images
 */
public class DEndNode extends DNode
{
    public DEndNode(SXML xmlWriter, UUID id, Boolean drawNode)
    {
        super(ImagesLoader.nodeImages.get("end"), id.toString());
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

    public void removeNode()
    {
        if(getNextDNode() != null)
        {
            xmlWriter.setPrevNode(getNextDNode().getId(), null);
            //setNextDNode(null);
            getNextDNode().setPPrevDNode(null);
        }

        if(getPrevDNode() != null)
        {
            xmlWriter.setNextNode(getPrevDNode().getId(), null);
            //setPrevDNode(null);
            getPrevDNode().setNNextDNode(null);
        }

        xmlWriter.removeNode(getId());
    }
}