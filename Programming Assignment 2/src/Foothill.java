import java.util.Scanner;

public class Foothill
{
	public static void main (String[]args) 
	{
		//---------------PHASE 1-----------------
		System.out.println("-------------PHASE 1------------");
		
	      Deck theDeck = new Deck();
	      System.out.println("One Deck Before Shuffles: ");
	      while(theDeck.getNumCards() > 0)
	      System.out.println(theDeck.getTopCard());
	     
	      // Restock and Shuffle the Cards one Decks
	      theDeck.restock(1);
	      theDeck.reorder();
	     
	      System.out.println("One Deck After Shuffles: ");
	      while(theDeck.getNumCards() > 0)
	         System.out.println(theDeck.getTopCard());
	      
	      // Restock 3 Decks
	      theDeck.restock(3);
	      
	      System.out.println("After restocking deck with 3 packs (unshuffled): ");
	      while(theDeck.getNumCards() > 0)
	         System.out.println(theDeck.getTopCard());
	   
	      // Restock and Shuffle 3 Decks
	      theDeck.restock(3);
	      theDeck.reorder();
	     
	      System.out.println("After shuffling the deck containing 3 packs: ");
	      while(theDeck.getNumCards() > 0)
	         System.out.println(theDeck.getTopCard());
	
	      //-----------PHASE 2-------------
	      System.out.println("----------------Phase 2----------------");
	      
	      Scanner input = new Scanner(System.in);
	     
	      // loop counters
	      int i;
	      int k = 0;
	      
	      int numPlayers = 0;
	      int minPlayers = 1;
	      int maxPlayers = 12;
	      
	      while (numPlayers == 0)
	      {
	         System.out.println("Add number of players (1 to 12): ");
	         
	         try
	         {
	            numPlayers = Integer.parseInt(input.nextLine());
	         }
	         catch (NumberFormatException error)
	         {
	            continue;
	         }
	         
	         if (numPlayers < minPlayers || numPlayers > maxPlayers)
	            numPlayers = 0;
	      }
	      
	      Hand[] players = new Hand[numPlayers];
	      Deck newDeck = new Deck();
	      
	      for (i = 0; i < numPlayers; i++)
	         players[i] = new Hand();
	      
	      while(newDeck.getNumCards() > 0)
	      {
	         players[k++].takeCard(newDeck.getTopCard());
	         
	         if(k == numPlayers)
	            k = 0;
	      }
	      
	      for (i = 0; i < numPlayers; i++)
	      {
	         System.out.println("Player " + (i + 1) +"'s hand: ");
	         
	         while(players[i].getNumCards() > 0)
	            System.out.println("  " + players[i].playCard());
	      }
	      
	      newDeck.restock(1);
	      newDeck.reorder();
	   // resetting loop counter
	      k = 0; 
	      
	      for (i = 0; i < numPlayers; i++)
	         players[i].resetHand();
	      
	      while(newDeck.getNumCards() > 0)
	      {
	         players[k++].takeCard(newDeck.getTopCard());
	         
	         if(k == numPlayers)
	            k = 0;
	      }
	      
	      System.out.println("\nHands after pack is shuffled: ");
	      
	      for (i = 0; i < numPlayers; i++)
	      {
	         System.out.println("Player " + (i + 1) +"'s hand: ");
	         
	         while(players[i].getNumCards() > 0)
	            System.out.println("  " + players[i].playCard());
	      }
	      
	      input.close();
	}
}

class Card 
{
	// type and constants
   public enum Suit { clubs, diamonds, hearts, spades }
   
   static final char DEFAULT_VAL = 'A';
   static final Suit DEFAULT_SUIT = Suit.spades;

   // private data
   private char value;
   private Suit suit;
   boolean errorFlag;

   // 4 overloaded constructors
   public Card(char value, Suit suit)
   {   // because mutator sets errorFlag, we don't have to test
      set(value, suit);
   }

   public Card(char value)
   {
      this(value, Suit.spades);
   }
   
   public Card()
   {
      this(DEFAULT_VAL, DEFAULT_SUIT);
   }
   
   // copy constructor
   public Card(Card card)
   {
      this(card.value, card.suit);
   }

   // mutators
   public boolean set(char value, Suit suit)
   {
      char upVal;            // for upcasing char

      // convert to uppercase to simplify
      upVal = Character.toUpperCase(value);

      if ( !isValid(upVal, suit))
      {
         errorFlag = true;
         return false;
      }
      
      // else implied
      errorFlag = false;
      this.value = upVal;
      this.suit = suit;
      return true;
   }

