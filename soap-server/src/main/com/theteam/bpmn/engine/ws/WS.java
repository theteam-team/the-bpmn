package com.theteam.bpmn.engine.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

//Service Endpoint Interface  
@WebService  
@SOAPBinding(style = Style.RPC)  
public interface WS
{  
    @WebMethod
    String getResponseWithName(String name);

    @WebMethod
    String getHello(String name);

}  