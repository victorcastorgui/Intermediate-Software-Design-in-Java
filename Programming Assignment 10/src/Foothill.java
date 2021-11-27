import java.util.*;
//Programming Assignment 10 Extra Credit
//Date: 03/19/2020
//Owner: Victor Castor
//Instructor: Dave Harden
//Class: CS1B
//Program: This program have Card class that is given by the instructor and LinkedList
//that is similar to the module that is given. compareTo is added to insert, remove,
//and removeAll. The Main in this program is used to generate 5 random Cards 
//then duplicate the cards. After all the cards been added to the list, it will 
//then remove some of the cards. Then using the boolean removeAll function to 
//remove Cards in myList. If it is not available, it will print out that no card 
//can be removed. If the card is removed, it will print out removed.
public class Foothill 
{
	public static void main(String[] args)
	   {
	      int k;
	      
	      Card first = new Card('A', Card.Suit.spades);
	      Card second = new Card('4', Card.Suit.hearts);
	      Card third = new Card('T', Card.Suit.clubs);
	      
	      System.out.println( "should all be 0:\n"); 
	      System.out.println( first.compareTo( first ) );
	      System.out.println( second.compareTo( second ) );
	      System.out.println( third.compareTo( third ) );
	      
	      System.out.println( "\nshould all be < 0:\n"); 
	      System.out.println( second.compareTo( first ) );
	      System.out.println( second.compareTo( third ) );
	      System.out.println( third.compareTo( first ) );
	      
	      System.out.println( "\nshould all be > 0:\n"); 
	      System.out.println( first.compareTo( second ) );
	      System.out.println( third.compareTo( second ) );
	      System.out.println( first.compareTo( third ) );
	      
	      System.out.println( "\nSome random cards:\n");
	      for ( k = 0; k < 50; k++ )
	      {
	         System.out.print( generateRandomCard().toString() + "  ");
	      }
	      System.out.println();
	     
	      LinkedList<Card> myList = new LinkedList<Card>();
	      Card cardList[] = new Card[5];
	      int i = 0;
	      
	      //Generating 5 random cards and duplicate it.
	      for (i = 0; i < 5; i++) 
	      {
	    	  cardList[i] = generateRandomCard();
	    	  insert(myList, cardList[i]);	
	    	  insert(myList, cardList[i]);
	      }
	      showList(myList);
	      while(true)
	      {
	    	  if(!remove(myList, cardList[0])) break;
	      }
	      while(true) 
	      {
	    	  if(!remove(myList, cardList[1])) break;
	      }
	      showList(myList);
	      
	      Card random = generateRandomCard();
	      System.out.println(random.toString() + " ");
	      if(removeAll(myList, random)) 
	      {
	    	  System.out.println("Removed");
	      }
	      else
	    	  System.out.println("Can't remove because not found.");
	      
	      System.out.println(cardList[2].toString() + " ");
	      if(removeAll(myList, cardList[2])) 
	      {
	    	  System.out.println("Removed");
	      }
	      else
	    	  System.out.println("Can't remove because not found in list.");
	      
	      System.out.println(cardList[3].toString() + " ");
	      if(removeAll(myList, cardList[3])) 
	      {
	    	  System.out.println("Removed");
	      }
	      else
	    	  System.out.println("Can't remove because not found.");
	      
	      System.out.println(cardList[4].toString() + " ");
	      if(removeAll(myList, cardList[4])) 
	      {
	    	  System.out.println("Removed");
	      }
	      else
	    	  System.out.println("Can't remove because not found in list.");
	      
	      System.out.println(cardList[0].toString() + " ");
	      if(removeAll(myList, cardList[0])) 
	      {
	    	  System.out.println("Removed");
	      }
	      else
	    	  System.out.println("Can't remove because not found.");
	   }

	   // "global" static Foothill methods 
	   static Card generateRandomCard()
	   {
	      // if firstTime = true, use clock to seed, else fixed seed for debugging
	      Card.Suit suit;
	      char val;

	      int suitSelector, valSelector;

	      // get random suit and value
	      suitSelector = (int) (Math.random() * 4);
	      valSelector = (int) (Math.random() * 13);

	      // pick suit
	      suit = turnIntIntoSuit(suitSelector);
	      val = turnIntIntoVal(valSelector);

	      return new Card(val, suit);
	   }

	   // note:  this method not needed if we use int for suits instead of enum
	   static Card.Suit turnIntIntoSuit(int k)
	   {
	      return Card.Suit.values()[k];  // 
	   }

	   static char turnIntIntoVal(int k)
	   {
	      String legalVals = "23456789TJQKA";
	      
	      if (k < 0 | k >= legalVals.length())
	         return '?';
	      return legalVals.charAt(k);
	   }
	   
	   //Copied from instructor's modules. 
	   static void showList(LinkedList<Card> myList)
	   {
	      ListIterator<Card> iter;
	      
	      System.out.println( "\n\n_____Here's the List_______\n" );
	      for (iter = myList.listIterator(); iter.hasNext(); )
	         System.out.print("[" + iter.next() + "] ");
	      System.out.println( "\n\n_____That's all!_______\n" );
	   }
	   
