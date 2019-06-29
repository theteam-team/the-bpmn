package com.theteam.bpmn.engine.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.theteam.bpmn.engine.Monitor;
import com.theteam.bpmn.engine.Workflow;

public class MyServletContextListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
      System.out.println("System Destroyed");
      //Notification that the servlet context is about to be shut down.   
    }
  
    @Override
    public void contextInitialized(ServletContextEvent arg0) {

      System.out.println("System Initialized");
      //Workflow.mon = new Monitor();
      // do all the tasks that you need to perform just after the server starts
  
      //Notification that the web application initialization process is starting
    }
  
  }