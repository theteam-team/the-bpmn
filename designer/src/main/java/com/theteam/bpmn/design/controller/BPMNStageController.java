package com.theteam.bpmn.design.controller;

import com.theteam.bpmn.design.dnode.*;
import com.theteam.snodes.SXML;

import de.jensd.fx.glyphs.materialicons.MaterialIconView;

import java.util.UUID;

import com.jfoenix.controls.JFXTextField;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javafx.event.EventHandler;

import javafx.fxml.FXML;

import javafx.scene.Node;
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
    private GridPane gridNodes;

    @FXML
    public VBox propertiesView;

    private ToolBar toolBar;

    private StringProperty logText = new SimpleStringProperty();

    public DNode selectedNode = null;
    public DNode firstNode = null;
    public DNode secondNode = null;

    SXML xmlWriter;

    public void loadNodes()
    {
        xmlWriter = new SXML();

        DStartNode start = new DStartNode(xmlWriter, UUID.randomUUID(), false);
        start.setFitHeight(50);
        start.setFitWidth(50);
        
        DEndNode end = new DEndNode(xmlWriter, UUID.randomUUID(), false);
        end.setFitHeight(50);
        end.setFitWidth(50);
        
        DServiceTaskNode serviceTask = new DServiceTaskNode(xmlWriter, UUID.randomUUID(), false);
        serviceTask.setFitHeight(50);
        serviceTask.setFitWidth(50);

        gridNodes.add(start, 0, 0);
        gridNodes.add(end, 1, 0);
        gridNodes.add(serviceTask, 0, 1);

        MaterialIconView saveIcon = new MaterialIconView();
        saveIcon.setGlyphName("SAVE");
        saveIcon.setSize("25");

        saveIcon.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t)
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
        });

        toolBar.getItems().add(saveIcon);

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
            
                default:
                    break;
            }

        }

    }

    private void createDrawNode(DNode node, MouseEvent me)
    {
        node.setFitHeight(50);
        node.setFitWidth(50);

        //node.setId(UUID.randomUUID().toString());

        drawArea.getChildren().add(node);

        node.setX(me.getX()-node.getFitWidth()/2);
        node.setY(me.getY()-node.getFitHeight()/2);

    }

    public void createLine(DNode nodeFrom, DNode nodeTo)
    {
        DLine line = new DLine(nodeFrom, nodeTo);

        logText.set("Line Created");
        drawArea.getChildren().add(line);
    }

    
}
