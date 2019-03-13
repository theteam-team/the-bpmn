package com.theteam.snodes;

import java.util.UUID;
import java.util.concurrent.Executor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

//import java.net.URL;  
import javax.xml.namespace.QName;  
import javax.xml.ws.Service;

import com.theteam.bpmn.engine.ws.WS;  


@XmlRootElement
@XmlType(propOrder = { "previousNode", "nextNode", "serviceType", "restLink", "soapFunc"})
public class STaskNode extends SNode
{

    private String previousNode = null;
    private String nextNode = null;

    private String serviceType = null;
    private String restLink = null;
    private String soapFunc = null;

    public STaskNode()
    {

    }

    public STaskNode(String type, UUID id)
    {
        this.type = type;
        this.nId = id.toString();
        this.uuid = id;
    }

    public STaskNode(String type, String id)
    {
        this.type = type;
        this.nId = id;
        this.uuid = UUID.fromString(id);
    }

    @XmlAttribute
    public String getNId()
    {
        return nId;  
    }

    public void setNId(String id)
    {
        this.nId = id;
    }

    public UUID getUUId()
    {
        return uuid;  
    }

    @XmlAttribute
    public String getType()
    {
        return type;  
    }  

    public void setType(String type)
    {  
        this.type = type;  
    }


    @XmlElement(name = "previousNode")
    public String getPreviousNode()
    {

        if(previousNode != null)
            return previousNode;
        return null;
        
    }

    public void setPreviousNode(String previousNode)
    {
        this.previousNode = previousNode;
    }

    @XmlElement(name = "nextNode")
    public String getNextNode()
    {

        if(nextNode != null)
            return nextNode;
        return null;
    }

    public void setNextNode(String nextNode)
    {
        this.nextNode = nextNode;
    }


    @Override
    public void run()
    {

        System.out.println("Service_task Node Running");


        if(serviceType.equals("soap"))
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

            if(soapFunc.equals("getHello"))
            {
                System.out.println("Execute function getHello");
                System.out.println(hello.getHello("the-team"));  
            }
            
            else if(soapFunc.equals("getResponseWithName"))
            {
                System.out.println("Execute function getHellogetResponseWithName");
                System.out.println(hello.getResponseWithName("the-team"));
            }

            } catch(Exception e)
            {

            }

        }

        else if(serviceType.equals("rest"))
        {
            System.out.println("Execute rest service to link " + restLink);
            try {

                // http://localhost:8080/RESTfulExample/json/product/get
            
                URL url = new URL(restLink);
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
                }
                
                conn.disconnect();
    
            } catch (MalformedURLException e) {
    
                e.printStackTrace();
            } catch (IOException e) {
    
                e.printStackTrace();
                
            }

        }


    }


    @XmlElement(name = "ServiceType")
    public String getServiceType()
    {

        if(serviceType != null)
            return serviceType;
        return null;
        
    }

    public void setServiceType(String serviceType)
    {
        this.serviceType = serviceType;
    }


    @XmlElement(name = "restLink")
    public String getRestLink()
    {

        if(restLink != null)
            return restLink;
        return null;
        
    }

    public void setRestLink(String restLink)
    {
        this.restLink = restLink;
    }


    @XmlElement(name = "soapFunc")
    public String getSoapFunc()
    {

        if(soapFunc != null)
            return soapFunc;
        return null;
        
    }

    public void setSoapFunc(String soapFunc)
    {
        this.soapFunc = soapFunc;
    }

}
