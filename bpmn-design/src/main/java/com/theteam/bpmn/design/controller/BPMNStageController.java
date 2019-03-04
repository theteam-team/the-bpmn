package com.theteam.bpmn.design.controller;

import com.theteam.bpmn.design.dnode.*;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.UUID;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

public class BPMNStageController {

    @FXML
    public AnchorPane drawArea;

    @FXML
    private GridPane gridNodes;

    private StringProperty logText = new SimpleStringProperty();

    public DNode selectedNode = null;
    public DNode firstNode = null;
    public DNode secondNode = null;

    public void loadNodes()
    {
        DStartNode start = new DStartNode();
        start.setFitHeight(50);
        start.setFitWidth(50);
        
        DEndNode end = new DEndNode();
        end.setFitHeight(50);
        end.setFitWidth(50);
        
        DServiceTaskNode serviceTask = new DServiceTaskNode();
        serviceTask.setFitHeight(50);
        serviceTask.setFitWidth(50);

        gridNodes.add(start, 0, 0);
        gridNodes.add(end, 1, 0);
        gridNodes.add(serviceTask, 0, 1);
    }

    public void setLogText(JFXTextField logText)
    {
        logText.textProperty().bind(this.logText);
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
            switch (selectedNode.getDType()) {

                case "start":
                    createDrawNode(new DStartNode(), me);
                    break;
    
                case "end":
                    createDrawNode(new DEndNode(), me);
                    break;
    
                case "service_task":
                    createDrawNode(new DServiceTaskNode(), me);
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

        node.setId(UUID.randomUUID().toString());
        node.setDrawNode(true);

        drawArea.getChildren().add(node);

        node.setX(me.getX()-node.getFitWidth()/2);
        node.setY(me.getY()-node.getFitHeight()/2);

    }

    public void createLine(DNode nodeFrom, DNode nodeTo)
    {
        DLine line = new DLine(nodeFrom, nodeTo);

        drawArea.getChildren().add(line);
    }

    
}
