package com.theteam.snodes;

public final class Types
{

    public static enum NodeTypes 
    {
        START,
        END,
        TASK,
        DB,
        EXTERNAL_EVENT,
        TIMER_EVENT,
        SCRIPT,
        TEST
    }

    public static enum ConnectionTypes 
    {
        NORMAL
    }


    public static String[] Nodes =
    {
        "START",
        "END",
        "TASK",
        "DB",
        "EXTERNAL_EVENT",
        "TIMER_EVENT",
        "SCRIPT",
        "TEST"
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
            case DB:
                return Nodes[3];
            case EXTERNAL_EVENT:
                return Nodes[4];
            case TIMER_EVENT:
                return Nodes[5];
            case SCRIPT:
                return Nodes[6];
            case TEST:
                return Nodes[7];
                
            default:
                return "NOT FOUND";

        }
    }
}