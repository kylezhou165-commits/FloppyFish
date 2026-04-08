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
    
    public void addCard(String s, String v)
    {
        Card tmp = new Card(s, v);
        
        arr.add(tmp);
        next.add(tmp);
    }
    
    public Card removeCard(int i)
    {
        
        Card tmp = arr.remove(i)
        Iterator<Card> it = next.iterator();
        
        while(it.hasNext())
        {
            if(it.next().equals(tmp))
                return it.remove();
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
        next = new LinkedList<Card>();
        
    }
    
}