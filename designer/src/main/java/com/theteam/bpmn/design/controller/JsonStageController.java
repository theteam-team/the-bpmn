package com.theteam.bpmn.design.controller;

import javafx.event.EventHandler;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleNode;
import com.theteam.bpmn.design.db.DBData;
import com.theteam.bpmn.design.db.SQLEditor;
import com.theteam.snodes.SXML;

import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import javafx.beans.property.SimpleStringProperty;

import javafx.beans.Observable;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Control BPMN Stage Window (Nodes, Tools, properties, ...)
 */
public class JsonStageController {

    @FXML
    public AnchorPane dbPane;

    private Stage stage;
    private JFXTextField txtField;

    private StringProperty title = new SimpleStringProperty();

    private double dragAnchorX;
    private double dragAnchorY;

    JFXButton dnButton;

    String nodeId;
    SXML xmlWriter;

    private ArrayList<Row> rows = new ArrayList<>();
    VBox jbox;


    @FXML
    private void chooseDBEvent(ActionEvent ae)
    {

        xmlWriter.resetJsonData(nodeId);

        Button b = (Button) ae.getSource();

        if(b.getText().equals("MAKE"))
        {
            loadJsonEditor();
        }

    }

    private void loadJsonEditor()
    {
        dbPane.getChildren().clear();

        stage.setHeight(stage.getHeight() + 100);

        MaterialIconView btnIcon = new MaterialIconView(MaterialIcon.NAVIGATE_BEFORE);
        btnIcon.setSize("30");
        btnIcon.setOnMouseClicked(btnIconHandler);
    
        MaterialIconView btnIcon1 = new MaterialIconView(MaterialIcon.NAVIGATE_NEXT);
        btnIcon1.setSize("30");
        btnIcon1.setOnMouseClicked(btnIconHandler1);
    
        HBox iconBox = new HBox(30, btnIcon, btnIcon1);
        iconBox.setAlignment(Pos.CENTER);


        Row row = new Row();
        rows.add(row);

        jbox = new VBox(30, row);
        jbox.setAlignment(Pos.CENTER);

        JFXButton dnButton = new JFXButton("DONE");
        dnButton.setOnMouseClicked(dnBtnHandler);

        VBox box = new VBox(50, iconBox, jbox, dnButton);
        box.setAlignment(Pos.CENTER);
    
        dbPane.getChildren().addAll(box);
        dbPane.setTopAnchor(box, 0.0);
        dbPane.setLeftAnchor(box, 0.0);
        dbPane.setRightAnchor(box, 0.0);
        dbPane.setBottomAnchor(box, 0.0);
    }

    public EventHandler<MouseEvent> btnIconHandler1 =  new EventHandler<MouseEvent>()
    {
        @Override
        public void handle(MouseEvent me)
        {
            stage.setHeight(stage.getHeight() + 100);
            Row row = new Row();
            rows.add(row);

            jbox.getChildren().add(row);
        }
    };

    public EventHandler<MouseEvent> btnIconHandler =  new EventHandler<MouseEvent>()
    {
        @Override
        public void handle(MouseEvent me)
        {
            if(rows.size() <= 1)
                return;

            jbox.getChildren().remove(rows.get(rows.size()-1));
            rows.remove(rows.size()-1);
            stage.setHeight(stage.getHeight() - 100);
        }
    };

    public EventHandler<MouseEvent> dnBtnHandler =  new EventHandler<MouseEvent>()
    {
        @Override
        public void handle(MouseEvent me)
        {
            for (Row var : rows) {
                String[] data = var.getRowData();
                xmlWriter.addJsonData(nodeId,
                                    UUID.randomUUID().toString(),
                                    data[0], data[1]);
            }

            stage.close();
        }
    };


    public class Row extends HBox
    {
        JFXTextField text1 = new JFXTextField();
        JFXTextField text2 = new JFXTextField();

        public Row()
        {
            super(10);

            text1.setText("Name");
            text2.setText("Value");

            getChildren().addAll(text1, text2);

        }

        public String[] getRowData()
        {
            String a[]= {text1.getText(), text2.getText()};
            return a;
        }
    }

    public void setStage(Stage stage)
    {
        this.stage = stage;
    }


    public void setTextField(JFXTextField txtField)
    {
        this.txtField = txtField;
    }

    public void setNodeId(String id)
    {
        this.nodeId = id;
    }

    public void setXMLWriter(SXML xmlWriter)
    {
        this.xmlWriter = xmlWriter;
    }

    public void setupBinding(StageStyle stageStyle)
    {

        stage.setResizable(false);

        //title.bind(testText.textProperty());
        stage.titleProperty().bind(title);
        title.set("DB");
        stage.initStyle(stageStyle);
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