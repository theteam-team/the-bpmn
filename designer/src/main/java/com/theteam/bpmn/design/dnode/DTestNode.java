package com.theteam.bpmn.design.dnode;

import java.util.UUID;

import com.theteam.bpmn.design.dnode.dproperty.DComboBoxProperty;
import com.theteam.bpmn.design.dnode.dproperty.DTextProperty;
import com.theteam.bpmn.design.loader.ImagesLoader;
import com.theteam.snodes.SXML;

import javafx.beans.property.StringProperty;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SingleSelectionModel;


/**
 * DServiceTaskNode ** Service Task **
 * All nodes are ImageView Nodes from the javafx library
 * So all dnodes can be rendered using javafx application
 * All dnodes are represented by images
 */
public class DTestNode extends DNode
{

    StringProperty inputProperty = new SimpleStringProperty();
    StringProperty outputProperty = new SimpleStringProperty();

    public DTestNode(SXML xmlWriter, UUID id, Boolean drawNode)
    {
        super(ImagesLoader.nodeImages.get("triangle"), id.toString());
        this.type = "triangle";

        this.drawNode = drawNode;

        setId(id.toString());
        idProperty().set(id.toString());

        if(drawNode)
        {
            this.xmlWriter = xmlWriter;
        
            xmlWriter.addTestNode(id.toString());

            DTextProperty dInputProperty = new DTextProperty("Input", inputProperty);
            DTextProperty dOutputProperty = new DTextProperty("Output", outputProperty);


            allDProperties.add(dInputProperty);
            allDProperties.add(dOutputProperty);

            inputProperty.addListener(new ChangeListener<String>()
            {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue)
                {
                    xmlWriter.setInput(id.toString(), newValue);
                }
            });

            outputProperty.addListener(new ChangeListener<String>()
            {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue)
                {
                    xmlWriter.setOutput(id.toString(), newValue);
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

        if(getPrevDNode() != null)
        {
            xmlWriter.setNextNode(getPrevDNode().getId(), null);
            //setPrevDNode(null);
            getPrevDNode().setNNextDNode(null);
        }

        xmlWriter.removeNode(getId());
    }
}