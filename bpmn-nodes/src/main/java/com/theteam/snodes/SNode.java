package com.theteam.snodes;

import java.util.UUID;

public abstract class SNode
{
	//changed all 'Get' functions to public
	
    protected String nId;
    protected UUID uuid;
    String type;

    public abstract String getNId();
    abstract void setNId(String id);

    public abstract UUID getUUId();

    public abstract String getType(); 

    public abstract void setType(String type);

    public abstract String getPreviousNode();

    abstract void setPreviousNode(String previousNode);

    public abstract String getNextNode();

    abstract void setNextNode(String nextNode);

    //public abstract void run();
    
    public abstract int run();
}
