package com.example.floppyfish;
import java.util.*;
public class Flash 
{
    private ArrayList<Card> arr;
    private int pointer = 0;

    public ArrayList<Card> getCards()
    {
        return arr;
    }
    public Flash()
    {
        arr = new ArrayList<Card>();
    }
    
    public void add(String s, String v)
    {
        Card tmp = new Card(s, v);
        
        arr.add(tmp);
    }

    
    public void remove(int i)
    {
        
        Card tmp = arr.remove(i);
    }

    public Card getTop()
    {
        return arr.get(pointer);
    }
    public Card nextCard()
    {
        if(pointer < arr.size())
        {
            Card tmp = arr.get(pointer);
            pointer++;
            return tmp;
        }
        return null;
    }
    public void shuffle()
    {
        Collections.shuffle(arr);
        pointer = 0;
    }
    public String getRandomAnswer()
    {
        if(arr.size() > 1)
            return arr.get((int) (Math.random() * arr.size())).getAnswer();
        else
            return "gng wtf we doing";
    }


}
