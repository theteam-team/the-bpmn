
package com.theteam.bpmn.design.dnode;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleNode;

import javafx.beans.property.StringProperty;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;

public class DProperty extends Dprop
{
    JFXToggleNode toggleNode = null;
    JFXTextField textField = null;

    public DProperty(String labelText, StringProperty sp)
    {
        toggleNode = new JFXToggleNode();
        toggleNode.setTextFill(Color.YELLOWGREEN);
        toggleNode.setText(labelText);

        textField = new JFXTextField();
        textField.textProperty().bindBidirectional(sp);

        getChildren().addAll(toggleNode, textField);

        setHgrow(textField, Priority.ALWAYS);
        
    }
}