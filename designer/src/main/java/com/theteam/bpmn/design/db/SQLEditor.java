package com.theteam.bpmn.design.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import  com.theteam.bpmn.design.db.SQLKEYS;

public class SQLEditor
{
    private static final Map<String, SQLKEYS> selectKeywords;
    private static final Map<SQLKEYS, String> selectKeywords1;

    static
    {
        selectKeywords = new HashMap<>();
        selectKeywords1 = new HashMap<>();

        selectKeywords.put("SELECT",     SQLKEYS.SELECT);
        selectKeywords.put("FROM",       SQLKEYS.FROM);
        selectKeywords.put("WHERE",      SQLKEYS.WHERE);
        selectKeywords.put("GROUP BY",   SQLKEYS.GroupBy);
        selectKeywords.put("HAVING",     SQLKEYS.Having);
        selectKeywords.put("ORDER BY",   SQLKEYS.OrderBy);
        selectKeywords.put("AND",        SQLKEYS.AND);
        selectKeywords.put("OR",         SQLKEYS.OR);
        selectKeywords.put("END",        SQLKEYS.END);

        selectKeywords1.put(SQLKEYS.SELECT,     "SELECT");
        selectKeywords1.put(SQLKEYS.FROM,       "FROM");
        selectKeywords1.put(SQLKEYS.WHERE,      "WHERE");
        selectKeywords1.put(SQLKEYS.GroupBy,    "GROUP BY");
        selectKeywords1.put(SQLKEYS.Having,     "HAVING");
        selectKeywords1.put(SQLKEYS.OrderBy,    "ORDER BY");
        selectKeywords1.put(SQLKEYS.AND,        "AND");
        selectKeywords1.put(SQLKEYS.OR,         "OR");
        selectKeywords1.put(SQLKEYS.END,        "END");
    }

    private State currentState = new State(SQLKEYS.SELECT);
    private ArrayList<SQLKEYS> visitedStates = new ArrayList<>();

    public SQLEditor()
    {
        visitedStates.add(SQLKEYS.SELECT);
    }

    public SQLKEYS getState()
    {
        return currentState.getState();
    }

    public String getStrState()
    {
        return selectKeywords1.get(currentState.getState());
    }

    public List<SQLKEYS> getNextStates()
    {
        return currentState.getNextStates();
    }

    public List<String> getStrNextStates()
    {
        List<String> temp = new ArrayList<>();

        for (SQLKEYS var : currentState.getNextStates()) {
            
            temp.add(selectKeywords1.get(var));
        }
        return temp;
    }

    public boolean ended()
    {
        if (currentState.getState() == SQLKEYS.END)
            return true;
        return false;
    }

    public void setNextState(SQLKEYS s)
    {
        currentState.setState(s);
    }

    public void setNextState(String s)
    {
        currentState.setState(s);
    }

    public void goPrevState()
    {
        System.out.println();
        System.out.println("_____VISITED_BEFORE____");
        for (SQLKEYS var : visitedStates)
        System.out.println(var);
        System.out.println("_____VISITED_BEFORE____");
        System.out.println();
        
        visitedStates.remove(visitedStates.size()-1);
        
        if(ended())
        visitedStates.remove(visitedStates.size()-1);
        
        SQLKEYS k = visitedStates.get(visitedStates.size()-1);
        currentState.setSState(k);
        
        
        System.out.println("_____VISITED_AFTER____");
        for (SQLKEYS var : visitedStates)
        System.out.println(var);
        System.out.println("_____VISITED_AFTER____");
        System.out.println();
        
    }

    private class State
    {
        SQLKEYS sqlState;

        public State(SQLKEYS sqlState)
        {
            this.sqlState = sqlState;
        }

        public void setSState(SQLKEYS s)
        {
            sqlState = s;
        }

        public void setState(SQLKEYS s)
        {

            if(currentState.getState() == SQLKEYS.END)
                return;

            if(currentState.getNextStates().contains(s))
            {
                visitedStates.add(s);
                sqlState = s;
            }
        }

        public void setState(String s)
        {
            if(currentState.getState() == SQLKEYS.END)
                return;

            SQLKEYS skey = selectKeywords.get(s);

            if(currentState.getNextStates().contains(skey))
            {
                visitedStates.add(skey);
                sqlState = skey;
            }
        }

        public SQLKEYS getState()
        {
            return sqlState;
        }

