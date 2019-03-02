package com.theteam.bpmn.design.controller;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;

public class MainStageController {

    @FXML
    private VBox rootVBox;

    @FXML
    private MenuBar mainMenuBar;

    @FXML
    private JFXTextField logText;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tab;

    private double dragAnchorX;
    private double dragAnchorY;

    private Stage stage;
    private StringProperty title = new SimpleStringProperty();

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setupBinding(StageStyle stageStyle) {

        stage.setResizable(false);

        //title.bind(testText.textProperty());
        stage.titleProperty().bind(title);
        title.set("the-bpmn");
        stage.initStyle(stageStyle);
    }

    @FXML
    public void createBPMNDiagram(ActionEvent ae) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
            .getResource("/fxml/bpmnStage.fxml"));
        AnchorPane bpmnDrawArea = fxmlLoader.load();

        final BPMNStageController bpmnStagecontroller = fxmlLoader.getController();

        tab.setContent(bpmnDrawArea);
    }

    @FXML
    public void windowPressed(MouseEvent me)
    {
        dragAnchorX = me.getScreenX() - stage.getX();
        dragAnchorY = me.getScreenY() - stage.getY();
    }

    @FXML
    private void windowDragged(MouseEvent me)
    {
        stage.setX(me.getScreenX() - dragAnchorX);
        stage.setY(me.getScreenY() - dragAnchorY);
    }

    @FXML
    public void toTrayClicked(MouseEvent me) { stage.setIconified(true); }

    @FXML
    public void minMaxClicked(MouseEvent me) { stage.setMaximized(!stage.isMaximized()); }

    @FXML
    public void exitClicked(MouseEvent me) { closeApplication(); }


    public void closeApplication() { stage.close(); }

}