   // accessors
   public char getVal()
   {
      return value;
   }

   public Suit getSuit()
   {
      return suit;
   }

   public boolean getErrorFlag()
   {
      return errorFlag;
   }
   
   // stringizer
   public String toString()
   {
      String retVal;

      if (errorFlag)
         return "** illegal **";

      // else implied
      retVal =  String.valueOf(value);
      retVal += " of ";
      retVal += String.valueOf(suit);

      return retVal;
   }

   // helper
   private static boolean isValid(char value, Suit suit)
   {
      // don't need to test suit (enum), but leave in for clarity
      char upVal;  // string to hold the 1-char value
      String legalVals = "A23456789TJQK";
      
      // convert to uppercase to simplify
      upVal = Character.toUpperCase(value);

      // check for validity
      if ( legalVals.indexOf(upVal) >= 0 )
         return true;
      else
         return false;
   }
   
   public boolean equals(Card card)
   {
      if (this.value != card.value)
         return false;
      if (this.suit != card.suit)
         return false;
      if (this.errorFlag != card.errorFlag)
         return false;
      return true;
   }
}

class Hand 
{
	public static final int MAX_CARDS_PER_HAND = 50; // should cover any game

	   private Card[] myCards;
	   private int numCards;

	   // constructor
	   public Hand()
	   {
	      // careful - we are only allocating the references
	      myCards = new Card[MAX_CARDS_PER_HAND];
	      resetHand();
	   }

	   // mutators
	   public void resetHand()
	   {
	      numCards = 0;
	   }

	   public boolean takeCard(Card card)
	   {
	      if (numCards >= MAX_CARDS_PER_HAND)
	         return false;

	      // be frugal - only allocate when needed
	      if (myCards[numCards] == null)
	         myCards[numCards] = new Card();

	      // don't assign - that would be a grave error (could clone(), instead)
	      myCards[numCards++].set(card.getVal(), card.getSuit());
	      return true;
	   }

	   public Card playCard()
	   {
	      // always play highest card in array. client will prepare this position.
	      // in rare case that client tries to play from a spent hand, return error

	      Card errorReturn = new Card('E', Card.Suit.spades); // in rare cases

	      if (numCards == 0)
	         return errorReturn;
	      else
	         return myCards[--numCards];
	   }

	   Card inspectCard(int k)
	   {
	      // return copy of card at position k.
	      // if client tries to access out-of-bounds card, return error

	      Card errorReturn = new Card('E', Card.Suit.spades); // in rare cases

	      if (k < 0 || k >= numCards)
	         return errorReturn;
	      else
	         return myCards[k];
	   }
	   
	   // Accessors
	   public String toString()
	   {
	      int k;
	      String retVal = "Hand =  ( ";

	      for (k = 0; k < numCards; k++)
	      {
	         retVal += myCards[k].toString();
	         if (k < numCards - 1)
	            retVal += ", ";
	      }
	      retVal += " )";
	      return retVal;
	   }

	   int getNumCards()
	   {
	      return numCards;
	   }
}

class Deck 
{
	private static final int MAX_PACKS = 6;
	private static final int NUM_CARDS_PER_PACK = 52;
	private static final int MAX_CARDS_PER_DECK = MAX_PACKS * NUM_CARDS_PER_PACK;
	private static boolean StaticPackAllocated = false;
	private static Card[] StaticPack;
	
	private Card[] cards;
	private int numCards;
	private int numPacks;
	
	public Deck() 
	{
		this(1);
	}
	public Deck(int numPacks)
	{
		allocateStaticPack();
		cards = new Card[MAX_CARDS_PER_DECK];
		restock(numPacks);
	}
	
	public int getNumCards() 
	{
		return numCards;
	}
	public boolean restock(int numPacks) 
	{
		if(numPacks > MAX_PACKS) 
			return false;
		for (int i = 0; i < numPacks*NUM_CARDS_PER_PACK; i++) 
		{
			cards[i] = StaticPack[i%NUM_CARDS_PER_PACK];
		}
		numCards=numPacks*NUM_CARDS_PER_PACK;
		return true;
	}
	
	//Shuffling Cards
	public void reorder() 
	{
		Card temp = new Card('A', Card.Suit.spades);
        int randNum = (int) Math.random() * 1000;
        
        for (int i = 0; i < numCards; i++)
        {
            randNum = (int) (Math.random() * 1000);   
            temp = cards[i];
            cards[i] = cards[(randNum) % numCards];
            cards[(randNum) % numCards] = temp;
		}
	}
	
