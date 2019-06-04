package com.theteam.bpmn.design.dnode;

import java.util.UUID;

import com.theteam.bpmn.design.dnode.dproperty.DComboBoxProperty;
import com.theteam.bpmn.design.dnode.dproperty.DTextProperty;
import com.theteam.bpmn.design.loader.ImagesLoader;
import com.theteam.snodes.SXML;

import javafx.beans.property.StringProperty;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SingleSelectionModel;


/**
 * DServiceTaskNode ** Service Task **
 * All nodes are ImageView Nodes from the javafx library
 * So all dnodes can be rendered using javafx application
 * All dnodes are represented by images
 */
public class DDBNode extends DNode
{

    StringProperty inputProperty = new SimpleStringProperty();
    StringProperty outputProperty = new SimpleStringProperty();

    StringProperty connectionStringProperty = new SimpleStringProperty();
    StringProperty selectStatementProperty = new SimpleStringProperty();

    StringProperty userNameProperty = new SimpleStringProperty();
    StringProperty passwordProperty = new SimpleStringProperty();

    public SingleSelectionModel<String> connectionString;

    public ObservableList<String>connectionStringList = FXCollections.observableArrayList(
        "mySQL",
        "firebase"
    );

    public DDBNode(SXML xmlWriter, UUID id, Boolean drawNode)
    {
        super(ImagesLoader.nodeImages.get("db"), id.toString());
        this.type = "db";

        this.drawNode = drawNode;

        setId(id.toString());
        idProperty().set(id.toString());

        if(drawNode)
        {
            this.xmlWriter = xmlWriter;
        
            xmlWriter.addDBNode(id.toString());

            DTextProperty dInputProperty = new DTextProperty("Input", inputProperty);
            DTextProperty dOutputProperty = new DTextProperty("Output", outputProperty);

            // DTextProperty dConnectionStringProperty = new DTextProperty("Connection String", connectionStringProperty);
            DTextProperty dSelectStatementProperty = new DTextProperty("Select Statment", selectStatementProperty);

            DTextProperty dUserNameProperty = new DTextProperty("user name", userNameProperty);
            DTextProperty dPasswordProperty = new DTextProperty("password", passwordProperty);

            DComboBoxProperty dConnectionStringProperty = new DComboBoxProperty("Service Type", connectionString, connectionStringList);
            

            allDProperties.add(dInputProperty);
            allDProperties.add(dOutputProperty);

            // allDProperties.add(dConnectionStringProperty);
            allDProperties.add(dSelectStatementProperty);

            allDProperties.add(dUserNameProperty);
            allDProperties.add(dPasswordProperty);

            allDProperties.add(dConnectionStringProperty);

            inputProperty.addListener(new ChangeListener<String>()
            {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue)
                {
                    xmlWriter.setInput(id.toString(), newValue);
                }
            });

            outputProperty.addListener(new ChangeListener<String>()
            {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue)
                {
                    xmlWriter.setOutput(id.toString(), newValue);
                }
            });

            /*
            connectionStringProperty.addListener(new ChangeListener<String>()
            {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue)
                {
                    xmlWriter.setDBConnectionStringProperty(id.toString(), newValue);
                }
            });
            */

            dConnectionStringProperty.getComboSelectionModel().selectedIndexProperty().addListener((Observable o) -> {

                String selectedItem = (String) dConnectionStringProperty.getComboSelectionModel().getSelectedItem();
                xmlWriter.setDBConnectionStringProperty(id.toString(), selectedItem);
                
            });
            
            selectStatementProperty.addListener(new ChangeListener<String>()
            {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue)
                {
                    xmlWriter.setDBSelectStatmentProperty(id.toString(), newValue);
                }
            });

            userNameProperty.addListener(new ChangeListener<String>()
            {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue)
                {
                    xmlWriter.setDBUserNameProperty(id.toString(), newValue);
                }
            });
            
            passwordProperty.addListener(new ChangeListener<String>()
            {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue)
                {
                    xmlWriter.setDBPasswordProperty(id.toString(), newValue);
                }
            });


        }
        

    }

    public void removeNode()
    {
        if(getNextDNode() != null)
        {
            xmlWriter.setPrevNode(getNextDNode().getId(), null);
            //setNextDNode(null);
            getNextDNode().setPPrevDNode(null);
        }

        if(getPrevDNode() != null)
        {
            xmlWriter.setNextNode(getPrevDNode().getId(), null);
            //setPrevDNode(null);
            getPrevDNode().setNNextDNode(null);
        }

        xmlWriter.removeNode(getId());
    }
}