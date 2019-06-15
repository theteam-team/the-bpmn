package com.theteam.bpmn.engine.enode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.theteam.bpmn.engine.Elist;
import com.theteam.bpmn.engine.Workflow;
import com.theteam.bpmn.engine.io.EVariable;
import com.theteam.snodes.SDBNode;
import com.theteam.snodes.SNode;



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
    public void run(Elist l, String id)
    {
        System.out.println("\nDB Node Running");

        String workflowName = l.sNodes.getName();
        String instanceID = id;

        ArrayList<String> processes = Workflow.processesRun.get(instanceID);

        if(processes == null)
        {
            ArrayList<String> tempList = new ArrayList<>();
            tempList.add(sNode.getNId());
            Workflow.processesRun.put(instanceID, tempList);
        }
        else
        {
            processes.add(sNode.getNId());
        }


        JsonObject jsonEle1 = new JsonObject();

        jsonEle1.addProperty("workflowName", workflowName);
        jsonEle1.addProperty("instanceID", instanceID);

        JsonArray jArray = new JsonArray();

        for (String var : Workflow.processesRun.get(instanceID)) {

            JsonObject jsonEle2 = new JsonObject();
            jsonEle2.addProperty("processID", var);
            jArray.add(jsonEle2);
        }

        jsonEle1.add("processes", jArray);
        Workflow.wo.updateVal(jsonEle1.toString());

        if(sDB.getConnectedEvent() != null)
        {

            /*
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
            */

            
            System.out.println("Listening to a database");
            ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
            exec.scheduleAtFixedRate(new Runnable()
            {
                @Override
                public void run() 
                {
                    
                    System.out.println("Listening the database");

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
                            ResultSetMetaData rsmd = rs.getMetaData();

                            JsonObject obj = new JsonObject();

                            int count = 0;
                
                            while(rs.next())
                            {
                                count++;

                                if(rs.isLast())
                                {
                                    int numColumns = rsmd.getColumnCount();

                                    for (int i=1; i<numColumns+1; i++)
                                    {
                                        String column_name = rsmd.getColumnName(i);

                                        if(rsmd.getColumnType(i)==java.sql.Types.BOOLEAN){
                                            obj.addProperty(column_name, rs.getBoolean(column_name));
                                        }
                                        else if(rsmd.getColumnType(i)==java.sql.Types.DOUBLE){
                                            obj.addProperty(column_name, rs.getDouble(column_name)); 
                                        }
                                        else if(rsmd.getColumnType(i)==java.sql.Types.FLOAT){
                                            obj.addProperty(column_name, rs.getFloat(column_name));
                                        }
                                        else if(rsmd.getColumnType(i)==java.sql.Types.INTEGER){
                                            obj.addProperty(column_name, rs.getInt(column_name));
                                        }
                                        else if(rsmd.getColumnType(i)==java.sql.Types.VARCHAR){
                                            obj.addProperty(column_name, rs.getString(column_name));
                                        }
                                        else if(rsmd.getColumnType(i)==java.sql.Types.DATE){
                                            obj.addProperty(column_name, rs.getDate(column_name).toString());
                                        }
                                        else if(rsmd.getColumnType(i)==java.sql.Types.TIMESTAMP){
                                            obj.addProperty(column_name, rs.getTimestamp(column_name).toString());
                                        }
                                        else{
                                            obj.addProperty(column_name, rs.getObject(column_name).toString());
                                        }
                                    }
                                }

                                if(sDB.getOutput() != null)
                                {
                                    EVariable o = list.getVariable(sDB.getOutput());
                                    o.setValue(obj.toString());
                                }
                            }

                            if(count > number)
                            {
                                number = count;
                                System.out.println(obj);
                                
                                for(ENode n : list.eNodes)
                                {
                                    
                                    if(n.getSNode().getNId().equals(sDB.getConnectedEvent()))
                                    {
                                        for(ENode in : l.eNodes)
                                        {
                                            if(in.getSNode().getNId().equals(n.getSNode().getNextNode()))
                                            {
                                                in.run(l, instanceID);
                                                return;
                                            }
                                        }

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
                }, 5, 5, TimeUnit.SECONDS);

                

        }

        else
        {

            try{  

                //Class.forName("com.mysql.cj.jdbc.Driver");
                //Class.forName("com.mysql.cj.jdbc.Driver");
                Class.forName("com.mysql.cj.jdbc.Driver");
                //Class.forName("oracle.jdbc.driver.OracleDriver");
                
                //Connection con = DriverManager.getConnection(
                //"jdbc:mysql://localhost:3306","root","mySQLpass@123");

                //"?useSSL=false"
    
                Connection con = DriverManager.getConnection(  
                sDB.getConnectionString()+"?useSSL=false", sDB.getUserName(), sDB.getPassword());

                Statement stmt = con.createStatement();
                //ResultSet rs=stmt.executeQuery("SELECT Name FROM world.city where id=1");
                ResultSet rs = stmt.executeQuery(sDB.getSelectStatement());

                /*
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

                */

                JsonArray json = new JsonArray();
                ResultSetMetaData rsmd = rs.getMetaData();

                while(rs.next())
                {
                    int numColumns = rsmd.getColumnCount();
                    JsonObject obj = new JsonObject();

                    for (int i=1; i<numColumns+1; i++)
                    {
                        String column_name = rsmd.getColumnName(i);

                        if(rsmd.getColumnType(i)==java.sql.Types.BOOLEAN){
                            obj.addProperty(column_name, rs.getBoolean(column_name));
                        }
                        else if(rsmd.getColumnType(i)==java.sql.Types.DOUBLE){
                            obj.addProperty(column_name, rs.getDouble(column_name)); 
                        }
                        else if(rsmd.getColumnType(i)==java.sql.Types.FLOAT){
                            obj.addProperty(column_name, rs.getFloat(column_name));
                        }
                        else if(rsmd.getColumnType(i)==java.sql.Types.INTEGER){
                            obj.addProperty(column_name, rs.getInt(column_name));
                        }
                        else if(rsmd.getColumnType(i)==java.sql.Types.VARCHAR){
                            obj.addProperty(column_name, rs.getString(column_name));
                        }
                        else if(rsmd.getColumnType(i)==java.sql.Types.DATE){
                            obj.addProperty(column_name, rs.getDate(column_name).toString());
                        }
                        else if(rsmd.getColumnType(i)==java.sql.Types.TIMESTAMP){
                            obj.addProperty(column_name, rs.getTimestamp(column_name).toString());
                        }
                        else{
                            obj.addProperty(column_name, rs.getObject(column_name).toString());
                        }
                    }

                    //System.out.println(obj);
                    json.add(obj);
                    
                }

                System.out.println(json);

                if(sDB.getOutput() != null)
                {
                    EVariable o = list.getVariable(sDB.getOutput());
                    o.setValue(json.toString());
                }
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
                n.run(l, instanceID);
                return;
            }
        }
    }
}