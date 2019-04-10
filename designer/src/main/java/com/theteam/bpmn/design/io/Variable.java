package com.theteam.bpmn.design.io;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleNode;

import javafx.beans.property.StringProperty;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;


import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXToggleNode;

import javafx.collections.ObservableList;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;


import javafx.scene.layout.HBox;

public class Variable extends HBox
{

    JFXTextField nameTextField = null;
    JFXComboBox<String> typeComboBox = null;
    JFXTextField valueTextField = null;

    public Variable(StringProperty nameProperty,
                    ObservableList<String> typeList,
                    StringProperty valueProperty)
    {

        nameTextField = new JFXTextField();
        nameTextField.textProperty().bindBidirectional(nameProperty);

        valueTextField = new JFXTextField();
        valueTextField.textProperty().bindBidirectional(valueProperty);

        typeComboBox = new JFXComboBox<String>();
        typeComboBox.setItems(typeList);
        typeComboBox.getSelectionModel().select(0);

        setHgrow(nameTextField, Priority.ALWAYS);
        setHgrow(valueTextField, Priority.ALWAYS);
        setHgrow(typeComboBox, Priority.ALWAYS);

        getChildren().addAll(nameTextField, typeComboBox, valueTextField);
        
    }

    public void setComboSelectionModel(SingleSelectionModel<String> sm)
    {
        sm = typeComboBox.getSelectionModel();

    }

    public SingleSelectionModel<String> getComboSelectionModel()
    {
        return typeComboBox.getSelectionModel();

    }
}