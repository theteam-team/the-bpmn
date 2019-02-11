package com.theteam.snodes;

public final class Types
{

    public static enum NodeTypes 
    {
        START,
        END,
        TASK
    }

    public static enum ConnectionTypes 
    {
        NORMAL
    }


    public static String[] Nodes =    
                        {
                        "START",
                        "END",
                        "TASK"
                        };
    
    
    public static String NodeType(Types.NodeTypes type)
    {
        switch (type)
        {

            case START:
                return Nodes[0];
            case END:
                return Nodes[1];
            case TASK:
                return Nodes[2];
                
            default:
                return "NOT FOUND";

        }
    }
}