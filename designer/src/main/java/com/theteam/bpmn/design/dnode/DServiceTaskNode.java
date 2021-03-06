package com.theteam.bpmn.design.dnode;

import java.util.UUID;

import com.theteam.bpmn.design.dnode.dproperty.DButtonTaskProperty;
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
 * DServiceTaskNode ** Service Task **
 * All nodes are ImageView Nodes from the javafx library
 * So all dnodes can be rendered using javafx application
 * All dnodes are represented by images
 */
public class DServiceTaskNode extends DNode
{

    StringProperty inputProperty = new SimpleStringProperty();
    StringProperty outputProperty = new SimpleStringProperty();

    StringProperty restLinkProperty = new SimpleStringProperty();

    StringProperty messageInProperty = new SimpleStringProperty();
    StringProperty messageOutProperty = new SimpleStringProperty();

    StringProperty taskNameProperty = new SimpleStringProperty();
    
    StringProperty taskDataProperty = new SimpleStringProperty();

    public SingleSelectionModel<String> serviceType;
    public SingleSelectionModel<String> soapFunc;
    
    public SingleSelectionModel<String> taskType;
    /*
    public ObservableList<String> serviceTypeList = FXCollections.observableArrayList(
        "rest",
        "soap"
    );
    */
    
    public ObservableList<String> serviceTypeList = FXCollections.observableArrayList(
        "get",
        "post",
        "put",
        "delete"
    );
    

    public ObservableList<String> taskTypeList = FXCollections.observableArrayList(
        "externalTask",
        "userTask",
        "APITask",
        "message"
    );

    public ObservableList<String> soapFuncList = FXCollections.observableArrayList(
        "getHello",
        "getResponseWithName"
    );

    public DServiceTaskNode(SXML xmlWriter, UUID id, Boolean drawNode)
    {
        super(ImagesLoader.nodeImages.get("service_task"), id.toString());
        this.type = "service_task";

        this.drawNode = drawNode;

        setId(id.toString());
        idProperty().set(id.toString());

        if(drawNode)
        {
            this.xmlWriter = xmlWriter;
        
            xmlWriter.addTaskNode(id.toString());

            DTextProperty dInputProperty = new DTextProperty("Input", inputProperty);
            DTextProperty dOutputProperty = new DTextProperty("Output", outputProperty);

            DTextProperty dMessageInProperty = new DTextProperty("message", messageInProperty);
            DTextProperty dMessageOutProperty = new DTextProperty("message", messageOutProperty);

            DTextProperty dRestLinkProperty = new DTextProperty("API Link", restLinkProperty);
            DTextProperty dTaskNameProperty = new DTextProperty("Task Name", taskNameProperty);
            
            DComboBoxProperty dServiceTypeProperty = new DComboBoxProperty("HTTP Method", serviceType, serviceTypeList);
            DComboBoxProperty dTaskTypeProperty = new DComboBoxProperty("Task Type", taskType, taskTypeList);

            DButtonTaskProperty DTaskDataProperty = new DButtonTaskProperty("Task Data", taskDataProperty, "taskStage", id.toString(), xmlWriter, dTaskTypeProperty);

            DComboBoxProperty dSoapFuncProperty = new DComboBoxProperty("Soap Func", soapFunc, soapFuncList);

            allDProperties.add(dInputProperty);
            allDProperties.add(dOutputProperty);

            allDProperties.add(dMessageInProperty);
            allDProperties.add(dMessageOutProperty);

            allDProperties.add(dTaskTypeProperty);
            allDProperties.add(dTaskNameProperty);
            
            allDProperties.add(DTaskDataProperty);

            allDProperties.add(dRestLinkProperty);
            allDProperties.add(dServiceTypeProperty);
            //allDProperties.add(dSoapFuncProperty);

            String a = (String) dServiceTypeProperty.getComboSelectionModel().getSelectedItem();
            xmlWriter.setServiceTypeProperty(id.toString(), a);

            a = (String) dSoapFuncProperty.getComboSelectionModel().getSelectedItem();
            xmlWriter.setSoapFuncProperty(id.toString(), a);

            a = (String) dTaskTypeProperty.getComboSelectionModel().getSelectedItem();
            xmlWriter.setTaskTypeProperty(id.toString(), a);

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

            restLinkProperty.addListener(new ChangeListener<String>()
            {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue)
                {
                    xmlWriter.setRestLinkProperty(id.toString(), newValue);
                }
            });

            messageInProperty.addListener(new ChangeListener<String>()
            {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue)
                {
                    xmlWriter.setTaskMessageInProprty(id.toString(), newValue);
                }
            });

            messageOutProperty.addListener(new ChangeListener<String>()
            {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue)
                {
                    xmlWriter.setTaskMessageOutProprty(id.toString(), newValue);
                }
            });

            taskNameProperty.addListener(new ChangeListener<String>()
            {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue)
                {
                    xmlWriter.setTaskNameProperty(id.toString(), newValue);
                }
            });

            dServiceTypeProperty.getComboSelectionModel().selectedIndexProperty().addListener((Observable o) -> {
                String selectedItem = (String) dServiceTypeProperty.getComboSelectionModel().getSelectedItem();

                xmlWriter.setServiceTypeProperty(id.toString(), selectedItem);
                
            });

            dTaskTypeProperty.getComboSelectionModel().selectedIndexProperty().addListener((Observable o) -> {
                String selectedItem = (String) dTaskTypeProperty.getComboSelectionModel().getSelectedItem();

                xmlWriter.setTaskTypeProperty(id.toString(), selectedItem);
                
            });

            dSoapFuncProperty.getComboSelectionModel().selectedIndexProperty().addListener((Observable o) -> {
                String selectedItem = (String) dSoapFuncProperty.getComboSelectionModel().getSelectedItem();

                xmlWriter.setSoapFuncProperty(id.toString(), selectedItem);
                
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