
package com.theteam.bpmn.design.dnode.dproperty;

import java.io.IOException;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleNode;
import com.theteam.bpmn.design.App;
import com.theteam.bpmn.design.controller.DBStageController;
import com.theteam.bpmn.design.controller.JsonStageController;
import com.theteam.snodes.SXML;

import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
 * DTextProperty property presents property that have a text
 * Node properties are used by design ndoes (dnodes) to present properties to users
 */
public class DButtonJsonProperty extends DProperty
{
    JFXToggleNode toggleNode = null;
    JFXTextField textField = null;

    String stageName;
    String nodeId;
    SXML xmlWriter;

    public DButtonJsonProperty(String labelText, StringProperty sp, String stageName, String id, SXML xmlWriter)
    {

        this.stageName = stageName;
        this.nodeId = id;
        this.xmlWriter =xmlWriter;

        toggleNode = new JFXToggleNode();
        toggleNode.setTextFill(Color.YELLOWGREEN);
        toggleNode.setText(labelText);

        textField = new JFXTextField();
        textField.textProperty().bindBidirectional(sp);

        MaterialIconView btnIcon = new MaterialIconView(MaterialIcon.REORDER);
        btnIcon.setSize("25");
        btnIcon.setOnMouseClicked(btnHandler);

        getChildren().addAll(toggleNode, btnIcon);

        setHgrow(textField, Priority.ALWAYS);

    }

    public EventHandler<MouseEvent> btnHandler =  new EventHandler<MouseEvent>()
    {
        @Override
        public void handle(MouseEvent me)
        {
            try { makeStege(textField); }
            catch(IOException ioe) { System.out.println(ioe); }
        }
    };

    void makeStege(JFXTextField textField) throws IOException
    {
        final StageStyle stageStyle = StageStyle.UNDECORATED;

            Stage jsonStage = new Stage(StageStyle.UNDECORATED);

            FXMLLoader fxmlLoader = new FXMLLoader(App.class
            .getResource("/fxml/" + stageName + ".fxml"));

            VBox rootGroup = fxmlLoader.load();

            final JsonStageController jsonStageController = fxmlLoader.getController();

            jsonStageController.setStage(jsonStage);
            
            jsonStageController.setupBinding(stageStyle);
            jsonStageController.setTextField(textField);
            jsonStageController.setNodeId(nodeId);
            jsonStageController.setXMLWriter(xmlWriter);

            Scene scene = new Scene(rootGroup, 450, 250);
            jsonStage.setScene(scene);
            scene.setFill(Color.TRANSPARENT);
            scene.getStylesheets().addAll(App.class
                .getResource("/css/dark-theme.css").toString());


            //scene.setFill(Color.TRANSPARENT);
            jsonStage.initStyle(StageStyle.TRANSPARENT);

            jsonStage.setOnCloseRequest(we -> System.out.println("DBStage is closing"));
            jsonStage.show();

            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            jsonStage.setX((primScreenBounds.getWidth() - jsonStage.getWidth()) / 2);
            jsonStage.setY((primScreenBounds.getHeight() - jsonStage.getHeight()) / 3 + 100);
    }
}