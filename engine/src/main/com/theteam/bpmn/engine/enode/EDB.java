package com.theteam.bpmn.engine.enode;

import com.theteam.bpmn.engine.Elist;
import com.theteam.bpmn.engine.Workflow;
import com.theteam.bpmn.engine.io.EVariable;
import com.theteam.snodes.SDBNode;
import com.theteam.snodes.SNode;

import java.sql.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class EDB extends ENode
{

    private SDBNode sDB;

    int number = 0;

    public EDB(SNode sNode, Elist list)
    {
        this.sNode = sNode;

        this.list = list;

        sDB = (SDBNode) sNode;
    }

    @Override
    public void run()
    {
        System.out.println("DB Node Running");

        if(sDB.getConnectedEvent() != null)
        {
            System.out.println("Listening to a database");
            ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
            exec.scheduleAtFixedRate(new Runnable()
            {
                @Override
                public void run() 
                {
                    
                    System.out.println("Checking the database");
                    

                        try
                        {  

                            //Class.forName("com.mysql.cj.jdbc.Driver");   
                            Class.forName("com.mysql.cj.jdbc.Driver");   
                            
                            //Connection con = DriverManager.getConnection(
                            //"jdbc:mysql://localhost:3306","root","mySQLpass@123");
                
                            Connection con = DriverManager.getConnection(  
                            sDB.getConnectionString()+"?useSSL=false", sDB.getUserName(), sDB.getPassword());  
                            
                
                            Statement stmt = con.createStatement();
                            //ResultSet rs=stmt.executeQuery("SELECT Name FROM world.city where id=1");
                            ResultSet rs = stmt.executeQuery(sDB.getSelectStatement());
                            int count = 0;
                
                            while(rs.next())
                            {
                                count++;
                                if(sDB.getOutput() != null)
                                {
                                    EVariable o = list.getVariable(sDB.getOutput());
                                    //System.out.println(rs.getString(2) + ": " + rs.getString(3));
                                    o.setValue(rs.getString(2)+ "  " + rs.getString(3));
                                }
                            }
                            if(count > number)
                            {
                                number = count;
                                
                                for(ENode n : list.eNodes)
                                {
                                    
                                    if(n.getSNode().getNId().equals(sDB.getConnectedEvent()))
                                    {
                                        n.run();
                                    }

                                }
                            }
                                
                            con.close();
                
                        } catch(Exception e)
                        {
                            System.out.println("MySql bug with java 11 when disconnecting - Search it");
                            System.out.println(e);
                        }
                    }
                }, 0, 5, TimeUnit.SECONDS);

        }

        else
        {

            try{  

                //Class.forName("com.mysql.cj.jdbc.Driver");   
                Class.forName("com.mysql.cj.jdbc.Driver");   
                
                //Connection con = DriverManager.getConnection(
                //"jdbc:mysql://localhost:3306","root","mySQLpass@123");
    
                Connection con = DriverManager.getConnection(  
                sDB.getConnectionString()+"?useSSL=false", sDB.getUserName(), sDB.getPassword());  
                  
    
                Statement stmt = con.createStatement();
                //ResultSet rs=stmt.executeQuery("SELECT Name FROM world.city where id=1");
                ResultSet rs = stmt.executeQuery(sDB.getSelectStatement());
    
                if(rs.next())
                {
                    if(sDB.getOutput() != null)
                    {
                        EVariable o = list.getVariable(sDB.getOutput());
                        System.out.println(rs.getString(2) + ": " + rs.getString(3));
                        o.setValue(rs.getString(3));
                    }
    
                }
    
                while(rs.next())  
                    System.out.println(rs.getString(3));  
                    
                con.close();
    
            } catch(Exception e)
            {
                System.out.println("MySql bug with java 11 when disconnecting - Search it");
                System.out.println(e);
            }
    
        }

        
    }
}