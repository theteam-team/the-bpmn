package com.theteam.bpmn.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.microsoft.signalr.*;


import io.reactivex.Single;

public class Notification {

	HubConnection hubConnection;
	Map<String, Boolean> notifications = new HashMap<String, Boolean>();

	public void moon(String[] args) {
		// TODO Auto-generated method stub
			
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
			String str;
			if(response) 
			{
				str = "Approved";
			}
			else 
				str = "disapproved";
			System.out.println("response for " + notificationId + " is " + str);
		},String.class, Boolean.class);
		
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
