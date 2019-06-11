package com.theteam.bpmn.design;

import com.theteam.bpmn.design.controller.MainStageController;
import com.theteam.bpmn.design.db.DBData;

import java.io.IOException;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 * The entry point for the application
 * Setup and load main stage controller settings
 * Give Control to the main stage Controller
 * Controls Window properties
 */
public class App extends Application
{
    public static void main(String[] args)
    {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException
    {
        final StageStyle stageStyle = StageStyle.UNDECORATED;

        FXMLLoader fxmlLoader = new FXMLLoader(App.class
            .getResource("/fxml/mainStage.fxml"));
        VBox rootGroup = fxmlLoader.load();
        //VBox rootGroup = new VBox();

        final MainStageController mainStagecontroller = fxmlLoader.getController();
        mainStagecontroller.setStage(stage);
        mainStagecontroller.setupBinding(stageStyle);

        Scene scene = new Scene(rootGroup, 1200, 800);
        mainStagecontroller.setScene(scene);
        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().addAll(App.class
            .getResource("/css/dark-theme.css").toString());

        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);

        stage.setScene(scene);
        stage.setOnCloseRequest(we -> System.out.println("Stage is closing"));
        stage.show();

        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 4);
        

    }

}