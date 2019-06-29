package com.theteam.bpmn.design.dnode;

import java.util.UUID;

import com.theteam.bpmn.design.dnode.dproperty.DComboBoxProperty;
import com.theteam.bpmn.design.dnode.dproperty.DTextProperty;
import com.theteam.bpmn.design.loader.ImagesLoader;
import com.theteam.snodes.SXML;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SingleSelectionModel;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


/**
 * DStartNode ** Start **
 * All nodes are ImageView Nodes from the javafx library
 * So all dnodes can be rendered using javafx application
 * All dnodes are represented by images
 */
public class DStartNode extends DNode
{

    StringProperty actionStringProperty1 = new SimpleStringProperty();

    StringProperty messageProperty = new SimpleStringProperty();

    public SingleSelectionModel<String> actionString;

    public ObservableList<String> actionStringList = FXCollections.observableArrayList(
        "OnStarted",
        "OnAwaked",
        "OnLoaded",
        "message"
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

            DTextProperty dMessageProperty = new DTextProperty("message", messageProperty);

            allDProperties.add(dMessageProperty);

            DComboBoxProperty dActionStringProperty = new DComboBoxProperty("Action Type", actionString, actionStringList);

            allDProperties.add(dActionStringProperty);

            String a = (String) dActionStringProperty.getComboSelectionModel().getSelectedItem();
            xmlWriter.setStartActionProprty(id.toString(), a);


            dActionStringProperty.getComboSelectionModel().selectedIndexProperty().addListener((Observable o) -> {
                String selectedItem = (String) dActionStringProperty.getComboSelectionModel().getSelectedItem();

                xmlWriter.setStartActionProprty(id.toString(), selectedItem);
                
            });

            messageProperty.addListener(new ChangeListener<String>()
            {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue)
                {
                    xmlWriter.setStartMessageProprty(id.toString(), newValue);
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

        xmlWriter.removePosition(getId());
        xmlWriter.removeNode(getId());
    }
}