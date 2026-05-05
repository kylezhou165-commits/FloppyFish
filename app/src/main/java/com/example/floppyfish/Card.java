public class Card
{
    public String value;
    public String answer;
    
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
    
    public String toString()
    {
        return value;
    }
}