        public List<SQLKEYS> getNextStates()
        {
            List<SQLKEYS> temp = new ArrayList<>();

            switch (sqlState) {

                case SELECT:
                    //visitedStates.add(SQLKEYS.SELECT);
                    temp.add(SQLKEYS.FROM);
                    return temp;

                case FROM:
                    //visitedStates.add(SQLKEYS.FROM);
                    temp.add(SQLKEYS.END);

                    temp.add(SQLKEYS.WHERE);
                    temp.add(SQLKEYS.GroupBy);
                    temp.add(SQLKEYS.OrderBy);
                    
                    return temp;

                case WHERE:
                    //visitedStates.add(SQLKEYS.WHERE);

                    temp.add(SQLKEYS.END);

                    temp.add(SQLKEYS.OR);
                    temp.add(SQLKEYS.AND);

                    if(!visitedStates.contains(SQLKEYS.GroupBy))
                        temp.add(SQLKEYS.GroupBy);

                    if(!visitedStates.contains(SQLKEYS.OrderBy))
                        temp.add(SQLKEYS.OrderBy);

                    if(!visitedStates.contains(SQLKEYS.Having) &&
                        visitedStates.contains(SQLKEYS.GroupBy))
                        temp.add(SQLKEYS.Having);

                    return temp;

                case GroupBy:
                    //visitedStates.add(SQLKEYS.GroupBy);

                    temp.add(SQLKEYS.END);

                    if(!visitedStates.contains(SQLKEYS.WHERE))
                        temp.add(SQLKEYS.WHERE);

                    if(!visitedStates.contains(SQLKEYS.OrderBy))
                        temp.add(SQLKEYS.OrderBy);

                    if(!visitedStates.contains(SQLKEYS.Having) &&
                        visitedStates.contains(SQLKEYS.GroupBy))
                        temp.add(SQLKEYS.Having);

                    
                    return temp;

                case Having:
                    //visitedStates.add(SQLKEYS.Having);

                    temp.add(SQLKEYS.END);

                    if(!visitedStates.contains(SQLKEYS.WHERE))
                        temp.add(SQLKEYS.WHERE);

                    if(!visitedStates.contains(SQLKEYS.OrderBy))
                        temp.add(SQLKEYS.OrderBy);

                    return temp;

                case OrderBy:
                    //visitedStates.add(SQLKEYS.OrderBy);

                    temp.add(SQLKEYS.END);

                    if(!visitedStates.contains(SQLKEYS.WHERE))
                        temp.add(SQLKEYS.WHERE);

                    if(!visitedStates.contains(SQLKEYS.GroupBy))
                        temp.add(SQLKEYS.GroupBy);

                    if(!visitedStates.contains(SQLKEYS.Having) &&
                        visitedStates.contains(SQLKEYS.GroupBy))
                        temp.add(SQLKEYS.Having);
                    

                    return temp;

                case AND:
                    //visitedStates.add(SQLKEYS.AND);

                    temp.add(SQLKEYS.END);

                    temp.add(SQLKEYS.AND);
                    temp.add(SQLKEYS.OR);

                    if(!visitedStates.contains(SQLKEYS.GroupBy))
                        temp.add(SQLKEYS.GroupBy);

                    if(!visitedStates.contains(SQLKEYS.OrderBy))
                        temp.add(SQLKEYS.OrderBy);

                    if(!visitedStates.contains(SQLKEYS.Having) &&
                        visitedStates.contains(SQLKEYS.GroupBy))
                        temp.add(SQLKEYS.Having);

                    return temp;

                case OR:
                    //visitedStates.add(SQLKEYS.OR);

                    temp.add(SQLKEYS.END);

                    temp.add(SQLKEYS.AND);
                    temp.add(SQLKEYS.OR);
                    
                    if(!visitedStates.contains(SQLKEYS.GroupBy))
                        temp.add(SQLKEYS.GroupBy);

                    if(!visitedStates.contains(SQLKEYS.OrderBy))
                        temp.add(SQLKEYS.OrderBy);

                    if(!visitedStates.contains(SQLKEYS.Having) &&
                        visitedStates.contains(SQLKEYS.GroupBy))
                        temp.add(SQLKEYS.Having);


                    return temp;

                case END:
                    //visitedStates.add(SQLKEYS.END);
                    return temp;
            
                default:
                    return temp;
            }
        }
    }

}