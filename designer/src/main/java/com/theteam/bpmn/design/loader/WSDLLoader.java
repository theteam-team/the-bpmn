package com.theteam.bpmn.design.loader;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;

import com.theteam.bpmn.design.App;

import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

/**
 * Class loader to load wsdl files ** Not used for Now **
 */
public final class WSDLLoader
{

    public static void loadWSDL()
    {
        try
        {
            
            File inputFile = new File(App.class.getResource("/wsdl/ws.xml").getFile());

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);

            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("portType");
            System.out.println("----------------------------");
            
            for (int temp = 0; temp < nList.getLength(); temp++)
            {

                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());
            
                if (nNode.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element eElement = (Element) nNode;
                    System.out.println("name : " 
                        + eElement.getAttribute("name"));

                    NodeList opList = eElement.getElementsByTagName("operation");

                    for (int a = 0; a < opList.getLength(); a++)
                    {
                        Node opNode = opList.item(a);

                        System.out.println("\nCurrent Element :" + opNode.getNodeName());

                        if (opNode.getNodeType() == Node.ELEMENT_NODE)
                        {
                            Element opElement = (Element) opNode;
                            System.out.println("name : " 
                                + opElement.getAttribute("name"));
                        }
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}