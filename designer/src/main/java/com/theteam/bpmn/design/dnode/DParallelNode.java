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
public class DParallelNode extends DNode
{
    public DParallelNode(SXML xmlWriter, UUID id, Boolean drawNode)
    {
        super(ImagesLoader.nodeImages.get("parallel"), id.toString());
        this.type = "parallel";

        this.drawNode = drawNode;

        setId(id.toString());
        idProperty().set(id.toString());

        if(drawNode)
        {
            this.xmlWriter = xmlWriter;
            xmlWriter.addParallelNode(id.toString());
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

        if(getNextDNode1() != null)
        {
            xmlWriter.setPrevNode(getNextDNode1().getId(), null);
            //setNextDNode(null);
            getNextDNode1().setPPrevDNode1(null);
        }

        if(getPrevDNode() != null)
        {
            xmlWriter.setNextNode(getPrevDNode().getId(), null);
            //setPrevDNode(null);
            getPrevDNode().setNNextDNode(null);
        }

        if(getPrevDNode1() != null)
        {
            xmlWriter.setNextNode(getPrevDNode1().getId(), null);
            //setPrevDNode(null);
            getPrevDNode1().setNNextDNode1(null);
        }

        xmlWriter.removePosition(getId());
        xmlWriter.removeNode(getId());
    }
}