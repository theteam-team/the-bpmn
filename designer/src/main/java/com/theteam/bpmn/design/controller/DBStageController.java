package com.theteam.bpmn.design.controller;

import javafx.event.EventHandler;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleNode;
import com.theteam.bpmn.design.db.SQLEditor;

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
public class DBStageController {
    @FXML
    public AnchorPane dbPane;

    private Stage stage;
    private Scene scene;

    private StringProperty title = new SimpleStringProperty();
    private JFXTextField txtField;

    private ArrayList<Statment> statments = new ArrayList<>();

    private double dragAnchorX;
    private double dragAnchorY;

    SQLEditor sqlEditor;
    JFXButton dnButton;
    VBox dbBox;


    @FXML
    private void chooseDBEvent(ActionEvent ae)
    {

        Button b = (Button) ae.getSource();

        if(b.getText().equals("SQL"))
        {
            loadSQLEditor();
        }
        else
        {
            stage.close();
            //loadEventEditor();
        }

    }

    private void loadEventEditor()
    {

    }

    private void loadSQLEditor()
    {

        dbPane.getChildren().clear();

        dbBox = new VBox(30);
        dbBox.setAlignment(Pos.CENTER);

        MaterialIconView btnIcon = new MaterialIconView(MaterialIcon.NAVIGATE_BEFORE);
        btnIcon.setSize("30");
        btnIcon.setOnMouseClicked(btnIconHandler);

        MaterialIconView btnIcon1 = new MaterialIconView(MaterialIcon.NAVIGATE_NEXT);
        btnIcon1.setSize("30");
        btnIcon1.setOnMouseClicked(btnIconHandler1);

        HBox iconBox = new HBox(30, btnIcon, btnIcon1);
        iconBox.setAlignment(Pos.CENTER);
        
        dnButton = new JFXButton("DONE");
        dnButton.setOnMouseClicked(dnBtnHandler);
        dnButton.setVisible(false);

        VBox box = new VBox(50, iconBox, dbBox, dnButton);

        box.setVgrow(dbBox, Priority.ALWAYS);
        box.setVgrow(dnButton, Priority.ALWAYS);

        box.setMargin(dnButton, new Insets(10, 0, 10, 0));

        box.setAlignment(Pos.TOP_CENTER);

        dbPane.getChildren().add(box);
        dbPane.setTopAnchor(box, 0.0);
        dbPane.setLeftAnchor(box, 0.0);
        dbPane.setRightAnchor(box, 0.0);
        dbPane.setBottomAnchor(box, 0.0);

        sqlEditor = new SQLEditor();

        ObservableList<String> obsList = FXCollections.observableArrayList(
            sqlEditor.getStrState()
        );

        Statment select = new Statment(obsList);
        statments.add(select);

        dbBox.getChildren().add(select);

        select.reverseVisibility();
        dnButton.setVisible(true);

        stage.setHeight(stage.getHeight() + 100);

        ObservableList<String> obsList1 = FXCollections.observableArrayList(
            sqlEditor.getStrNextStates()
        );

        Statment st = new Statment(obsList1);
        statments.add(st);
        dbBox.getChildren().add(st);

    }

    public EventHandler<MouseEvent> dnBtnHandler =  new EventHandler<MouseEvent>()
    {
        @Override
        public void handle(MouseEvent me)
        {
            String s = "";

            for (Statment st : statments)
            {
                s += st.getStrStatment();
                s += " ";
            }

            s += ";";

            System.out.println(s);
            txtField.setText(s);
            stage.close();
        }
    };

    public EventHandler<MouseEvent> btnIconHandler =  new EventHandler<MouseEvent>()
    {
        @Override
        public void handle(MouseEvent me)
        {
            showPrevStatment();
        }

    };
    public EventHandler<MouseEvent> btnIconHandler1 =  new EventHandler<MouseEvent>()
    {
        @Override
        public void handle(MouseEvent me)
        {
            showNextStatment();
        }
    };

