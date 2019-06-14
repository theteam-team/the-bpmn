package com.theteam.bpmn.design.dnode;

import java.util.UUID;

import com.theteam.bpmn.design.dnode.dproperty.DButtonProperty;
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
    StringProperty connectionStringProperty1 = new SimpleStringProperty();
    StringProperty selectStatementProperty = new SimpleStringProperty();

    StringProperty sqlStatementProperty = new SimpleStringProperty();

    StringProperty userNameProperty = new SimpleStringProperty();
    StringProperty passwordProperty = new SimpleStringProperty();

    public SingleSelectionModel<String> connectionString;

    public ObservableList<String>connectionStringList = FXCollections.observableArrayList(
        "Oracle",
        "SQLlite",
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

            DComboBoxProperty dConnectionStringProperty = new DComboBoxProperty("Service Type", connectionString, connectionStringList);
            DTextProperty dConnectionStringProperty1 = new DTextProperty("host", connectionStringProperty1);
            DTextProperty dSelectStatementProperty = new DTextProperty("Statment", selectStatementProperty);
            DButtonProperty dSqlStatementProperty = new DButtonProperty("Select Statment", sqlStatementProperty, "dbStage");

            DTextProperty dUserNameProperty = new DTextProperty("user name", userNameProperty);
            DTextProperty dPasswordProperty = new DTextProperty("password", passwordProperty);

            allDProperties.add(dInputProperty);
            allDProperties.add(dOutputProperty);

            allDProperties.add(dConnectionStringProperty);
            allDProperties.add(dConnectionStringProperty1);

            allDProperties.add(dSelectStatementProperty);
            allDProperties.add(dSqlStatementProperty);

            allDProperties.add(dUserNameProperty);
            allDProperties.add(dPasswordProperty);


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

                    String s = "";

                    switch (selectedItem)
                    {
                        case "Oracle":
                            s += "jdbc:oracle:thin:@";
                            //  jdbc:oracle:thin:@hostname:port Number:databaseName
                            break;

                        case "SQLlite":
                            s += "jdbc:sqlite:";
                            // jdbc:sqlite:C:/sqlite/db/chinook.db
                            
                            break;
                            
                        case "mySQL":
                            s += "jdbc:mysql://";
                            // jdbc:mysql://hostname/ databaseName
                            // jdbc:mysql://localhost:3306"
                            break;

                        case "firebase":
                            s += "firebase://";
                            break;
                    
                        default:
                            break;
                    }

                    s += connectionStringProperty1.get();

                    xmlWriter.setDBConnectionStringProperty(id.toString(), s);
                
            });
            
            connectionStringProperty1.addListener(new ChangeListener<String>()
            {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue)
                {
                    String selectedItem = (String) dConnectionStringProperty.getComboSelectionModel().getSelectedItem();

                    String s = "";

                    switch (selectedItem)
                    {
                        case "Oracle":
                            s += "jdbc:oracle:thin:@";
                            //  jdbc:oracle:thin:@hostname:port Number:databaseName
                            break;

                        case "SQLlite":
                            s += "jdbc:sqlite:";
                            // jdbc:sqlite:C:/sqlite/db/chinook.db
                            
                            break;
                            
                        case "mySQL":
                            s += "jdbc:mysql://";
                            // jdbc:mysql://hostname/ databaseName
                            // jdbc:mysql://localhost:3306"
                            break;

                        case "firebase":
                            s += "firebase://";
                            break;
                    
                        default:
                            break;
                    }

                    s += newValue;
                    xmlWriter.setDBConnectionStringProperty(id.toString(), s);
                }
            });

            selectStatementProperty.addListener(new ChangeListener<String>()
            {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue)
                {
                    xmlWriter.setDBSelectStatmentProperty(id.toString(), newValue);
                }
            });

            sqlStatementProperty.addListener(new ChangeListener<String>()
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

        xmlWriter.removePosition(getId());
        xmlWriter.removeNode(getId());
    }
}