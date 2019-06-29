package com.theteam.bpmn.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.gson.JsonObject;
import com.theteam.bpmn.engine.observers.MessageObserver;
import com.theteam.bpmn.engine.observers.ServerObserver;
import com.theteam.bpmn.engine.observers.TaskObserver;
import com.theteam.bpmn.engine.observers.WorkflowObserver;

public class Workflow
{

    public static Map<String, Elist> workflows = new HashMap<>();

    public static Map<String, ArrayList<String>> runningWorkflows = new HashMap<>();
    public static Map<String, ArrayList<String>> processesRun = new HashMap<>();

    public static ArrayList<JsonObject> businessTasks = new ArrayList<>();

    public static Elist getWorkflow(String name) { return workflows.get(name); }

    public static Set<String> getWorkflowsNames() { return workflows.keySet(); }

    public static void addWorkflow(Elist l) { workflows.put(l.sNodes.getName(), l); }

    public static WorkflowObserver workflowObserver = new WorkflowObserver();
    public static TaskObserver notificationObserver = new TaskObserver();
    public static ServerObserver serverObserver = new ServerObserver();
    public static MessageObserver messageObserver = new MessageObserver();

    public static Monitor mon;

}