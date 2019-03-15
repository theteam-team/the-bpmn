
package com.theteam.bpmn.design.dnode.dproperty;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleNode;

import javafx.beans.property.StringProperty;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;

/**
 * DTextProperty property presents property that have a text
 * Node properties are used by design ndoes (dnodes) to present properties to users
 */
public class DTextProperty extends DProperty
{
    JFXToggleNode toggleNode = null;
    JFXTextField textField = null;

    public DTextProperty(String labelText, StringProperty sp)
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