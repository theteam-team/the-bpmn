package com.theteam.positions;

import java.util.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "position" })
public class SPositionList
{
    @XmlElement(name = "position")
    private List<SPosition> position;

    HashMap<String, SPosition> mapNodes;

    HashMap<String, SPosition> mapNodes1;

    public SPositionList()
    {
        position = new ArrayList<SPosition>();
        mapNodes = new HashMap<>();
        mapNodes1 = new HashMap<>();
    }

    public List<SPosition> getPositionsList()
    {
        return position;
    }
    
    public void addPosition(SPosition var)
    {
        //mapNodes.put(var.getNId(), var);
        mapNodes1.put(var.getNodeId(), var);

        position.add(var);
    }


    public SPosition getPositionById(String id)
    {
        return mapNodes.get(id);
    }

    public void removePosition(String id)
    {
        SPosition pos = mapNodes1.get(id);
        position.remove(pos);
    }

    public void updatePosition(String id, String x, String y)
    {
        SPosition pos = mapNodes1.get(id);
        pos.setX(x);
        pos.setY(y);
    }

    public void clear()
    {
        position.clear();
        mapNodes.clear();
        mapNodes1.clear();
    }
}

