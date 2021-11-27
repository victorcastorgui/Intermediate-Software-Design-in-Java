public class Foothill 
{
	public static void main(String[]args)
	{
		CardQueue Queue = new CardQueue();
	     
      Queue.add(new Card('7', Card.Suit.spades));
      Queue.add(new Card('8', Card.Suit.diamonds));
      Queue.add(new Card('T', Card.Suit.hearts));
      Queue.add(new Card());
      Queue.add(new Card('5', Card.Suit.clubs));
      
      System.out.println("Cards in Queue: ");
      System.out.println(Queue);
     
      System.out.println("Removing and Show from Queue:");
      // To show that after 5 it will be empty
      for (int i = 0; i < 10; i++)
      {
         try
         {
            System.out.println(Queue.removeFront());
         }
         catch(QueueEmptyException e)
         {
            System.out.println("Empty Queue");
         }
      }
	}
}

class Node
{
	// data (protected allows only certain other classes to access "next" directly)
	protected Node next;
	    
	// constructor
	public Node()
   	{
	   next = null;
   	}
	   
   	// console display
   	public String toString()
   	{
   		return ("Generic Code");
   	}
}

class Queue
{
	// pointer to first node in stack
	private Node front;
	private Node back;
   
	// constructor
	public Queue()
	{
		front = null;
		back = null;
	}
   
	public void push_back(Node newNode)
	{   
		if (newNode == null) 
			return;   // emergency return
		
		if (front == null)
		front = back = newNode;
		
		else 
		{
			back.next = newNode;
			back = newNode;
		}
	}  
   
	public Node remove_front() throws QueueEmptyException
	{
		if (front==null)
			return null;
		
		Node temp;
		temp = front;
		
		if (front.next != null)
		{
			front = front.next; 
			temp.next = null; // don't give client access to stack!
		}
		
		else 
		{
			front = null;
		}
		
		return temp;      
   }

	// console display
	public String toString()
	{
		Node p;
		String s = "";
		
		// Display all the nodes in the stack
		for(p = front; p != null; p = p.next)
			s += p.toString() + "\n";
		
		return s;
	}
}

class QueueEmptyException extends Exception
{
}

class CardNode extends Node
{
	// additional data for derive class
	private Card data;
	// constructor
	public CardNode(Card x)
	{
		super();  // constructor call to base class
		data = x;
	}
	
	// accessor
	public Card getData()
	{
		return data;
	}
	
	//to show the data
	public String toString() 
	{
		return data.toString();
	}	
}

class CardQueue extends Queue
{
	// pointer to first node in stack
	public void add(Card card)
	{
		if (card==null) return;
		
		CardNode CN = new CardNode(card);
		
		super.push_back(CN);
	}
	
	public Card removeFront() throws QueueEmptyException
	{
		CardNode Card = (CardNode) super.remove_front();
		if (Card==null) throw new QueueEmptyException();
		return Card.getData();
	}
}

//Assignment 1 Solution Card Class from Professor Harden
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
}

/*
Cards in Queue: 
7 of spades
8 of diamonds
T of hearts
A of spades
5 of clubs

Removing and Show from Queue:
7 of spades
8 of diamonds
T of hearts
A of spades
5 of clubs
Empty Queue
Empty Queue
Empty Queue
Empty Queue
Empty Queue
*/
