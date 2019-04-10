package com.theteam.bpmn.design.controller;

import com.theteam.bpmn.design.dnode.*;
import com.theteam.bpmn.design.dnode.event.DExternalEvent;
import com.theteam.bpmn.design.dnode.event.DTimerEvent;
import com.theteam.bpmn.design.io.Variable;
import com.theteam.snodes.SXML;

import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import com.jfoenix.controls.JFXTextField;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;

import javafx.fxml.FXML;

import javafx.scene.Node;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


/**
 * Control BPMN Stage Window (Nodes, Tools, properties, ...)
 */
public class BPMNStageController {

    @FXML
    public AnchorPane drawArea;

    @FXML
    private VBox gridNodes;

    @FXML
    public VBox propertiesView;
    
    @FXML
    public VBox variablesView;

    private ToolBar toolBar;

    private StringProperty logText = new SimpleStringProperty();

    public DNode selectedNode = null;
    public DNode firstNode = null;
    public DNode secondNode = null;

    SXML xmlWriter;

    Integer i = 0;

    public void loadNodes()
    {
        xmlWriter = new SXML();

        // ADD NODE

        DStartNode start = new DStartNode(xmlWriter, UUID.randomUUID(), false);
        start.setFitHeight(NodeConstants.nodeSize);
        start.setFitWidth(NodeConstants.nodeSize);
        
        DEndNode end = new DEndNode(xmlWriter, UUID.randomUUID(), false);
        end.setFitHeight(NodeConstants.nodeSize);
        end.setFitWidth(NodeConstants.nodeSize);
        
        DServiceTaskNode serviceTask = new DServiceTaskNode(xmlWriter, UUID.randomUUID(), false);
        serviceTask.setFitHeight(NodeConstants.nodeSize);
        serviceTask.setFitWidth(NodeConstants.nodeSize);

        DDBNode db = new DDBNode(xmlWriter, UUID.randomUUID(), false);
        db.setFitHeight(NodeConstants.nodeSize);
        db.setFitWidth(NodeConstants.nodeSize);

        DExternalEvent ext = new DExternalEvent(xmlWriter, UUID.randomUUID(), false);
        ext.setFitHeight(NodeConstants.nodeSize);
        ext.setFitWidth(NodeConstants.nodeSize);

        DTimerEvent timer = new DTimerEvent(xmlWriter, UUID.randomUUID(), false);
        timer.setFitHeight(NodeConstants.nodeSize);
        timer.setFitWidth(NodeConstants.nodeSize);

        DScriptNode script = new DScriptNode(xmlWriter, UUID.randomUUID(), false);
        script.setFitHeight(NodeConstants.nodeSize);
        script.setFitWidth(NodeConstants.nodeSize);

        DTestNode test = new DTestNode(xmlWriter, UUID.randomUUID(), false);
        test.setFitHeight(NodeConstants.nodeSize);
        test.setFitWidth(NodeConstants.nodeSize);

        gridNodes.getChildren().add(start);
        gridNodes.getChildren().add(end);
        gridNodes.getChildren().add(serviceTask);
        gridNodes.getChildren().add(db);
        gridNodes.getChildren().add(ext);
        gridNodes.getChildren().add(timer);
        gridNodes.getChildren().add(script);
        gridNodes.getChildren().add(test);
        

        MaterialIconView saveIcon = new MaterialIconView();
        saveIcon.setGlyphName("SAVE");
        saveIcon.setSize("25");
        saveIcon.setOnMouseClicked(saveButtonHandler);

        MaterialIconView addIcon = new MaterialIconView(MaterialIcon.ADD_CIRCLE);
        addIcon.setSize("25");
        addIcon.setOnMouseClicked(addVariableButtonHandler);

        toolBar.getItems().addAll(saveIcon, addIcon);

    }

    public void setLogText(JFXTextField logText)
    {
        logText.textProperty().bind(this.logText);
    }

    public void setToolBar(ToolBar toolBar)
    {
        this.toolBar = toolBar;
    }


