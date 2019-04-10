package com.theteam.bpmn.engine;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.theteam.ElementsList;
import com.theteam.bpmn.engine.enode.*;
import com.theteam.bpmn.engine.io.EVariable;
import com.theteam.snodes.SNodeList;
import com.theteam.io.SVariablesList;

/**
 * TO BE CHANGED
 * used to load and use the list of Nodes, Variables
 */
public final class Elist
{
    public static ElementsList allList;
    public static SNodeList sNodes;
    public static SVariablesList variables;

    public static List<ENode> eNodes = new LinkedList<>();
    public static Map<String, EVariable> eVariables = new HashMap<>();

    public static EStart eStart = null;

    public static ENode getStartNode()
    {
        for (ENode var : eNodes) {
            if(var.getSNode().getType().equals("START"))
            {
                return var;
            }
        }

        return null;
    }

    public static EVariable getVariable(String name) { return eVariables.get(name); }

    public static void addVariable(EVariable eVar)
    {
        eVariables.put(eVar.getSVariable().getName(), eVar);
    }
}