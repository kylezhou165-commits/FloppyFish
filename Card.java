import java.util.*;

public class Flash 
{
    public ArrayList<Card> arr;
    public LinkedList<Card> next;
    
    public Flash()
    {
        arr = new ArrayList<Card>();
        next = new LinkedList<Card>();
    }
    
    public void add(String s, String v)
    {
        Card tmp = new Card(s, v);
        
        arr.add(tmp);
        next.add(tmp);
    }
    
    public String toString()
    {
        String str = arr.toString() + "\n" + next.toString();
        return str;
    }
    
    public void remove(int i)
    {
        
        Card tmp = arr.remove(i);
        ListIterator<Card> it = next.listIterator();
        
        while(it.hasNext())
        {
            Card g = it.next();
            if(g.equals(tmp))
                it.remove();
        }
    }
    
    public Card nextCard()
    {
        Card tmp = next.getFirst();
        next.removeFirst();
        next.addLast(tmp);
        return tmp;
    }
    
    public void shuffle()
    {
        shuffle(200);
    }
    
    public void shuffle(int n)
    {
        next = new LinkedList<Card>();
        ArrayList<Card> copy = new ArrayList<Card>();
        
        for(Card c : arr)
            copy.add(c);
            
        stupidShuffle(copy, n);
        
        for(Card c : copy)
        {
            next.add(c);
        }
    }
    
    private static void stupidShuffle(ArrayList arrTmp, int n)
    {
        for(int i = 0; i < n; i++)
        {
 
            for(int ind = 0; ind < arrTmp.size() - 1; ind++)
            {
                if(Math.random() < 0.5)
                {
                   
                    Object tmp = arrTmp.get(ind + 1);
                    arrTmp.set(ind + 1, arrTmp.get(ind));
                    arrTmp.set(ind, tmp);
                }
            }
        }
    }
    
    
}
