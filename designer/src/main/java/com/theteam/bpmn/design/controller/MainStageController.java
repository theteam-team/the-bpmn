package com.theteam.bpmn.design.controller;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

import com.jfoenix.controls.JFXTextField;

import com.theteam.bpmn.design.dnode.DNodeEventHandler;
import com.theteam.bpmn.design.loader.ImagesLoader;
import com.theteam.bpmn.design.loader.WSDLLoader;

/**
 * The orchestrator of the application
 * Start new stage to make bpmn projects
 * Loads what each tage needs before loading it
 * Control all other stages
 */
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

    @FXML
    private ToolBar toolBar;

    private double dragAnchorX;
    private double dragAnchorY;

    private Scene scene;
    private Stage stage;

    private StringProperty title = new SimpleStringProperty();

    public void setStage(Stage stage)
    {
        this.stage = stage;
    }

    public void setScene(Scene scene)
    {
        this.scene = scene;
    }

    public void setupBinding(StageStyle stageStyle)
    {

        stage.setResizable(false);

        //title.bind(testText.textProperty());
        stage.titleProperty().bind(title);
        title.set("the-bpmn");
        stage.initStyle(stageStyle);
    }

    @FXML
    public void createBPMNDiagram(ActionEvent ae) throws IOException
    {

        // WSDLLoader.loadWSDL();
        
        ImagesLoader.initializeImages();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
            .getResource("/fxml/bpmnStage.fxml"));  
        AnchorPane bpmnDrawArea = fxmlLoader.load();

        final BPMNStageController bpmnStagecontroller = fxmlLoader.getController();

        bpmnStagecontroller.setLogText(logText);
        bpmnStagecontroller.setToolBar(toolBar);
        bpmnStagecontroller.setTab(tab);
        
        bpmnStagecontroller.loadNodes();

        DNodeEventHandler.setBPMNController(bpmnStagecontroller);

        tab.setContent(bpmnDrawArea);
    }

    @FXML
    private void changeTheme(ActionEvent ae)
    {
       MenuItem me = (MenuItem) ae.getSource();

       scene.getStylesheets().clear();

       if(me.getText().equals("Dark"))
            scene.getStylesheets().addAll(getClass()
                .getResource("/css/dark-theme.css").toString());
       else
            scene.getStylesheets().addAll(getClass()
                .getResource("/css/light-theme.css").toString());
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
