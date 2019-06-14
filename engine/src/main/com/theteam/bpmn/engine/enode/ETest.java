package com.theteam.bpmn.engine.enode;

import com.theteam.bpmn.engine.Elist;
import com.theteam.bpmn.engine.Workflow;
import com.theteam.bpmn.engine.io.EVariable;
import com.theteam.bpmn.engine.observers.WorkflowObserver;
import com.theteam.snodes.SCondition;
import com.theteam.snodes.SNode;
import com.theteam.snodes.STestNode;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.google.gson.JsonObject;
import com.microsoft.signalr.*;

public class ETest extends ENode
{

    private STestNode sTest;

    HubConnection hubConnection;
	Map<String, Boolean> notifications = new HashMap<String, Boolean>();

    /*

    int var1;
    int var2;

    Boolean error = false;

    */

    public ETest(SNode sNode, Elist list)
    {
        this.sNode = sNode;
        sTest = (STestNode) sNode;

        this.list = list;

        /*

        var1 = 0;
        var2 = 0;

        */

        //System.out.println(sTest.getInput());

    }

    public void sendNotification() 
	{
		UUID uuid = UUID.randomUUID();
		String notificationId = uuid.toString();
		notifications.put(notificationId, false);
		hubConnection.send("sendNotification","Adminstrator", notificationId, "please approve this order blablabla" );
		System.out.println("sending Notification "+ notificationId);
	}
	
	public void delay(int delay) 
	{
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}

    @Override
    public void run(Elist l)
    {
        System.out.println("\nTest Node Running");

        JsonObject obj = new JsonObject();

        obj.addProperty("workflowName", l.sNodes.getName());
        obj.addProperty("processName", sNode.getType());
        obj.addProperty("processID", sNode.getNId());

        Workflow.wo.updateVal(obj.toString());

        hubConnection = HubConnectionBuilder.create("http://localhost:8888/NotificationHub").build();
		
		hubConnection.start();
		
		while(hubConnection.getConnectionState().name() != "CONNECTED") 
		{
			System.out.print(hubConnection.getConnectionState().name());
			delay(50);
		}
		
		hubConnection.send("AddToGroup", "Engine");
		//send the notification
		sendNotification();
		
		
		//on receiving Response on a specific notification 
		hubConnection.on("notificationResponse",(notificationId, response)->{
			notifications.put(notificationId, response);
			String str = "";
            String res = "";
            
			if(response) 
			{
                str = "Approved";
                res = "true";
			}
            else
            {
                str = "disapproved";
                res = "false";
                
            }
            System.out.println("response for " + notificationId + " is " + str);
            
            for(ENode n : l.eNodes)
            {
                if(n.getSNode().getNId().equals(getSNode().getNextNode()))
                {
                    if(n.getSNode().getType().equals("CONDITION"))
                    {
                        ECondition db = (ECondition) n;
                        SCondition sdb = (SCondition) db.getSNode();

                        sdb.setExpression(res);

                    }
                    n.run(l);
                    return;
                }
            }
		},String.class, Boolean.class);



        /*

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

        */
    }
}