	public Card getTopCard() 
	{
		Card temp = new Card(cards[numCards-1].getVal(), cards[numCards-1].getSuit());
		numCards--;
		return temp;
	}
	
	public Card getCard(int k) 
	{
		if(k == 0 || k > numCards)
			return new Card('Z', Card.Suit.spades);
		Card removedCard= new Card(cards[k - 1].getVal(), cards[k-1].getSuit());
		return removedCard;
	}
	
	//
	private static void allocateStaticPack() 
	{
		if(!StaticPackAllocated) 
		{
			Card.Suit suit;
			int k, j;
			char value;
			
			StaticPack = new Card [NUM_CARDS_PER_PACK];
			
			for(int i = 0; i < StaticPack.length; i++) 
			{
				StaticPack[i]= new Card();
			}
			
			for (k = 0; k < 4; k++) 
			{
				switch (k)
				{
				case 0:
					suit = Card.Suit.clubs; break;
				case 1:
					suit = Card.Suit.diamonds; break;
				case 2:
					suit = Card.Suit.spades; break;
				case 3:
					suit = Card.Suit.hearts; break;
				default:
					suit = Card.Suit.spades; break;
				}
				StaticPack[13 * k].set('A', suit);
				for(value = '2', j = 1; value <= '9'; value++, j++)
					StaticPack[13 * k + j].set(value, suit);
				StaticPack[13 * k + 9].set('T', suit);
				StaticPack[13 * k + 10].set('J', suit);
				StaticPack[13 * k + 11].set('Q', suit);
				StaticPack[13 * k + 12].set('K', suit);
			}
			
			StaticPackAllocated = true;
		}
		else
			return;
	}
}




