package com.theteam.bpmn.design.controller;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

public class MainStageController {

    @FXML
    private VBox rootVBox;

    @FXML
    private MenuBar mainMenuBar;

    @FXML
    private Canvas drawCanvas;

    private ImageView selectedNode = null;
    private String selectedId = null;

    private Stage stage;
    private StringProperty title = new SimpleStringProperty();

    private GraphicsContext gc;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void prepareGraphicContext()
    {
        gc = drawCanvas.getGraphicsContext2D();
    }

    public void setupBinding(StageStyle stageStyle) {

        stage.setResizable(false);

        //title.bind(testText.textProperty());
        stage.titleProperty().bind(title);
        title.set("the-bpmn");
        stage.initStyle(stageStyle);
    }

    @FXML
    private void drawCanvasClicked(MouseEvent me)
    {
        double x = me.getX() - 40;
        double y = me.getY() - 40;

        if(selectedNode == null)
        {
            System.out.println("Choose a node");
            return;
        }

        Image i = new Image(getClass().getResource("/images/"+ selectedId + ".png").toString());

        gc.drawImage(i, x, y, 80, 80);

    }


    @FXML
    private void nodeClicked(MouseEvent me)
    {
        Image im;
       

        if(selectedNode != null)
        {
            im = new Image(getClass().getResource("/images/"+ selectedId + ".png").toString());
            selectedNode.setImage(im);
        }

        ImageView imView = ((ImageView) me.getSource());
        String id = imView.getId();

        selectedNode = imView;
        selectedId = id;

        im = new Image(getClass().getResource("/images/"+ selectedId + "_chosen.png").toString());
            selectedNode.setImage(im);

    }

}
