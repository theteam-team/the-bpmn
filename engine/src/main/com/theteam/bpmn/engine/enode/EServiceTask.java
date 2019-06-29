package com.theteam.bpmn.engine.enode;

import com.theteam.snodes.SNode;
import com.theteam.snodes.STaskNode;
import com.theteam.taskdata.STaskData;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.UUID;

import javax.observer.Subscription;
import javax.observer.ValueChangedEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

//import javax.xml.namespace.QName;
//import javax.xml.ws.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.theteam.bpmn.engine.Elist;
import com.theteam.bpmn.engine.Workflow;
import com.theteam.bpmn.engine.io.EVariable;
import com.theteam.bpmn.engine.scan.Scan;
import com.theteam.bpmn.engine.ws.WS;  

public class EServiceTask extends ENode
{

    private STaskNode sTask;

    private boolean in;
    private boolean out;

    Subscription taskSubscription;
    Subscription messageSubscription;

    String workflowName;
    String instanceId;
    String taskId;

    public EServiceTask(SNode sNode, Elist list)
    {
        this.sNode = sNode;
        this.list = list;
    }
    

    @Override
    public void run(Elist l, String id)
    {
        sTask = (STaskNode) sNode;

        System.out.println("\nService_task Node Running");

        workflowName = l.sNodes.getName();
        instanceId = id;
        taskId = sTask.getNId();

        ArrayList<String> processes = Workflow.processesRun.get(instanceId);

        if(processes == null)
        {
            ArrayList<String> tempList = new ArrayList<>();
            tempList.add(sNode.getNId());
            Workflow.processesRun.put(instanceId, tempList);
        }
        else
        {
            processes.add(sNode.getNId());
        }


        JsonObject jsonEle1 = new JsonObject();

        jsonEle1.addProperty("workflowName", workflowName);
        jsonEle1.addProperty("instanceID", instanceId);

        JsonArray jArray = new JsonArray();

        for (String var : Workflow.processesRun.get(instanceId)) {

            JsonObject jsonEle2 = new JsonObject();
            jsonEle2.addProperty("processID", var);
            jArray.add(jsonEle2);
        }

        jsonEle1.add("processes", jArray);
        Workflow.workflowObserver.updateVal(jsonEle1.toString());

        //String temp = "";

        if(sTask.getInput() != null)
        {
            System.out.println("\nThis node has input");
            System.out.println(sTask.getInput());

            in = true;
            
        }
        
        else
        {
            System.out.println("\nThis node doesn't have input");
            in =  false;
        }

        if(sTask.getOutput() != null)
        {
            System.out.println("\nThis node has output\n");

            out = true;
        }

        else
        {
            System.out.println("\nThis node doesn't have output\n");

            out = false;
        }

        //
        //
        //
        //
        // Start

        /*

        if(sTask.getServiceType().equals("soap"))
        {
            System.out.println("Execute soap service to link http://localhost:8080/soap_server/ws?wsdl");
            try
            {
                URL url = new URL("http://localhost:8080/soap_server/ws?wsdl");

                //1st argument service URI, refer to wsdl document above  
                //2nd argument is service name, refer to wsdl document above

                QName qname = new QName("http://ws.engine.bpmn.theteam.com/", "WSImplService");  
                Service service = Service.create(url, qname);

                WS hello = service.getPort(WS.class);

                if(sTask.getSoapFunc().equals("getHello"))
                {
                    System.out.println("Execute function getHello");

                    temp = hello.getHello("the-team");
                    System.out.println(temp); 
                }
                
                else if(sTask.getSoapFunc().equals("getResponseWithName"))
                {
                    System.out.println("Execute function getHellogetResponseWithName");
                    temp = hello.getResponseWithName("the-team");
                    System.out.println(temp);
                }

            } catch(Exception e)
            {

            }

        }
        */

        if(sTask.getTaskType().equals("APITask"))
        {
            
            System.out.println("\nExecute rest service to link " + sTask.getRestLink());
            
            if(sTask.getServiceType().equals("get"))

            {
                try {
    
                    // http://localhost:8080/RESTfulExample/json/product/get
                
                    URL url = new URL(sTask.getRestLink());
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Accept", "application/json");
        
                    if (conn.getResponseCode() != 200) {
                        throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
                    }
        
                    BufferedReader br = new BufferedReader(new InputStreamReader(
                            (conn.getInputStream())));
        
                    String temp = "";
                    String output;
                    // System.out.println("Output from Server .... \n");
                    while ((output = br.readLine()) != null) {
        
                        System.out.println(output);
                        temp += output;
                    }
                    
                    conn.disconnect();
        
                } catch (MalformedURLException e) {
        
                    e.printStackTrace();
                } catch (IOException e) {
        
                    e.printStackTrace();
                    
                }

            }
            

            if(sTask.getServiceType().equals("post"))

            {

                try {
    
                    String a = "";
        
                    CloseableHttpClient client = HttpClients.createDefault();
                    //HttpPost post = new HttpPost("https://postman-echo.com/post");
                    HttpPost post = new HttpPost(sTask.getRestLink());
    
                    EVariable e = list.getVariable(sTask.getInput());
                    Scan s = new Scan(e.getValue(), l);
                    String j = s.getFinalString();
    
                    System.out.println("\n JJJJSONS");
                    //System.out.println(o.getValue());
                    System.out.println(j);
    
                    JSONObject orgJson = new JSONObject(j);
    
                    String so = e.getValue();
                    JsonObject payload = new JsonObject();
                    
                    payload.addProperty("customer_id", UUID.randomUUID().toString());
                    payload.addProperty("name", so);
                    payload.addProperty("phone_number", 0);
                    payload.addProperty("email", "1234");
                    payload.addProperty("dateOfBirth", "1999-06-02");
                    payload.addProperty("gender", "1234");
                    payload.addProperty("loyality_points", 0);
                    payload.addProperty("type", 0);
                    payload.addProperty("company", "1234");
                    payload.addProperty("company_email", "1234");
                    payload.addProperty("is_lead", true);
                    
                    
                    post.setEntity(new StringEntity(orgJson.toString()));
                    post.setHeader("Accept", "application/json");
                    post.setHeader("Content-type", "application/json");
        
                    CloseableHttpResponse response = client.execute(post);
                    System.out.println(response.getStatusLine().getStatusCode());
        
                    BufferedReader br = new BufferedReader(new InputStreamReader(
                            (response.getEntity().getContent())));
        
                    String output;
                    // System.out.println("Output from Server .... \n");
                    while ((output = br.readLine()) != null) {
        
                        //System.out.println(output);
                        a += output;
                    }
                    System.out.println(a);
        
                    
                    client.close();
        
                    
        
                } catch (Exception e) {
        
                    e.printStackTrace();
                }

            }

            for(ENode n : l.eNodes)
            {
                if(n.getSNode().getNId().equals(getSNode().getNextNode()))
                {
                    n.run(l, instanceId);
                    return;
                }
            }

        }

        else if(sTask.getTaskType().equals("userTask"))
        {

            JsonObject ob = new JsonObject();

            ob.addProperty("type", "userTask");

            ob.addProperty("workflowName", workflowName);
            ob.addProperty("instanceID", instanceId);
            ob.addProperty("taskID", sTask.getNId());

            ob.addProperty("taskName", sTask.getTaskName());

            JsonArray obArr = new JsonArray();

            for (STaskData var : sTask.getTaskDData().getData())
            {

                JsonObject ob1 = new JsonObject();

                ob1.addProperty("name", var.getName());
                ob1.addProperty("type", var.getVal());

                obArr.add(ob1);
            }

            ob.add("taskParam", obArr);

            Workflow.businessTasks.add(ob);

            taskSubscription = Workflow.serverObserver.onChanged(e -> completeTask(e));

        }

        else if(sTask.getTaskType().equals("externalTask"))
        {

            JsonObject ob = new JsonObject();

            ob.addProperty("type", "externalTask");

            ob.addProperty("workflowName", workflowName);
            ob.addProperty("instanceID", instanceId);
            ob.addProperty("taskID", sTask.getNId());

            ob.addProperty("taskName", sTask.getTaskName());

            JsonArray obArr = new JsonArray();
            JsonObject ob1 = new JsonObject();

            for (STaskData var : sTask.getTaskDData().getData())
            {
                ob1.addProperty(var.getName(), var.getVal());
            }

            obArr.add(ob1);
            ob.add("taskParam", obArr);

            Workflow.businessTasks.add(ob);

            taskSubscription = Workflow.serverObserver.onChanged(e -> completeTask(e));

        }

        else if(sTask.getTaskType().equals("message"))
        {

            JsonObject ob1 = new JsonObject();
            ob1.addProperty("message", sTask.getMessageOut());
            ob1.addProperty("data", sTask.getMessageOut());


            Workflow.messageObserver.updateVal(ob1.toString());
            messageSubscription = Workflow.messageObserver.onChanged(e -> completeMessage(e));

        }

    }