/*
Victor Castor Assignment 2
-------------PHASE 1------------
One Deck Before Shuffles: 
K of hearts
Q of hearts
J of hearts
T of hearts
9 of hearts
8 of hearts
7 of hearts
6 of hearts
5 of hearts
4 of hearts
3 of hearts
2 of hearts
A of hearts
K of spades
Q of spades
J of spades
T of spades
9 of spades
8 of spades
7 of spades
6 of spades
5 of spades
4 of spades
3 of spades
2 of spades
A of spades
K of diamonds
Q of diamonds
J of diamonds
T of diamonds
9 of diamonds
8 of diamonds
7 of diamonds
6 of diamonds
5 of diamonds
4 of diamonds
3 of diamonds
2 of diamonds
A of diamonds
K of clubs
Q of clubs
J of clubs
T of clubs
9 of clubs
8 of clubs
7 of clubs
6 of clubs
5 of clubs
4 of clubs
3 of clubs
2 of clubs
A of clubs
One Deck After Shuffles: 
6 of hearts
Q of hearts
7 of hearts
T of clubs
9 of hearts
5 of hearts
4 of diamonds
J of hearts
A of hearts
3 of spades
9 of spades
A of diamonds
K of hearts
7 of spades
2 of clubs
A of clubs
5 of diamonds
6 of spades
T of hearts
K of spades
9 of clubs
5 of spades
4 of clubs
8 of clubs
Q of diamonds
3 of clubs
K of clubs
Q of spades
3 of hearts
4 of spades
7 of clubs
J of diamonds
2 of hearts
J of clubs
2 of spades
4 of hearts
3 of diamonds
6 of diamonds
T of diamonds
5 of clubs
2 of diamonds
K of diamonds
8 of hearts
Q of clubs
8 of spades
A of spades
7 of diamonds
J of spades
8 of diamonds
6 of clubs
T of spades
9 of diamonds
After restocking deck with 3 packs (unshuffled): 
K of hearts
Q of hearts
J of hearts
T of hearts
9 of hearts
8 of hearts
7 of hearts
6 of hearts
5 of hearts
4 of hearts
3 of hearts
2 of hearts
A of hearts
K of spades
Q of spades
J of spades
T of spades
9 of spades
8 of spades
7 of spades
6 of spades
5 of spades
4 of spades
3 of spades
2 of spades
A of spades
K of diamonds
Q of diamonds
J of diamonds
T of diamonds
9 of diamonds
8 of diamonds
7 of diamonds
6 of diamonds
5 of diamonds
4 of diamonds
3 of diamonds
2 of diamonds
A of diamonds
K of clubs
Q of clubs
J of clubs
T of clubs
9 of clubs
8 of clubs
7 of clubs
6 of clubs
5 of clubs
4 of clubs
3 of clubs
2 of clubs
A of clubs
K of hearts
Q of hearts
J of hearts
T of hearts
9 of hearts
8 of hearts
7 of hearts
6 of hearts
5 of hearts
4 of hearts
3 of hearts
2 of hearts
A of hearts
K of spades
Q of spades
J of spades
T of spades
9 of spades
8 of spades
7 of spades
6 of spades
5 of spades
4 of spades
3 of spades
2 of spades
A of spades
K of diamonds
Q of diamonds
J of diamonds
T of diamonds
9 of diamonds
8 of diamonds
7 of diamonds
6 of diamonds
5 of diamonds
4 of diamonds
3 of diamonds
2 of diamonds
A of diamonds
K of clubs
Q of clubs
J of clubs
T of clubs
9 of clubs
8 of clubs
7 of clubs
6 of clubs
5 of clubs
4 of clubs
3 of clubs
2 of clubs
A of clubs
K of hearts
Q of hearts
J of hearts
T of hearts
9 of hearts
8 of hearts
7 of hearts
6 of hearts
5 of hearts
4 of hearts
3 of hearts
2 of hearts
A of hearts
K of spades
Q of spades
J of spades
T of spades
9 of spades
8 of spades
7 of spades
6 of spades
5 of spades
4 of spades
3 of spades
2 of spades
A of spades
K of diamonds
Q of diamonds
J of diamonds
T of diamonds
9 of diamonds
8 of diamonds
7 of diamonds
6 of diamonds
5 of diamonds
4 of diamonds
3 of diamonds
2 of diamonds
A of diamonds
K of clubs
Q of clubs
J of clubs
T of clubs
9 of clubs
8 of clubs
7 of clubs
6 of clubs
5 of clubs
4 of clubs
3 of clubs
2 of clubs
A of clubs
After shuffling the deck containing 3 packs: 
8 of diamonds
7 of spades
K of clubs
J of clubs
K of spades
T of diamonds
3 of hearts
7 of diamonds
5 of spades
4 of spades
6 of clubs
7 of hearts
T of diamonds
J of spades
3 of hearts
3 of diamonds
8 of diamonds
9 of clubs
J of clubs
6 of clubs
8 of clubs
T of hearts
A of diamonds
7 of spades
5 of diamonds
K of spades
8 of diamonds
8 of spades
Q of diamonds
Q of spades
5 of clubs
A of diamonds
T of clubs
7 of clubs
2 of hearts
K of clubs
6 of diamonds
9 of hearts
4 of spades
5 of hearts
4 of diamonds
6 of spades
A of spades
9 of clubs
8 of clubs
3 of spades
3 of clubs
Q of hearts
J of diamonds
Q of diamonds
T of clubs
3 of diamonds
6 of diamonds
4 of hearts
5 of hearts
Q of clubs
2 of diamonds
K of diamonds
4 of diamonds
8 of hearts
J of hearts
2 of hearts
K of hearts
8 of hearts
A of spades
Q of diamonds
7 of diamonds
3 of hearts
2 of spades
K of spades
9 of diamonds
9 of spades
T of spades
A of clubs
A of hearts
9 of diamonds
A of hearts
7 of clubs
9 of spades
A of diamonds
5 of diamonds
5 of diamonds
6 of clubs
3 of spades
2 of spades
8 of hearts
5 of clubs
T of hearts
K of diamonds
7 of spades
6 of hearts
T of clubs
6 of hearts
5 of clubs
4 of clubs
7 of hearts
4 of spades
Q of hearts
Q of clubs
2 of diamonds
2 of spades
2 of clubs
A of clubs
K of clubs
4 of hearts
6 of hearts
Q of spades
T of diamonds
9 of spades
J of diamonds
9 of hearts
4 of diamonds
2 of clubs
4 of hearts
J of spades
9 of diamonds
T of spades
Q of clubs
7 of clubs
3 of clubs
5 of spades
K of hearts
8 of clubs
A of hearts
K of hearts
J of spades
4 of clubs
2 of clubs
3 of clubs
A of clubs
T of spades
T of hearts
J of hearts
J of hearts
J of clubs
8 of spades
A of spades
3 of diamonds
4 of clubs
Q of spades
9 of clubs
6 of spades
J of diamonds
9 of hearts
K of diamonds
2 of diamonds
2 of hearts
7 of diamonds
5 of spades
8 of spades
3 of spades
7 of hearts
Q of hearts
6 of spades
6 of diamonds
5 of hearts
----------------Phase 2----------------
Add number of players (1 to 12): 

*/