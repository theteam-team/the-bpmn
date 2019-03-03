package com.theteam.bpmn.design.dnode;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;

public final class DNodeEventHandler
{

    public static EventHandler<MouseEvent> mouseClickedHandler =  new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent me) {

            DNode node = (DNode) me.getSource();
            node.setCursor(Cursor.MOVE);

            if(node.isDrawNode())
            {
                node.setClicked(!node.getClicked());
            }

        }
    };

    public static EventHandler<MouseEvent> mousePressedHandler =  new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent me) {

            DNode node = (DNode) me.getSource();
            node.setCursor(Cursor.MOVE);

            if(node.isDrawNode())
            {
                node.setClicked(!node.getClicked());
            }
        }
    };

    public static EventHandler<MouseEvent> mouseReleasedHandler =  new EventHandler<MouseEvent>() {
        @Override 
        public void handle(MouseEvent me) {

            DNode node = (DNode) me.getSource();
            node.setCursor(Cursor.HAND);
        }
    };

    public static EventHandler<MouseEvent> mouseDraggedHandler =  new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent me) {

            DNode node = (DNode) me.getSource();

            if(node.isDrawNode())
            {
                node.setX(me.getX()-node.getFitWidth()/2);
                node.setY(me.getY()-node.getFitHeight()/2);
            }


        }
    };

    public static EventHandler<MouseEvent> mouseEnteredHandler =  new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent me) {
            DNode node = (DNode) me.getSource();

            node.setCursor(Cursor.HAND);
        }
    };

    public static EventHandler<MouseEvent> mouseExitedHandler =  new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent me) {
            DNode node = (DNode) me.getSource();

            node.setCursor(Cursor.DEFAULT);
        }
    };

}