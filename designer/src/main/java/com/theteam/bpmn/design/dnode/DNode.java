package com.theteam.bpmn.design.dnode;

import java.util.ArrayList;
import java.util.List;

import com.theteam.bpmn.design.dnode.dproperty.DProperty;
import com.theteam.bpmn.design.dnode.dproperty.DTextProperty;
import com.theteam.bpmn.design.loader.ImagesLoader;
import com.theteam.snodes.SXML;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * The parent class for all design nodes (dnodes)
 * All nodes are ImageView Nodes from the javafx library
 * So all dnodes can be rendered using javafx application
 * All dnodes are represented by images
 */
public class DNode extends ImageView
{
    SXML xmlWriter;
    
    protected String type;

    private DNode nextDNode;
    private DNode prevDNode;

    protected List<DProperty> allDProperties;

    private BooleanProperty clicked = new SimpleBooleanProperty(this, "clicked", false);

    protected boolean drawNode = false;

    public DNode(String id)
    {

        super();
        
        setPickOnBounds(true);

        allDProperties = new ArrayList<DProperty>();

        DProperty dIdProperty = new DTextProperty("ID", idProperty());

        allDProperties.add(dIdProperty);

        setId(id);

        clicked.addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue)
            {
                if(newValue)
                    setImage(ImagesLoader.nodeImages.get(getDType() + "_chosen"));
                else
                    setImage(ImagesLoader.nodeImages.get(getDType()));
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

    public DNode(Image im, String id)
    {
        this(id);
        setImage(im);
    }

    public List<DProperty> getIDPropertNode() { return allDProperties; }

    public boolean isDrawNode() { return drawNode; }
    public void setDrawNode(boolean i) { drawNode = i; }

    public boolean getClicked() { return clicked.get(); }
    public void setClicked(boolean i) { this.clicked.set(i); }

    public DNode getNextDNode() { return this.nextDNode; }
    public void setNextDNode(DNode node)
    {
        this.nextDNode = node;
        xmlWriter.setNextNode(getId(), node.getId());
    }

    public DNode getPrevDNode() { return this.prevDNode; }
    public void setPrevDNode(DNode node)
    { 
        this.prevDNode = node;
        xmlWriter.setPrevNode(getId(), node.getId());
    }

    public String getDType() { return this.type; }

}