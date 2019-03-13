package com.theteam.bpmn.engine.ws;

import javax.jws.WebService;

//Service Implementation  
@WebService(endpointInterface = "com.theteam.bpmn.engine.ws.WS")  
public class WSImpl implements WS
{  
    @Override  
    public String getResponseWithName(String name)
    {  
        return "JAX-WS Responds with " + name;  
    }  

    @Override  
    public String getHello(String name)
    {  
        return "Hello from server " + name;  
    }  
} 