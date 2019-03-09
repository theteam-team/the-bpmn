package com.theteam.bpmn.design.dnode;

import java.util.UUID;

import com.theteam.snodes.SXML;

import javafx.beans.property.StringProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class DServiceTaskNode extends DNode
{

    StringProperty restLinkProperty = new SimpleStringProperty();

    public DServiceTaskNode(SXML xmlWriter, UUID id, Boolean drawNode)
    {
        super(NodeImages.nodeImages.get("service_task"), id.toString());
        this.type = "service_task";

        this.drawNode = drawNode;

        setId(id.toString());
        idProperty().set(id.toString());

        if(drawNode)
        {
            this.xmlWriter = xmlWriter;
        
            xmlWriter.addTaskNode(id.toString());

            restLinkProperty.addListener(new ChangeListener<String>()
            {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue)
                {
                    xmlWriter.setRestProperty(id.toString(), newValue);
                }
            });

            DProperty dRestLinkProperty = new DProperty("Rest Link", restLinkProperty);
            allDProperties.add(dRestLinkProperty);

        }
        

    }
}