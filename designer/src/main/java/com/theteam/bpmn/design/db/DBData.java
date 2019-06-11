package com.theteam.bpmn.design.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBData
{
    // String databaseName = "test";

    private static Logger log = LoggerFactory
       .getLogger(DBData.class);

       Connection con;

    public DBData(String connectionString, String userName, String password)
    {

        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(
            connectionString+"?useSSL=false", userName, password);
        } catch(Exception e) {System.out.print(e);}
    }

    public ArrayList<String> getSchemas()
    {
        ArrayList<String> temp = new ArrayList<>();

        try 
        {
            // --- LISTING DATABASE SCHEMA NAMES ---
            ResultSet resultSet = con.getMetaData().getCatalogs();

            while (resultSet.next())
            {
                String s = resultSet.getString("TABLE_CAT");
                log.info("Schema Name = " + s);
                temp.add(s);
            }
            
            resultSet.close();

            return temp;

        } catch(Exception e)
        {
            System.out.print(e);
            return temp;
        }

    }

    public ArrayList<String> getTables(String databaseName)
    {

        ArrayList<String> temp = new ArrayList<>();

        try 
        {
            // --- LISTING DATABASE TABLE NAMES ---
            String[] types = { "TABLE" };
            ResultSet resultSet = con.getMetaData()
                .getTables(databaseName, null, "%", types);

            String tableName = "";
            while (resultSet.next())
            {
                tableName = resultSet.getString(3);
                log.info("Table Name = " + tableName);
                temp.add(tableName);
            }

            resultSet.close();
            return temp;

        } catch(Exception e)
        {
            System.out.print(e);
            return temp;
        }

    }

    public ArrayList<String> getColumns(String databaseName, String tableName)
    {
        ArrayList<String> temp = new ArrayList<>();

        try 
        {
            // --- LISTING DATABASE COLUMN NAMES ---
            DatabaseMetaData meta = con.getMetaData();
            ResultSet resultSet = meta.getColumns(databaseName, null, tableName, "%");
            while (resultSet.next())
            {
                String s = resultSet.getString(4);
                log.info("Column Name of table " + tableName + " = "
                    + s);
                temp.add(s);
            }

            resultSet.close();
            return temp;

        } catch(Exception e)
        {
            System.out.print(e);
            return temp;
        }

    }


    
}