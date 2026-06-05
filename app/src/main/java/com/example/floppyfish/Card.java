package com.example.floppyfish;

import java.io.Serializable;

public class Card implements Serializable
{
    public String value;
    public String answer;
    public boolean flipped;
    
    public Card()
    {
        value = "";
        answer = "";
    }
    
    public Card(String v, String s)
    {
        value = v;
        answer = s;
    }
    
    public boolean equals(Card c)
    {
        if(!value.equals(c.value))
            return false;
        if(!answer.equals(c.answer))
            return false;
        return true;
    }

    public String getValue()
    {
        return value;
    }

    public String getAnswer()
    {
        return answer;
    }

    public boolean getFlipped()
    {
        return flipped;
    }

    public void flip()
    {
        if(flipped == true)
            flipped = false;
        else
            flipped = true;
    }
}
