package com.theteam.bpmn.engine.observers;

import com.guigarage.observer.BasicObservable;


public class WorkflowObserver extends BasicObservable<String>
{

    public WorkflowObserver()
    {
        //updateValue("Hello");

        onChanged(e -> System.out.println(e.getValue()));
    }

    public void updateVal(String s)
    {
        updateValue(s);
    }

    
    
    /*
    public static void main(String[] args) {
        WorkflowObserver observer = new WorkflowObserver();
        observer.onChanged(e -> System.out.println(e.getValue()));
    }
    */
    
    

}
