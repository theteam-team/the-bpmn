package com.theteam.snodes;

import java.util.UUID;

public abstract class SNode
{
    protected String nId;
    protected UUID uuid;
    String type;

    public abstract String getNId();
    abstract void setNId(String id);

    abstract UUID getUUId();

    public abstract String getType(); 

    abstract void setType(String type);

    abstract String getPreviousNode();

    abstract void setPreviousNode(String previousNode);

    abstract String getNextNode();

    abstract void setNextNode(String nextNode);

    abstract void run();
}