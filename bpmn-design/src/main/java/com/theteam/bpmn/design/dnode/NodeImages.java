package com.theteam.bpmn.design.dnode;

import com.theteam.bpmn.design.App;

import java.util.HashMap;

import javafx.scene.image.Image;

public final class NodeImages
{

    public static HashMap<String, Image> nodeImages = new HashMap<>();

    private static Image start = 
        new Image(App.class.getResource("/images/nodes/start.png").toString());
    private static Image start_chosen = 
        new Image(App.class.getResource("/images/nodes/start_chosen.png").toString());
    private static Image end = 
        new Image(App.class.getResource("/images/nodes/end.png").toString());
    private static Image end_chosen = 
        new Image(App.class.getResource("/images/nodes/end_chosen.png").toString());
    private static Image service_task = 
        new Image(App.class.getResource("/images/nodes/service_task.png").toString());
    private static Image service_task_chosen = 
        new Image(App.class.getResource("/images/nodes/service_task_chosen.png").toString());

    public static void initializeImages()
    {
        nodeImages.put("start", start);
        nodeImages.put("start_chosen", start_chosen);
        nodeImages.put("end", end);
        nodeImages.put("end_chosen", end_chosen);
        nodeImages.put("service_task", service_task);
        nodeImages.put("service_task_chosen", service_task_chosen);
    }
}