    @FXML
    private void drawAreaClicked(MouseEvent me)
    {

        if(selectedNode == null)
        {
            logText.set("Select a Node");
            return;
        }

        PickResult pe = me.getPickResult();
        Node n = pe.getIntersectedNode();

        if(n.getId().equals("drawArea"))
        {
            switch (selectedNode.getDType())
            {

                // ADD NODE
                
                case "start":
                    createDrawNode(new DStartNode(xmlWriter, UUID.randomUUID(), true), me);
                    logText.set("Start Node Created");
                    break;
    
                case "end":
                    createDrawNode(new DEndNode(xmlWriter, UUID.randomUUID(), true), me);
                    logText.set("End Node Created");
                    break;
    
                case "service_task":
                    createDrawNode(new DServiceTaskNode(xmlWriter, UUID.randomUUID(), true), me);
                    logText.set("Service Task Node Created");
                    break;

                case "db":
                    createDrawNode(new DDBNode(xmlWriter, UUID.randomUUID(), true), me);
                    logText.set("Database Node Created");
                    break;

                case "external":
                    createDrawNode(new DExternalEvent(xmlWriter, UUID.randomUUID(), true), me);
                    logText.set("External Node Created");
                    break;
    
                case "timer":
                    createDrawNode(new DTimerEvent(xmlWriter, UUID.randomUUID(), true), me);
                    logText.set("Timer Node Created");
                    break;
    
                case "script":
                    createDrawNode(new DScriptNode(xmlWriter, UUID.randomUUID(), true), me);
                    logText.set("Script Task Node Created");
                    break;

                case "triangle":
                    createDrawNode(new DTestNode(xmlWriter, UUID.randomUUID(), true), me);
                    logText.set("Test Node Created");
                    break;
            
                default:
                    break;
            }

        }

    }

    private void createDrawNode(DNode node, MouseEvent me)
    {
        node.setFitHeight(NodeConstants.nodeSize);
        node.setFitWidth(NodeConstants.nodeSize);

        //node.setId(UUID.randomUUID().toString());

        drawArea.getChildren().add(node);

        node.setX(me.getX()-node.getFitWidth()/2);
        node.setY(me.getY()-node.getFitHeight()/2);

    }

    public void createLine(DNode nodeFrom, DNode nodeTo)
    {
        DLine line = new DLine(nodeFrom, nodeTo);

        nodeFrom.getStartLines().add(line);
        nodeTo.getEndLines().add(line);

        logText.set("Line Created");
        drawArea.getChildren().add(line);
    }

    public EventHandler<MouseEvent> saveButtonHandler =  new EventHandler<MouseEvent>()
    {
        @Override
        public void handle(MouseEvent me)
        {
            try
            {
                xmlWriter.saveToXML("../xml/nodeXML.xml");
                logText.set("Saved To ../xml/nodeXML.xml");
            } catch(Exception e)
            {
                System.out.println(e);
            }
        }
    };

    List<StringProperty> n = new LinkedList<>();
    List<StringProperty> v = new LinkedList<>();
    public EventHandler<MouseEvent> addVariableButtonHandler =  new EventHandler<MouseEvent>()
    {
        @Override
        public void handle(MouseEvent me)
        {
            StringProperty nameProperty = new SimpleStringProperty();
            n.add(nameProperty);
            nameProperty.set("var" + i.toString());
            i += 1;

            StringProperty valueProperty = new SimpleStringProperty();
            v.add(valueProperty);
            valueProperty.set("null");

            //SingleSelectionModel<String> type;

            ObservableList<String> typeList = FXCollections.observableArrayList(
                "string",
                "int",
                "float",
                "bool"

            );

            Variable var = new Variable(nameProperty,
                                        typeList,
                                        valueProperty);

            String id = UUID.randomUUID().toString();

            var.setId(id);
            
            xmlWriter.addVariable(id, nameProperty.get(), (String) var.getComboSelectionModel().getSelectedItem(), valueProperty.get());

            nameProperty.addListener(new ChangeListener<String>()
            {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue)
                {
                    xmlWriter.setVariableName(var.getId(), newValue);
                }
            });

            valueProperty.addListener(new ChangeListener<String>()
            {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue)
                {
                    xmlWriter.setVariableValue(var.getId(), newValue);
                }
            });
            
            var.getComboSelectionModel().selectedIndexProperty().addListener((Observable o) ->
            {
                String selectedItem = (String) var.getComboSelectionModel().getSelectedItem();
                xmlWriter.setVariableType(var.getId(), selectedItem);
                
            });

            variablesView.getChildren().add(var);

        }
    };
}