	   //insert takes two parameters, LinkedList<Card> myList and Card x.
	   //insert, utter repeatedly through mylist and using the compareTo to 
	   //compare x to card list. If it finds a bigger Card in myList,
	   //it will put the Card next to the smaller Card.
	   static void insert(LinkedList<Card> myList, Card x)
	   {
	      ListIterator<Card> iter;
	      Card listX;

	      for (iter = myList.listIterator(); iter.hasNext(); )
	      {
	        listX = iter.next();
	        if (x.compareTo(listX) < 0)
	        {
	           iter.previous(); // back up one
	           break;
	        }
	      }
	      iter.add(x);
	   }
	   
	   //boolean remove takes two parameters, LinkedList<Card> myList and Card x.
	   //It simply removes whatever Card x in myList. If it found something and remove,
	   //it will return true. Else, it will return false because it can not remove
	   //anything.
	   static boolean remove(LinkedList<Card> myList, Card x)
	   {
	      ListIterator<Card> iter;

	      for (iter = myList.listIterator(); iter.hasNext(); )
	         if (x.compareTo(iter.next()) == 0)
	         {
	            iter.remove();
	            return true;   // we found, we removed, we return
	         }
	      return false;
	   }
	   
	   //boolean remove takes two parameters, LinkedList<Card> myList and Card x.
	   //It is first set to false. Then, removeAll remove function on myList 
	   //If remove is successfull, it will return true. Else, it will return false.
	   static boolean removeAll(LinkedList<Card> myList, Card x) 
	   {
		   boolean removeAll = false;
		   for(ListIterator<Card> iter = myList.listIterator(); iter.hasNext();) 
			   if (x.compareTo(iter.next()) == 0)
		         {
		            iter.remove();
		            return true;   // we found, we removed, we return
		         }
		   return removeAll;
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
      String legalVals = "23456789TJQKA";
      
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
   
// to existing members, add:

   // for sort  
   protected static char[] valueRanks = { '2', '3', '4', '5', '6', '7', '8', '9', 
      'T', 'J', 'Q', 'K', 'A'};
   protected static Suit[] suitRanks = {Suit.clubs, Suit.diamonds, Suit.hearts, 
      Suit.spades};
   protected static final int NUM_VALS = 13; 

   // sort member methods
   public int compareTo(Card other)
   {
      if (this.value == other.value)
         return ( getSuitRank(this.suit) - getSuitRank(other.suit) );

      return ( 
            getValueRank(this.value) 
            - getValueRank(other.value) 
            );
   }

   public static int getSuitRank(Suit st)
   {
      int k;

      for (k = 0; k < 4; k++) 
         if (suitRanks[k] == st)
            return k;

      // should not happen
      return 0;
   }

   public  static int getValueRank(char val)
   {
      int k;

      for (k = 0; k < NUM_VALS; k++) 
         if (valueRanks[k] == val)
            return k;

      // should not happen
      return 0;
   }
   
   static boolean remove(LinkedList<Float> myList, float x)
   {
      ListIterator<Float> iter;

      for (iter = myList.listIterator(); iter.hasNext(); )
         if (iter.next() == x)
         {
            iter.remove();
            return true;   // we found, we removed, we return
         }
      return false;
   }
}

/*
should all be 0:

0
0
0

should all be < 0:

-10
-6
-4

should all be > 0:

10
6
4

Some random cards:

3 of clubs  5 of clubs  9 of hearts  4 of hearts  T of diamonds  Q of diamonds  7 of hearts  K of clubs  4 of diamonds  T of spades  8 of clubs  4 of hearts  T of diamonds  6 of clubs  2 of spades  5 of hearts  A of hearts  Q of diamonds  6 of diamonds  K of diamonds  3 of clubs  6 of hearts  9 of diamonds  8 of clubs  6 of clubs  Q of hearts  J of diamonds  3 of clubs  2 of spades  2 of diamonds  J of spades  9 of clubs  4 of spades  5 of clubs  7 of spades  4 of spades  K of clubs  3 of hearts  8 of spades  4 of spades  6 of diamonds  K of spades  6 of spades  8 of diamonds  8 of hearts  9 of hearts  A of diamonds  J of spades  6 of clubs  K of clubs  


_____Here's the List_______

[4 of spades] [4 of spades] [4 of spades] [4 of spades] [Q of diamonds] [Q of diamonds] [A of diamonds] [A of diamonds] [A of hearts] [A of hearts] 

_____That's all!_______



_____Here's the List_______

[4 of spades] [4 of spades] [4 of spades] [4 of spades] [Q of diamonds] [Q of diamonds] 

_____That's all!_______

5 of hearts 
Can't remove because not found.
4 of spades 
Removed
4 of spades 
Removed
Q of diamonds 
Removed
A of hearts 
Can't remove because not found.
*/