package com.theteam.bpmn.engine.enode;

import com.theteam.bpmn.engine.Elist;
import com.theteam.bpmn.engine.Workflow;
import com.theteam.bpmn.engine.io.EVariable;
import com.theteam.snodes.SNode;
import com.theteam.snodes.STestNode;

public class ETest extends ENode
{

    private STestNode sTest;

    int var1;
    int var2;

    Boolean error = false;

    public ETest(SNode sNode, Elist list)
    {
        this.sNode = sNode;
        sTest = (STestNode) sNode;

        this.list = list;

        var1 = 0;
        var2 = 0;

        //System.out.println(sTest.getInput());

    }

    @Override
    public void run(Elist l)
    {
        System.out.println("\nTest Node Running");

        EVariable e = list.getVariable(sTest.getInput());

        String s = e.getValue();

        if(s.contains(" ") || s.contains(","))
        {
            String[] a = s.split(",");

            int len = a.length;

            //System.out.println(a[0]);
            //System.out.println(a[1]);
            
            if(len == 2)
            {

                if(list.getVariable(a[0]) == null)
                    var1 = Integer.valueOf(a[0]);
                else
                {
                    EVariable k = list.getVariable(a[0]);
                    String d = k.getValue();
                    //System.out.println(k.getSVariable().getName());
                    //System.out.println(d);

                    var1 = Integer.valueOf(d.split(",")[0]);
                }

                if(list.getVariable(a[1]) == null)
                    var2 = Integer.valueOf(a[1]);
                else
                {
                    EVariable k = list.getVariable(a[1]);
                    String d = k.getValue();
                    //System.out.println(k.getSVariable().getName());
                    //System.out.println(d);

                    var2 = Integer.valueOf(d.split(",")[0]);
                }

                //var2 = Integer.valueOf(a[1]);
            }
            else
            {
                error = true;
            }

        }
        else
        {
            error = true;
        }
        
        if(!error)
        {
            int sum = var1 + var2;
            
            if(sTest.getOutput() != null)
            {
                EVariable o = list.getVariable(sTest.getOutput());
                o.setValue(String.valueOf(sum));

            }

            System.out.println("\nThe sum: " + sum);

        }

        else
        {
            System.out.println("\nError Happened");
        }

        for(ENode n : l.eNodes)
        {
            if(n.getSNode().getNId().equals(getSNode().getNextNode()))
            {
                n.run(l);
                return;
            }
        }
    }
}