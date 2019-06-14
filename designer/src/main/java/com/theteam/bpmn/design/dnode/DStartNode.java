package com.theteam.bpmn.design.dnode;

import java.util.UUID;

import com.theteam.bpmn.design.dnode.dproperty.DComboBoxProperty;
import com.theteam.bpmn.design.loader.ImagesLoader;
import com.theteam.snodes.SXML;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SingleSelectionModel;


/**
 * DStartNode ** Start **
 * All nodes are ImageView Nodes from the javafx library
 * So all dnodes can be rendered using javafx application
 * All dnodes are represented by images
 */
public class DStartNode extends DNode
{

    StringProperty actionStringProperty1 = new SimpleStringProperty();

    public SingleSelectionModel<String> actionString;

    public ObservableList<String> actionStringList = FXCollections.observableArrayList(
        "OnStarted",
        "OnAwaked",
        "OnLoaded"
    );

    public DStartNode(SXML xmlWriter, UUID id, Boolean drawNode)
    {
        super(ImagesLoader.nodeImages.get("start"), id.toString());
        this.type = "start";

        this.drawNode = drawNode;

        if(drawNode)
        {
            this.xmlWriter = xmlWriter;
            xmlWriter.addStartNode(id.toString());

            DComboBoxProperty dActionStringProperty = new DComboBoxProperty("Action Type", actionString, actionStringList);

            allDProperties.add(dActionStringProperty);

            String a = (String) dActionStringProperty.getComboSelectionModel().getSelectedItem();
            xmlWriter.setStartActionProprty(id.toString(), a);


            dActionStringProperty.getComboSelectionModel().selectedIndexProperty().addListener((Observable o) -> {
                String selectedItem = (String) dActionStringProperty.getComboSelectionModel().getSelectedItem();

                xmlWriter.setStartActionProprty(id.toString(), selectedItem);
                
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

        xmlWriter.removePosition(getId());
        xmlWriter.removeNode(getId());
    }
}