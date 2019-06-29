package com.theteam.taskdata;

import java.util.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "data" })
public class STaskDataList
{
    @XmlElement(name = "data")
    private List<STaskData> data;

    HashMap<String, STaskData> mapNodes;

    public STaskDataList()
    {
        data = new ArrayList<STaskData>();
        mapNodes = new HashMap<>();
    }

    public List<STaskData> getData()
    {
        return data;
    }
    
    public void addJsonData(STaskData var)
    {
        mapNodes.put(var.getNId(), var);
        data.add(var);
    }

    public void removeJsonData(String jsonDataId)
    {
        STaskData jsonData = mapNodes.get(jsonDataId);
        data.remove(jsonData);
    }

    public STaskData getJsonDataById(String id)
    {
        return mapNodes.get(id);
    }

    public void reset()
    {
        data.clear();
        mapNodes.clear();
    }

}

