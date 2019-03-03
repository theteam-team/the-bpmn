package com.theteam.bpmn.design.dnode;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DNode extends ImageView
{

    protected String type;

    private DNode nextDNode;
    private DNode prevDNode;

    private BooleanProperty clicked = new SimpleBooleanProperty(this, "clicked", false);

    private boolean drawNode = false;

    public DNode()
    {
        setPickOnBounds(true);

        clicked.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
                //System.out.println("Property str changed: old value = " + oldValue + ", new value = " + newValue);

                if(newValue)
                    setImage(NodeImages.nodeImages.get(getDType() + "_chosen"));
                else
                    setImage(NodeImages.nodeImages.get(getDType()));

            }
        });

        nextDNode = null;
        prevDNode = null;


        setOnMouseClicked(DNodeEventHandler.mouseClickedHandler);
        setOnMousePressed(DNodeEventHandler.mousePressedHandler);
        setOnMouseReleased(DNodeEventHandler.mouseReleasedHandler);
        setOnMouseDragged(DNodeEventHandler.mouseDraggedHandler);
        setOnMouseEntered(DNodeEventHandler.mouseEnteredHandler);
        setOnMouseExited(DNodeEventHandler.mouseExitedHandler);
    }

    public DNode(Image im)
    {
        this();
        setImage(im);
    }

    public final boolean isDrawNode() { return drawNode; }
    public final void setDrawNode(boolean i) { drawNode = i; }

    public final boolean getClicked() { return clicked.get(); }
    public final void setClicked(boolean i) { this.clicked.set(i); }

    public void setNextDNode(DNode node){ this.nextDNode = node; }
    public void setPrevDNode(DNode node){ this.prevDNode = node; }

    public String getDType(){ return this.type; }
    public DNode getNextDNode(){ return this.nextDNode; }
    public DNode getPrevDNode(){ return this.prevDNode; }
    
}