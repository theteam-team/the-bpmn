package com.theteam.bpmn.engine;

import java.util.*;

import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.HubConnectionBuilder;

import io.reactivex.Single;


public class Monitor
{
		public HubConnection hubConnection;
		
    	public Monitor() {
    		//create the connection with token validation don't worry the token expiration date is after 100 years
    		 hubConnection = HubConnectionBuilder.create("http://localhost:5001/DeployWorkflowHub")
    				 /*.withAccessTokenProvider(Single.defer(() -> {
    				 
    					 // TODO request Token the put it 
    				        return Single.just(/*put your token here"CfDJ8ILPBX-GLhFAhs0mV7u-2ypYX2yGxTouybnBkwFd_gWViN2xmlp9kMedp_ZBCmOBJwU4JBRpD_HKQ7Yz56STlIpcSLaf5Yeq_8hohzoZV7dGJb2opQJ2UCwv40xV5Ty4RkmVy19IxAUFRfG4DKvf2ApsoWKKs1iBet9u_4klmNqHdZe58b8ii5gFbEmCVFV1yg");
    				    })*/.build();
    		
    		hubConnection.start();
    		//wait for the connection to be established
    		while(hubConnection.getConnectionState().name() != "CONNECTED") 
    		{
    			System.out.print(hubConnection.getConnectionState().name());
    			delay(50);
    		}
    		hubConnection.send("AddToGroup","Engine");
    		delay(50);
    		//hubConnection.send("GetCurrentDeployed");
    		///initialize the deployed list
			hubConnection.send("InitializeDeployList"); // when the engine starts
			
			
			//String workFlowName = "aa";
			//String InstanceId = "aa";

			//int runningInstances = 5;
		
			// LA LA LA
    		//hubConnection.send("updateDeployList" , workFlowName, runningInstances); //when a new workFlow Deployed
		
			/* this should be invoked when the engine starts and you should make an api to 
			get Running instance for a specific workflow and return {"instance1","instance2",,,}*/
			//hubConnection.send("InitializeRuningInstances");
    		
    		//Add running instance in the client this should be invoked when the A new Instance Created
			// hubConnection.send("AddRunningInstance", workFlowName, InstanceId);
			
			// ArrayList<String> nodes = new ArrayList<String>(); //the executed nodes for the instance in the workflow
    		// this should be invoked At every update in a execution 
			
			
			///this a listener 
			hubConnection.on("InitializeExecution",(workflowID, Instance_Id) ->
    		{
				ArrayList<String> nodes = Workflow.processesRun.get(Instance_Id);
    			hubConnection.send("UpdateExecution", workflowID, Instance_Id, nodes);
    		},String.class, String.class);

    	}

    	public void delay(int delay) 
    	{
    		try {
    			Thread.sleep(delay);
    		} catch (InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
		}
		
		public HubConnection getHubConnection()
		{
			return hubConnection;
		}
    
}
