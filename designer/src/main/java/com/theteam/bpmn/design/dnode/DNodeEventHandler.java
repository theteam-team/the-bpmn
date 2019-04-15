package com.theteam.bpmn.design.dnode;

import com.theteam.bpmn.design.controller.BPMNStageController;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * Represnts the functionality for different events to be used by design nodes (dnodes)
 * Could be moved to new package which will deal with handling events
 */
public final class DNodeEventHandler
{

    private static  BPMNStageController dbpmnController;

    public static void setBPMNController(BPMNStageController bpmnController)
    {
        dbpmnController = bpmnController;
    }

    public static EventHandler<MouseEvent> mouseClickedHandler =  new EventHandler<MouseEvent>()
    {

        @Override
        public void handle(MouseEvent me)
        {

            DNode node = (DNode) me.getSource();

            if(me.getButton() == MouseButton.SECONDARY)
            {
                node.removeNode();
                for (DLine line : node.getStartLines()) {
                    dbpmnController.drawArea.getChildren().remove(line);
                }

                for (DLine line : node.getEndLines()) {
                    dbpmnController.drawArea.getChildren().remove(line);
                }

                dbpmnController.drawArea.getChildren().remove(node);
                
                return;
            }

            if(node.isDrawNode())
            {

                dbpmnController.propertiesView.getChildren().clear();
                dbpmnController.propertiesView.getChildren().addAll(node.getIDPropertNode());

                if(dbpmnController.firstNode == null)
                {
                    dbpmnController.firstNode = node;
                    dbpmnController.firstNode.setClicked(true);
                }

                else
                {
                    if(node == dbpmnController.firstNode)
                    {
                        dbpmnController.firstNode.setClicked(false);
                        dbpmnController.firstNode = null;
                    }
                    else
                    {
                        dbpmnController.secondNode = node;
                        
                        dbpmnController.createLine(dbpmnController.firstNode, dbpmnController.secondNode);

                        dbpmnController.firstNode.setClicked(false);
                        dbpmnController.firstNode = null;
                        dbpmnController.secondNode = null;
                    }
                }
            }

            else
            {
                if(dbpmnController.selectedNode != null)
                    dbpmnController.selectedNode.setClicked(false);

                dbpmnController.selectedNode = node;
                node.setClicked(true);
                
            }

        }
    };

    public static EventHandler<MouseEvent> mousePressedHandler =  new EventHandler<MouseEvent>()
    {
        @Override
        public void handle(MouseEvent me)
        {
            DNode node = (DNode) me.getSource();
            node.setCursor(Cursor.MOVE);

        }
    };

    public static EventHandler<MouseEvent> mouseReleasedHandler =  new EventHandler<MouseEvent>()
    {
        @Override 
        public void handle(MouseEvent me)
        {

            DNode node = (DNode) me.getSource();
            node.setCursor(Cursor.HAND);
        }
    };

    public static EventHandler<MouseEvent> mouseDraggedHandler =  new EventHandler<MouseEvent>()
    {
        @Override
        public void handle(MouseEvent me)
        {

            DNode node = (DNode) me.getSource();

            if(node.isDrawNode())
            {
                node.setX(me.getX()-node.getFitWidth()/2);
                node.setY(me.getY()-node.getFitHeight()/2);

                for (DLine line : node.getStartLines()) {
                    line.changeLineStart(node);
                }

                for (DLine line : node.getEndLines()) {
                    line.changeLineEnd(node);
                }

            }


        }
    };

    public static EventHandler<MouseEvent> mouseEnteredHandler =  new EventHandler<MouseEvent>()
    {
        @Override public void handle(MouseEvent me)
        {
            DNode node = (DNode) me.getSource();

            node.setCursor(Cursor.HAND);
        }
    };

    public static EventHandler<MouseEvent> mouseExitedHandler =  new EventHandler<MouseEvent>()
    {
        @Override public void handle(MouseEvent me)
        {
            DNode node = (DNode) me.getSource();

            node.setCursor(Cursor.DEFAULT);
        }
    };

}