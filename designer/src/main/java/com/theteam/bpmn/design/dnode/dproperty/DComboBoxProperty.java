
package com.theteam.bpmn.design.dnode.dproperty;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXToggleNode;

import javafx.collections.ObservableList;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;


/**
 * DComboBoxProperty property presents property that have a comboBox to choose from
 * Node properties are used by design ndoes (dnodes) to present properties to users
 */
public class DComboBoxProperty extends DProperty
{
    JFXToggleNode toggleNode = null;
    JFXComboBox<String> comboBox = null;

    public DComboBoxProperty(String labelText, SingleSelectionModel<String> sm, ObservableList<String> list)
    {
        toggleNode = new JFXToggleNode();
        toggleNode.setTextFill(Color.YELLOWGREEN);
        toggleNode.setText(labelText);

        comboBox = new JFXComboBox<String>();
        comboBox.setItems(list);
        comboBox.getSelectionModel().select(0);

        getChildren().addAll(toggleNode, comboBox);

        setHgrow(comboBox, Priority.ALWAYS);
        
    }

    public void setComboSelectionModel(SingleSelectionModel<String> sm)
    {
        sm = comboBox.getSelectionModel();

    }

    public SingleSelectionModel<String> getComboSelectionModel()
    {
        return comboBox.getSelectionModel();

    }
}