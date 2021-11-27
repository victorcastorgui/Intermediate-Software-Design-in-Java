public class Hand
{
    public static int MAX_CARDS = 30;

    private Card[] myCards;
    private int numCards;
    
    Hand()
    {
    	myCards = new Card[MAX_CARDS];
        numCards = 0;
        for (int k = 0; k<MAX_CARDS; k++)
        myCards[k] = new Card();
    }
   
    public void resetHand()
    {
    	numCards = 0;
    }
    
    public boolean takeCard(Card card) 
    {        
        if (numCards >= MAX_CARDS)
            return false;

        if (card.geterrorFlag() == false)
        {          
            myCards[numCards] = card;
            numCards++;
        }
       System.out.println(card);
          return true;
    }
   
    public Card playCard()
    {  
        if (numCards <= 0)
        	return new Card('T', Card.Suit.spades);
        Card temp = myCards[numCards - 1];
        numCards--;
       
        return temp;
    }

    public String toString()
    {    
    	String handCards = "Hand: ";
      
        if (numCards <= 0) return handCards;

        for (int k = 0; k < numCards; k++)
            handCards += myCards[k].toString() + " \n";
      return handCards;
    }

    public Card inspectCard(int k)
    {   
        if (k < 0 || (k > numCards - 1) || k >= MAX_CARDS)
            return new Card('X', Card.Suit.spades);

        return myCards[k];
    }

	public int getNumCards() {
		return 0;
	}
}
