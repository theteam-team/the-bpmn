package com.theteam.jsondata;

import java.util.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "data" })
public class SJsonDataList
{
    @XmlElement(name = "data")
    private List<SJsonData> data;

    HashMap<String, SJsonData> mapNodes;

    public SJsonDataList()
    {
        data = new ArrayList<SJsonData>();
        mapNodes = new HashMap<>();
    }

    public List<SJsonData> getData()
    {
        return data;
    }
    
    public void addJsonData(SJsonData var)
    {
        mapNodes.put(var.getNId(), var);
        data.add(var);
    }

    public void removeJsonData(String jsonDataId)
    {
        SJsonData jsonData = mapNodes.get(jsonDataId);
        data.remove(jsonData);
    }

    public SJsonData getJsonDataById(String id)
    {
        return mapNodes.get(id);
    }

}