    public void showPrevStatment()
    {
        System.out.println();

        System.out.println(sqlEditor.getStrState());

        if(statments.size() <= 2)
            return;


        stage.setHeight(stage.getHeight() - 100);

        Statment last = statments.get(statments.size()-1);
        statments.remove(last);
        dbBox.getChildren().remove(last);
        
        last = statments.get(statments.size()-1);
        statments.remove(last);
        dbBox.getChildren().remove(last);

        sqlEditor.goPrevState();

        ObservableList<String> obsList = FXCollections.observableArrayList(
            sqlEditor.getStrNextStates()
        );

        Statment st = new Statment(obsList);
        statments.add(st);
        dbBox.getChildren().add(st);

        System.out.println(sqlEditor.getStrState());
        System.out.println();
    }

    public void showNextStatment()
    {

        System.out.println();
        System.out.println(sqlEditor.getStrState());

        Statment last = statments.get(statments.size()-1);
        last.reverseVisibility();

        sqlEditor.setNextState(last.getState());

        ObservableList<String> obsList = FXCollections.observableArrayList(
            sqlEditor.getStrNextStates()
        );
        
        System.out.println(sqlEditor.getStrState());
        System.out.println();

        if(sqlEditor.ended())
            return;

        
        Statment st = new Statment(obsList);
        statments.add(st);

        stage.setHeight(stage.getHeight() + 100);
        dbBox.getChildren().add(st);

    }

    public void setStage(Stage stage)
    {
        this.stage = stage;
    }

    public void setScene(Scene scene)
    {
        this.scene = scene;
    }


    public void setTextField(JFXTextField txtField)
    {
        this.txtField = txtField;
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


    public class Statment extends VBox
    {
        JFXComboBox<String> cb = new JFXComboBox<String>();

        JFXTextField text = new JFXTextField();
        JFXToggleNode toggleNode = new JFXToggleNode();

        public Statment()
        {

        }

        public Statment(ObservableList<String> list)
        {

            super(10);

            cb.setItems(list);
            cb.getSelectionModel().select(0);
            toggleNode.setTextFill(Color.YELLOWGREEN);

            if(cb.getSelectionModel().getSelectedItem().equals("END"))
            {
                getChildren().clear();
                getChildren().addAll(cb);
            }

            else
            {
                getChildren().clear();
                getChildren().addAll(cb, text);
            }

            cb.getSelectionModel().selectedIndexProperty().addListener((Observable o) -> {

                String selectedItem = (String) cb.getSelectionModel().getSelectedItem();
                
                if(selectedItem.equals("END"))
                {
                    getChildren().clear();
                    getChildren().addAll(cb);
                }

                else
                {
                    getChildren().clear();
                    getChildren().addAll(cb, text);
                }
            });

        }

        public void reverseVisibility()
        {
            if(cb.isVisible())
            {

                cb.setVisible(false);

                toggleNode.setText(cb.getSelectionModel().getSelectedItem());

                if(cb.getSelectionModel().getSelectedItem().equals("END"))
                {
                    getChildren().clear();
                    getChildren().addAll(toggleNode);
                }

                else
                {
                    getChildren().clear();
                    getChildren().addAll(toggleNode, text);
                }

            }
            else
            {
                cb.setVisible(true);

                if(cb.getSelectionModel().getSelectedItem().equals("END"))
                {
                    getChildren().clear();
                    getChildren().addAll(cb);
                }

                else
                {
                    getChildren().clear();
                    getChildren().addAll(cb, text);
                }

            }
        }

        public String getStrStatment()
        {
            String s = cb.getSelectionModel().getSelectedItem();

            if(s.equals("END"))
                return "";

            return  s +
                    " " +
                    text.getText();
        }

        public String getState()
        {
            return cb.getSelectionModel().getSelectedItem();
        }

    }
}