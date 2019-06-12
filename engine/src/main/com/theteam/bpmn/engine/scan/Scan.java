package com.theteam.bpmn.engine.scan;

import com.theteam.bpmn.engine.Elist;
import com.theteam.bpmn.engine.io.EVariable;

import java.util.ArrayList;
import java.util.regex.*;

public class Scan
{
    String sentene;
    Elist list;

    String reg = "\\$\\w+";

    public Scan(String sentence, Elist list)
    {
        this.sentene = sentence;
        this.list = list;
    }

    public String reg(String text)
    {
        String tText = "";

        //System.out.println("Enter regex pattern:");
        Pattern pattern = Pattern.compile(reg);

        //System.out.println("Enter text:");
        Matcher matcher = pattern.matcher(text);

        boolean found = false;

        int start = 0;

        while (matcher.find())
        {

            //System.out.println("I found the text " + matcher.group() + " starting at index "+
                //matcher.start()+" and ending at index "+ matcher.end());
            
            String s = text.substring(matcher.start()+1, matcher.end());
            // System.out.println(s);

            tText += text.substring(start, matcher.start()) + list.getVariable(s).getValue();
            start = matcher.end();

            found = true;
        }


        if(!found)
            return null;

        tText += text.substring(start);
        return tText;
    }


    public String getFinalString()
    {
        String s = sentene;
        String t = s;
        //System.out.println(s);

        while(s != null)
        {
            t = s;
            s = reg(s);
            //System.out.println(s);
        }

        return t;
    }

    public int getNumberOfVariables()
    {
        return 0;
    }
}