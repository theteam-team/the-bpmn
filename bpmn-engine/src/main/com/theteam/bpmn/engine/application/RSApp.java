package com.theteam.bpmn.engine.application;  

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;

import com.theteam.bpmn.engine.api.Load;
import com.theteam.bpmn.engine.api.Workflow;

public class RSApp extends Application
{
    public Set<Class<?>> getClasses()
    {
        Set<Class<?>> s = new HashSet<Class<?>>();
        
        s.add(Load.class);
        s.add(Workflow.class);

        return s;
    }
}