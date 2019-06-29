package com.theteam.bpmn.design.dnode;

import java.util.UUID;

import com.theteam.bpmn.design.dnode.dproperty.DTextProperty;
import com.theteam.bpmn.design.loader.ImagesLoader;
import com.theteam.snodes.SXML;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


/**
 * DEndNode ** End **
 * All nodes are ImageView Nodes from the javafx library
 * So all dnodes can be rendered using javafx application
 * All dnodes are represented by images
 */
public class DCondition extends DNode
{
    StringProperty conditionProperty = new SimpleStringProperty();
    
    public DCondition(SXML xmlWriter, UUID id, Boolean drawNode)
    {
        super(ImagesLoader.nodeImages.get("condition"), id.toString());
        this.type = "condition";

        this.drawNode = drawNode;

        setId(id.toString());
        idProperty().set(id.toString());

        

        if(drawNode)
        {
            this.xmlWriter = xmlWriter;
            xmlWriter.addConditionNode(id.toString());

            DTextProperty dConditionProperty = new DTextProperty("condition", conditionProperty);
            allDProperties.add(dConditionProperty);


            conditionProperty.addListener(new ChangeListener<String>()
            {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue)
                {
                    xmlWriter.setConditionProperty(id.toString(), newValue);
                }
            });
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
            xmlWriter.setPrevNode1(getNextDNode1().getId(), null);
            //setNextDNode(null);
            getNextDNode1().setPPrevDNode(null);
        }

        if(getPrevDNode() != null)
        {
            xmlWriter.setNextNode(getPrevDNode().getId(), null);
            //setPrevDNode(null);
            getPrevDNode().setNNextDNode(null);
        }

        xmlWriter.removePosition(getId());
        xmlWriter.removeNode(getId());
    }
}