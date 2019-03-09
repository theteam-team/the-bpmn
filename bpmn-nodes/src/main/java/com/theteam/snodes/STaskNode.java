package com.theteam.snodes;

import java.util.UUID;

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


@XmlRootElement
@XmlType(propOrder = { "previousNode", "nextNode", "webServiceType", "restLink", "restType"})
public class STaskNode extends SNode
{

    private String previousNode = null;
    private String nextNode = null;

    private String webServiceType = null;
    private String restLink = null;
    private String restType = null;

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
            return previousNode.toString();
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
            return nextNode.toString();
        return null;
    }

    public void setNextNode(String nextNode)
    {
        this.nextNode = nextNode;
    }


    @Override
    public void run()
    {

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


    @XmlElement(name = "webServiceType")
    public String getWebServiceType()
    {

        if(webServiceType != null)
            return webServiceType;
        return null;
        
    }

    public void setWebServiceType(String webServiceType)
    {
        this.webServiceType = webServiceType;
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


    @XmlElement(name = "restType")
    public String getRestType()
    {

        if(restType != null)
            return restType;
        return null;
        
    }

    public void setrestType(String restType)
    {
        this.restType = restType;
    }

}
