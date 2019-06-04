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

import java.io.FileInputStream;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
    public void run(Elist l)
    {
        System.out.println("\nDB Node Running");

        if(sDB.getConnectedEvent() != null)
        {

            try
        {
            //System.out.println("Hello");
            FileInputStream serviceAccount =
            new FileInputStream("C:/work/bpm/realtime-23d22-firebase-adminsdk-i7h3m-ed52b91526.json");
            
            //System.out.println("Hello1");
            FirebaseOptions options = new FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .setDatabaseUrl("https://realtime-23d22.firebaseio.com")
            .build();
            //System.out.println("Hello2");
            
            FirebaseApp.initializeApp(options);
            
            //System.out.println("Hello3");

            var key_orders= sDB.getSelectStatement();

            FirebaseDatabase db = FirebaseDatabase.getInstance();

            DatabaseReference ref = db
            .getReference(key_orders);
            System.out.println(ref);

            ref.limitToLast(1).addValueEventListener(new ValueEventListener(){
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    System.out.println("Found new data");

                    String v1 = "";
                    String v2 = "";

                    for(DataSnapshot d : dataSnapshot.getChildren())
                    {
                        Object document = d.getValue();

                        JsonParser jsonPa = new JsonParser();

                        JsonElement ge =  jsonPa.parse(document.toString());

                        JsonObject payload1 = ge.getAsJsonObject();

                        v1 = payload1.get("name").toString();
                        
                        //System.out.println(payload1.get("name"));
                        //System.out.println(payload1.get("email"));
                    }

                    if(sDB.getOutput() != null)
                    {
                        EVariable o = list.getVariable(sDB.getOutput());
                        System.out.println(v1);
                        o.setValue(v1);

                        for(ENode n : l.eNodes)
                        {
                        
                            if(n.getSNode().getNId().equals(  sDB.getConnectedEvent()  ))
                            {

                                for(ENode s : l.eNodes)
                                {
                                
                                    if(s.getSNode().getNId().equals(  n.getSNode().getNextNode()  ))
                                    {
                                        s.run(l);
                                    }
                                }

                            }
                        }

                        
                    }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
            

        } catch(Exception e)
        {
            System.out.println(e);
        }

            /*
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
                                        n.run(l);
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

                */

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

        

        for(ENode n : l.eNodes)
        {
            if(n.getSNode().getNId().equals(getSNode().getNextNode()))
            {
                n.run(l);
                return;
            }
        }
    }
}