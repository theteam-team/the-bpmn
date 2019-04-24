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

import com.theteam.bpmn.engine.Elist;
import com.theteam.bpmn.engine.Workflow;
import com.theteam.bpmn.engine.ws.WS;  

public class EServiceTask extends ENode
{

    private STaskNode sTask;

    public EServiceTask(SNode sNode, Elist list)
    {
        this.sNode = sNode;
        this.list = list;
    }
    

    @Override
    public void run(Elist l)
    {
        sTask = (STaskNode) sNode;

        System.out.println("\nService_task Node Running");

        String temp = null;

        if(sTask.getInput() != null)
        {
            System.out.println("\nThis node has input");
            System.out.println(sTask.getInput());
            
        }
        
        else
        {
            System.out.println("\nThis node doesn't have input");
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
            System.out.println("\nExecute rest service to link " + sTask.getRestLink());
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
            System.out.println("\nThis node has output\n");
        }

        else
        {
            System.out.println("\nThis node doesn't have output\n");
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