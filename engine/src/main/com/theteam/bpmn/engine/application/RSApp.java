package com.theteam.bpmn.engine.application;  

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;

import com.theteam.bpmn.engine.api.Load;
import com.theteam.bpmn.engine.api.StartWorkflow;

/** Add classes manually to end point /api */
public class RSApp extends Application
{
    public Set<Class<?>> getClasses()
    {
        Set<Class<?>> s = new HashSet<Class<?>>();
        
        s.add(Load.class);
        s.add(StartWorkflow.class);

        return s;
    }
}