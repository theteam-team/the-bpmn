package com.theteam.bpmn.design.controller;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
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
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

public class BPMNStageController {


    private ImageView selectedNode = null;
    private String selectedId = null;

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

        //gc.drawImage(i, x, y, 80, 80);

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

    /*
    byNode.setOnMousePressed(new EventHandler<MouseEvent>() {
  @Override public void handle(MouseEvent mouseEvent) {
    // record a delta distance for the drag and drop operation.
    dragDelta.x = stage.getX() - mouseEvent.getScreenX();
    dragDelta.y = stage.getY() - mouseEvent.getScreenY();
    byNode.setCursor(Cursor.MOVE);
  }
});
byNode.setOnMouseReleased(new EventHandler<MouseEvent>() {
  @Override public void handle(MouseEvent mouseEvent) {
    byNode.setCursor(Cursor.HAND);
  }
});
byNode.setOnMouseDragged(new EventHandler<MouseEvent>() {
  @Override public void handle(MouseEvent mouseEvent) {
    stage.setX(mouseEvent.getScreenX() + dragDelta.x);
    stage.setY(mouseEvent.getScreenY() + dragDelta.y);
  }
});
byNode.setOnMouseEntered(new EventHandler<MouseEvent>() {
  @Override public void handle(MouseEvent mouseEvent) {
    if (!mouseEvent.isPrimaryButtonDown()) {
      byNode.setCursor(Cursor.HAND);
    }
  }
});
byNode.setOnMouseExited(new EventHandler<MouseEvent>() {
  @Override public void handle(MouseEvent mouseEvent) {
    if (!mouseEvent.isPrimaryButtonDown()) {
      byNode.setCursor(Cursor.DEFAULT);
    }
  }
});

    */

    /*
        Group root = new Group();

        // bending curve
        Rectangle srcRect1 = new Rectangle(100,100,50,50);
        Rectangle dstRect1 = new Rectangle(300,300,50,50);

        CubicCurve curve1 = new CubicCurve( 125, 150, 125, 225, 325, 225, 325, 300);
        curve1.setStroke(Color.BLACK);
        curve1.setStrokeWidth(1);
        curve1.setFill( null);

        double size=Math.max(curve1.getBoundsInLocal().getWidth(),
                            curve1.getBoundsInLocal().getHeight());
        double scale=size/4d;

        Point2D ori=eval(curve1,0);
        Point2D tan=evalDt(curve1,0).normalize().multiply(scale);
        Path arrowIni=new Path();
        arrowIni.getElements().add(new MoveTo(ori.getX()+0.2*tan.getX()-0.2*tan.getY(),
                                            ori.getY()+0.2*tan.getY()+0.2*tan.getX()));
        arrowIni.getElements().add(new LineTo(ori.getX(), ori.getY()));
        arrowIni.getElements().add(new LineTo(ori.getX()+0.2*tan.getX()+0.2*tan.getY(),
                                            ori.getY()+0.2*tan.getY()-0.2*tan.getX()));

        ori=eval(curve1,1);
        tan=evalDt(curve1,1).normalize().multiply(scale);
        Path arrowEnd=new Path();
        arrowEnd.getElements().add(new MoveTo(ori.getX()-0.2*tan.getX()-0.2*tan.getY(),
                                            ori.getY()-0.2*tan.getY()+0.2*tan.getX()));
        arrowEnd.getElements().add(new LineTo(ori.getX(), ori.getY()));
        arrowEnd.getElements().add(new LineTo(ori.getX()-0.2*tan.getX()+0.2*tan.getY(),
                                            ori.getY()-0.2*tan.getY()-0.2*tan.getX()));

        root.getChildren().addAll(srcRect1, dstRect1, curve1, arrowIni, arrowEnd);

        stage.setScene(new Scene(root, 800, 600));
        stage.show();
        */


    /**
     * Evaluate the cubic curve at a parameter 0<=t<=1, returns a Point2D
     * @param c the CubicCurve 
     * @param t param between 0 and 1
     * @return a Point2D 
     */
    private Point2D eval(CubicCurve c, float t){
        Point2D p=new Point2D(Math.pow(1-t,3)*c.getStartX()+
                3*t*Math.pow(1-t,2)*c.getControlX1()+
                3*(1-t)*t*t*c.getControlX2()+
                Math.pow(t, 3)*c.getEndX(),
                Math.pow(1-t,3)*c.getStartY()+
                3*t*Math.pow(1-t, 2)*c.getControlY1()+
                3*(1-t)*t*t*c.getControlY2()+
                Math.pow(t, 3)*c.getEndY());
        return p;
    }

    /**
     * Evaluate the tangent of the cubic curve at a parameter 0<=t<=1, returns a Point2D
     * @param c the CubicCurve 
     * @param t param between 0 and 1
     * @return a Point2D 
     */
    private Point2D evalDt(CubicCurve c, float t){
        Point2D p=new Point2D(-3*Math.pow(1-t,2)*c.getStartX()+
                3*(Math.pow(1-t, 2)-2*t*(1-t))*c.getControlX1()+
                3*((1-t)*2*t-t*t)*c.getControlX2()+
                3*Math.pow(t, 2)*c.getEndX(),
                -3*Math.pow(1-t,2)*c.getStartY()+
                3*(Math.pow(1-t, 2)-2*t*(1-t))*c.getControlY1()+
                3*((1-t)*2*t-t*t)*c.getControlY2()+
                3*Math.pow(t, 2)*c.getEndY());
        return p;
    }
}