    public void completeTask(ValueChangedEvent<? extends String> e)
    {

        JSONObject orgJson = new JSONObject(e.value());

        String workflowName = orgJson.getString("workflowName");
        String instanceId = orgJson.getString("instanceID");
        String taskId = orgJson.getString("taskID");

        String type = orgJson.getString("type");


        if( this.workflowName.equals(workflowName) &&
            this.instanceId.equals(instanceId) &&
            this.taskId.equals(taskId) )
        {

            if(type.equals("externalTask"))
            {

            }
            else if(type.equals("userTask"))
            {

            }

            taskSubscription.unsubscribe();

            if(sTask.getMessageOut() != null)
            {
                JsonObject ob1 = new JsonObject();
                ob1.addProperty("message", sTask.getMessageOut());
                ob1.addProperty("data", sTask.getMessageOut());


                Workflow.messageObserver.updateVal(ob1.toString());
                messageSubscription = Workflow.messageObserver.onChanged(e -> completeMessage(e));
            }
            else
            {
                // add to variable output - continue tasks
            }
        }
    }

    public void completeMessage(ValueChangedEvent<? extends String> e)
    {

        JSONObject orgJson = new JSONObject(e.value());

        String message = orgJson.getString("message");
        String data = orgJson.getString("data");

        if(message.equals(sTask.getMessageIn()))
        {
            if(sTask.getOutput() != null)
            {
                EVariable o = list.getVariable(sTask.getOutput());
                o.setValue(data);
            }

            messageSubscription.unsubscribe();

            for(ENode n : list.eNodes)
            {
                if(n.getSNode().getNId().equals(getSNode().getNextNode()))
                {
                    n.run(list, instanceId);
                    return;
                }
            }

        }

    }
}