package com.theteam.bpmn.engine.enode;

import com.theteam.snodes.SNode;
import com.theteam.snodes.STaskNode;

import java.util.UUID;
import java.util.concurrent.Executor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;  
import javax.xml.ws.Service;

import com.theteam.bpmn.engine.ws.WS;  

public class EServiceTask extends ENode
{

    private STaskNode sTask;

    public EServiceTask(SNode sNode)
    {
        this.sNode = sNode;
    }
    

    @Override
    public void run()
    {
        sTask = (STaskNode) sNode;

        System.out.println("Service_task Node Running");

        String temp = null;

        if(sTask.getInput() != null)
        {
            System.out.println("This node has input");
            System.out.println(sTask.getInput());
            
        }
        
        else
        {
            System.out.println("This node doesn't have input has input");
        }

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

        else if(sTask.getServiceType().equals("rest"))
        {
            System.out.println("Execute rest service to link " + sTask.getRestLink());
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
    
                String output;
                // System.out.println("Output from Server .... \n");
                while ((output = br.readLine()) != null) {
    
                    System.out.println(output);
                    temp += temp;
                }
                
                conn.disconnect();
    
            } catch (MalformedURLException e) {
    
                e.printStackTrace();
            } catch (IOException e) {
    
                e.printStackTrace();
                
            }

        }

        if(sTask.getOutput() != null)
        {
            System.out.println("This node has output");
        }

        else
        {
            System.out.println("This node doesn't have output");
        }
    }
}