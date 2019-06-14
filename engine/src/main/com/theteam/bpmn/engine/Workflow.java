package com.theteam.bpmn.engine;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.theteam.bpmn.engine.observers.WorkflowObserver;

public class Workflow
{
    public static Map<String, Elist> workflows = new HashMap<>();

    public static Map<String, Integer> runningWorkflows = new HashMap<>();

    public static Elist getWorkflow(String name) { return workflows.get(name); }

    public static Set<String> getWorkflowsNames() { return workflows.keySet(); }

    public static void addWorkflow(Elist l) { workflows.put(l.sNodes.getName(), l); }

    public static WorkflowObserver wo = new WorkflowObserver();
    

}