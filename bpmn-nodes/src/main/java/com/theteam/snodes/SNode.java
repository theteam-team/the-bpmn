package com.theteam.snodes;

import java.util.UUID;

public abstract class SNode
{
    protected String nId;
    protected UUID uuid;
    protected String type;

    public abstract String getNId();
    public abstract void setNId(String id);

    public abstract UUID getUUId();

    public abstract String getType(); 
    public abstract void setType(String type);

    public abstract String getPreviousNode();

    public abstract void setPreviousNode(String previousNode);

    // needs implementation
    // abstract void setPreviousNode(SNode previousNode);

    public abstract String getNextNode();
    public abstract void setNextNode(String nextNode);

    public abstract void setOutput(String output);
    public abstract void setInput(String input);
    
    // needs implementation
    // abstract void setNextNode(SNode nextNode);

    //public abstract void run();
}