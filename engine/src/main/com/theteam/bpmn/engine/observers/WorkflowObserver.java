package com.theteam.bpmn.engine.observers;

import com.guigarage.observer.BasicObservable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class WorkflowObserver extends BasicObservable<String>
{

    public WorkflowObserver()
    {
        Logger logger = LoggerFactory.getLogger(WorkflowObserver.class);
        onChanged(e -> logger.info(e.getValue()));

        //onChanged(e -> sendToServer(e));
    }

    public void updateVal(String s)
    {
        updateValue(s);
    }

    public void sendToServer()
    {

    }

    /*
    public static void main(String[] args) {
        WorkflowObserver observer = new WorkflowObserver();
        observer.onChanged(e -> System.out.println(e.getValue()));
    }
    */

}
