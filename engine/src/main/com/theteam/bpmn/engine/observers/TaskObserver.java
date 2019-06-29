package com.theteam.bpmn.engine.observers;

import javax.observer.ValueChangedEvent;

import com.guigarage.observer.BasicObservable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TaskObserver extends BasicObservable<String>
{
    Logger logger = LoggerFactory.getLogger(TaskObserver.class);

    public TaskObserver()
    {
        // onChanged(e -> logger.info(e.getValue()));
        onChanged(e -> sendToServer(e));
    }

    public void updateVal(String s)
    {
        updateValue(s);
    }

    public void sendToServer(ValueChangedEvent<? extends String> e)
    {

    }


}
