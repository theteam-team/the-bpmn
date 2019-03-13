
package com.theteam.bpmn.design.dnode;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleNode;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;

public class DComboBoxProperty extends Dprop
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