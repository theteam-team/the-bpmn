package com.theteam.bpmn.engine;

import java.util.*;


import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.HubConnectionBuilder;

import io.reactivex.Single;

/**
 * Hello world!
 *
 */
public class Monitor
{
		HubConnection hubConnection;
		List<String> workflows = new ArrayList<String>(); 
		Map<String, ArrayList<String>> workdfowInstances = new HashMap<String, ArrayList<String>>();
		Map<String, ArrayList<String>> instanceExecution = new HashMap<String, ArrayList<String>>();
		String[] nodes = {"aa2c092e-c67e-473a-825c-14e56ee4adbd","1fca3740-7a53-48af-9286-b90b68b0ff58",
				"5c7c4013-dc5d-4ef0-b167-d657f6e2aeea","f65667fa-9cb5-4ef9-80fd-1db526bf03dc",
				"08ec8b9d-fc04-4dbf-9e90-5e5d9c2987b1","09028f4b-9e3b-4535-8e4f-8a222d37cc6a",
				"9f0e5850-b69b-4e01-a3d9-ff7ca4944ff0","fe84bce5-d113-4561-b565-188a3cb70b0d","573fef8c-9936-4906-ab00-5d2f22acbbf1"};

    	public void moon(String[] args) {
    		//create the connection with token validation don't worry the token expiration date is after 100 years
    		 hubConnection = HubConnectionBuilder.create("http://localhost:8888/DeployWorkflowHub")
    				 .withAccessTokenProvider(Single.defer(() -> {
    				 
    					 // TODO request Token the put it 
    				        return Single.just(/*put your token here*/"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhYUBhYS5hYSIsImp0aSI6Ijk1ZGM2MmM2LWFhNmEtNGI0OC05MWUyLTk3MGIyNWVmNWE1YiIsInVuaXF1ZV9uYW1lIjoiYWRtaW4iLCJodHRwOi8vc2NoZW1hcy5taWNyb3NvZnQuY29tL3dzLzIwMDgvMDYvaWRlbnRpdHkvY2xhaW1zL3JvbGUiOiJBZG1pbnN0cmF0b3IiLCJleHAiOjE1NTc1MTM2NjUsImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODg4OCIsImF1ZCI6InVzZXJzIn0.zv6NVd9ZHPlHtTEuJp6bjfpxSYbnqUzPfmlh9oX_Jpg");
    				    })).build();
    		
    		hubConnection.start();
    		//wait for the connection to be established
    		while(hubConnection.getConnectionState().name() != "CONNECTED") 
    		{
    			System.out.print(hubConnection.getConnectionState().name());
    			delay(50);
    		}
    		hubConnection.send("AddToGroup","Engine");
    		delay(50);
    		///initialize the deployed list
    		hubConnection.send("GetCurrentDeployed");
    		hubConnection.on("InitializeDeployList", (deployList)->{InitializeDeployList(deployList);}, ArrayList.class);
    		hubConnection.on("updateDeployList", (id, name, workFlowStr) -> {			
    			updateDeployList( id,  name,  workFlowStr); 
    		}, String.class, String.class, String.class);
    		
    		
    		//initialize Workflow instances in the client 
    		hubConnection.on("GetRunningWorkFlowInstances", (workFlowID, ConnectionId)->
    		{
    			System.out.print(workdfowInstances.get(workFlowID));
    			hubConnection.send("InitializeRuningInstances",workFlowID, workdfowInstances.get(workFlowID), ConnectionId);
    		}, String.class, String.class);
    		
    		//initialize the execution state for the given instance in the given workflow
    		hubConnection.on("InitializeExecution",(workflowID, InstanceId) ->
    		{
    			hubConnection.send("UpdateExecution", workflowID, InstanceId, instanceExecution.get(InstanceId));
    		},String.class, String.class);	
    		
    		
    		
    		
    	}
    	
    	//simulate a workflow execution  
    	public void simulateExecution() 
    	{
    		for(int i = 1; i < nodes.length; ++i) 
    		{
    			
    			for(int j = 0; j< workflows.size(); ++j) 
    			{
    				
    				for(int k = 0; k <  workdfowInstances.get(workflows.get(j)).size(); ++k) 
    				{
    					for(String ins : workdfowInstances.get(workflows.get(j)))
    					{
    						instanceExecution.get(ins).add(nodes[i]);
    						
    						hubConnection.send("UpdateExecution", workflows.get(j), ins, instanceExecution.get(ins));
    					}
    					delay(50);
    				}
    			}
    		}
    	}
    	public void updateDeployList(String id, String name, String workFlowStr) 
    	{
    		
    		InitializeInstances(id);
    		
    		
    	}
    	public void InitializeDeployList(ArrayList<Map<String, String>>  list) 
    	{
    
    		for(int i = 0; i < list.size(); ++i) 
    		{
    			String workflowId = list.get(i).get("id");  
    			InitializeInstances(workflowId);
    			
    		}
    		
    		simulateExecution();
    		
    	
    	}
    	public void AddInstance(String workflowId) 
    	{
    		UUID uuid = UUID.randomUUID();
    		String InstanceId = uuid.toString();
    		ArrayList<String> arrStr = new ArrayList<String>();
			arrStr.add(InstanceId);
			workdfowInstances.put(workflowId, arrStr);
			instanceExecution.get(InstanceId).add(nodes[0]);
			
			
    	}
    	
    	public void InitializeInstances(String workflowId) 
    	{
    		workflows.add(workflowId);
    		for(int i = 0; i<=2; ++i) 
    		{
	    		UUID uuid = UUID.randomUUID();
	    		String InstanceId = uuid.toString();
	    		ArrayList<String> arrNod = new ArrayList<String>();
	    		arrNod.add(nodes[0]);
	    		if(workdfowInstances.containsKey(workflowId))
	    		{

	    			workdfowInstances.get(workflowId).add(uuid.toString());
	    			instanceExecution.put(InstanceId, arrNod);
	    		}
	    		else 
	    		{
	    			System.out.print("here");
	    			ArrayList<String> arrIns = new ArrayList<String>();
	    			
	    			arrIns.add(InstanceId);
	    			
	    			workdfowInstances.put(workflowId, arrIns);
	    			instanceExecution.put(InstanceId, arrNod);
	    			
	    		}
	    		hubConnection.send("AddRunningInstance", workflowId, InstanceId, instanceExecution.get(InstanceId));
    		}
    		//System.out.print(list);
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
    
}
