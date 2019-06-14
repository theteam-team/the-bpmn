package com.theteam.bpmn.design.dnode;

import java.util.UUID;

import com.theteam.bpmn.design.dnode.dproperty.DButtonJsonProperty;
import com.theteam.bpmn.design.dnode.dproperty.DButtonProperty;
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
 * DStartNode ** Start **
 * All nodes are ImageView Nodes from the javafx library
 * So all dnodes can be rendered using javafx application
 * All dnodes are represented by images
 */
public class DJsonNode extends DNode
{
    StringProperty inputProperty = new SimpleStringProperty();
    StringProperty outputProperty = new SimpleStringProperty();

    StringProperty jsonOperationProperty = new SimpleStringProperty();
    StringProperty jsonDataProperty = new SimpleStringProperty();

    public SingleSelectionModel<String> jsonOperation;

    public ObservableList<String> jsonOperationList = FXCollections.observableArrayList(
        "make",
        "break"
    );


    public DJsonNode(SXML xmlWriter, UUID id, Boolean drawNode)
    {
        super(ImagesLoader.nodeImages.get("json"), id.toString());
        this.type = "json";

        this.drawNode = drawNode;

        if(drawNode)
        {
            this.xmlWriter = xmlWriter;
            xmlWriter.addJSONNode(id.toString());

            DTextProperty dInputProperty = new DTextProperty("Input", inputProperty);
            DTextProperty dOutputProperty = new DTextProperty("Output", outputProperty);

            DComboBoxProperty dOperationProperty = new DComboBoxProperty("Operation", jsonOperation, jsonOperationList);
            DButtonJsonProperty DJsonDataProperty = new DButtonJsonProperty("Json Data", jsonDataProperty, "jsonStage", id.toString(), xmlWriter);

            allDProperties.add(dInputProperty);
            allDProperties.add(dOutputProperty);

            allDProperties.add(dOperationProperty);
            allDProperties.add(DJsonDataProperty);

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

            dOperationProperty.getComboSelectionModel().selectedIndexProperty().addListener((Observable o) -> {

                String selectedItem = (String) dOperationProperty.getComboSelectionModel().getSelectedItem();
                xmlWriter.setJsonActionProperty(id.toString(), selectedItem);
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