package com.theteam.bpmn.engine.observers;

import java.util.ArrayList;

import javax.observer.ValueChangedEvent;

import com.guigarage.observer.BasicObservable;
import com.theteam.bpmn.engine.Workflow;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class WorkflowObserver extends BasicObservable<String>
{

    Logger logger = LoggerFactory.getLogger(WorkflowObserver.class);

    public WorkflowObserver()
    {
        //Logger logger = LoggerFactory.getLogger(WorkflowObserver.class);
        //onChanged(e -> logger.info(e.getValue()));

        onChanged(e -> sendToServer(e));
    }

    public void updateVal(String s)
    {
        updateValue(s);
    }

    public void sendToServer(ValueChangedEvent<? extends String> e)
    {
        String json = e.getValue();
        logger.info(json);

        JSONObject orgJson = new JSONObject(json);

        String workflowName = orgJson.getString("workflowName");
        String instanceID = orgJson.getString("instanceID");

        JSONArray jsonArr = orgJson.getJSONArray("processes");

        ArrayList<String> processIds = new ArrayList<>();

        for(int i = 0 ; i < jsonArr.length() ; i++){
            JSONObject p = (JSONObject) jsonArr.get(i);
            String processID = p.getString("processID");
            processIds.add(processID);
           
        }

        System.out.println("Sending new data to hub");
        if(Workflow.mon.getHubConnection().getConnectionState().name() == "CONNECTED")
        {
            Workflow.mon.getHubConnection().send("UpdateExecution", workflowName, instanceID, processIds);
            System.out.println("Sending new data to hub  - done");

        }

        /*
        System.out.println("______TEST_____");
        System.out.println(workflowName);
        System.out.println(instanceID);
        for (String var : processIds) {
            
            System.out.println(var);
        }
        System.out.println("______TEST_____");
        */
    }

    /*
    public static void main(String[] args) {
        WorkflowObserver observer = new WorkflowObserver();
        observer.onChanged(e -> System.out.println(e.getValue()));
    }
    */

    

}
