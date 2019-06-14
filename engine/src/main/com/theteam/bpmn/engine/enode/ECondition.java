package com.theteam.bpmn.engine.enode;

import com.google.gson.JsonObject;
import com.theteam.bpmn.engine.Elist;
import com.theteam.bpmn.engine.Workflow;
import com.theteam.bpmn.engine.io.EVariable;
import com.theteam.bpmn.engine.scan.Scan;
import com.theteam.snodes.SCondition;
import com.theteam.snodes.SNode;

import javax.script.*;

public class ECondition extends ENode
{

    private SCondition sCondition;
    

    public ECondition(SNode sNode, Elist list)
    {
        this.sNode = sNode;
        this.list = list;

        sCondition = (SCondition) this.sNode;
    }

    @Override
    public void run(Elist l)
    {
        System.out.println("\nCondition Node Running");

        JsonObject ob = new JsonObject();

        ob.addProperty("workflowName", l.sNodes.getName());
        ob.addProperty("workflowID", l.getID());
        ob.addProperty("processName", sNode.getType());
        ob.addProperty("processID", sNode.getNId());

        Workflow.wo.updateVal(ob.toString());

        Scan s = new Scan(sCondition.getExpression(), l);
        String condition = s.getFinalString();

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");
        
        boolean conEvaluated;

        try
        {
            conEvaluated = (boolean) engine.eval(condition);

        } catch(Exception e)
        { 
            System.out.println(e);

            System.out.println("Condition wrong");
            return;
        }


        if(conEvaluated)
        {
            for(ENode n : l.eNodes)
            {
                if(n.getSNode().getNId().equals(getSNode().getNextNode()))
                {
                    n.run(l);
                    return;
                }
            }
        }
        else
        {
            for(ENode n : l.eNodes)
            {
                if(n.getSNode().getNId().equals(getSNode().getNextNode1()))
                {
                    n.run(l);
                    return;
                }
            }
        }
    }
}