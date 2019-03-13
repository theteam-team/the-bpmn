package com.theteam.bpmn.design.dnode;

import java.util.UUID;

import com.theteam.snodes.SXML;

import javafx.beans.property.StringProperty;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SingleSelectionModel;

public class DServiceTaskNode extends DNode
{

    StringProperty restLinkProperty = new SimpleStringProperty();

    public SingleSelectionModel<String> serviceType;
    public SingleSelectionModel<String> soapFunc;

    public ObservableList<String> serviceTypeList = FXCollections.observableArrayList(
        "rest",
        "soap"
    );

    public ObservableList<String> soapFuncList = FXCollections.observableArrayList(
        "getHello",
        "getResponseWithName"
    );

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

            DProperty dRestLinkProperty = new DProperty("Rest Link", restLinkProperty);
            DComboBoxProperty dServiceTypeProperty = new DComboBoxProperty("Service Type", serviceType, serviceTypeList);
            DComboBoxProperty dSoapFuncProperty = new DComboBoxProperty("Soap Func", soapFunc, soapFuncList);

            allDProperties.add(dRestLinkProperty);
            allDProperties.add(dServiceTypeProperty);
            allDProperties.add(dSoapFuncProperty);

            String a = (String) dServiceTypeProperty.getComboSelectionModel().getSelectedItem();
            xmlWriter.setServiceTypeProperty(id.toString(), a);

            a = (String) dSoapFuncProperty.getComboSelectionModel().getSelectedItem();
            xmlWriter.setSoapFuncProperty(id.toString(), a);

            restLinkProperty.addListener(new ChangeListener<String>()
            {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue)
                {
                    xmlWriter.setRestLinkProperty(id.toString(), newValue);
                }
            });

            dServiceTypeProperty.getComboSelectionModel().selectedIndexProperty().addListener((Observable o) -> {
                String selectedItem = (String) dServiceTypeProperty.getComboSelectionModel().getSelectedItem();

                xmlWriter.setServiceTypeProperty(id.toString(), selectedItem);
                
            });

            dSoapFuncProperty.getComboSelectionModel().selectedIndexProperty().addListener((Observable o) -> {
                String selectedItem = (String) dSoapFuncProperty.getComboSelectionModel().getSelectedItem();

                xmlWriter.setSoapFuncProperty(id.toString(), selectedItem);
                
            });

            

        }
        

    }
}