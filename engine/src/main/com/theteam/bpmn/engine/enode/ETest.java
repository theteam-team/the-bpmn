package com.theteam.bpmn.engine.enode;

import com.theteam.bpmn.engine.Elist;
import com.theteam.bpmn.engine.io.EVariable;
import com.theteam.snodes.SNode;
import com.theteam.snodes.STestNode;

public class ETest extends ENode
{

    private STestNode sTest;

    int var1;
    int var2;

    Boolean error = false;

    public ETest(SNode sNode)
    {
        this.sNode = sNode;
        sTest = (STestNode) sNode;

        var1 = 0;
        var2 = 0;

        //System.out.println(sTest.getInput());

    }

    @Override
    public void run()
    {
        System.out.println("Test Node Running");

        EVariable e = Elist.getVariable(sTest.getInput());

        String s = e.getValue();

        if(s.contains(" ") || s.contains(","))
        {
            String[] a = s.split(",");

            int len = a.length;

            //System.out.println(a[0]);
            //System.out.println(a[1]);
            
            if(len == 2)
            {

                if(Elist.getVariable(a[0]) == null)
                    var1 = Integer.valueOf(a[0]);
                else
                {
                    EVariable k = Elist.getVariable(a[0]);
                    String d = k.getValue();
                    //System.out.println(k.getSVariable().getName());
                    //System.out.println(d);

                    var1 = Integer.valueOf(d.split(",")[0]);
                }

                var2 = Integer.valueOf(a[1]);
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
                EVariable o = Elist.getVariable(sTest.getOutput());
                o.setValue(String.valueOf(sum));

            }

            System.out.println("The sum: " + sum);

        }

        else
        {
            System.out.println("Error Happened");
        }
    }
}