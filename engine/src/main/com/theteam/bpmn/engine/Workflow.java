package com.theteam.bpmn.engine;

import java.util.HashMap;
import java.util.Map;

public class Workflow
{
    public static Map<String, Elist> workflows = new HashMap<>();

    public static Elist getWorkflow(String name) { return workflows.get(name); }

    public static void addWorkflow(Elist l) { workflows.put(l.sNodes.getName(), l); }

}