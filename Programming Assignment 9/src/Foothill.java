//Programming Assignment 9
//Date: 03/09/2020
//Owner: Victor Castor
//Instructor: Dave Harden
//Class: CS1B
//Program: This is a B-1 and B-2 program. This program consists derived class that was
//given by the instructor and generic tree with soft deletion. Furthermore, this 
//program consist of 3 mains. One is given. The other two is a simple numeric type and 
//the other is card. All the mains format are similar to the instructors main.
//Extra Credit: B-1 and B-2.
public class Foothill
{
	// -------  main --------------
	   public static void main(String[] args) throws Exception
	   {
		   //Test given by instructor
		  System.out.println("--------------RUN 1 MAIN 1--------------");
	      FHsdTree<String> sceneTree = new FHsdTree<String>();
	      FHsdTreeNode<String> tn;
	      
	      System.out.println("Starting tree empty? " + sceneTree.empty() + "\n");
	      // create a scene in a room
	      tn = sceneTree.addChild(null, "room");

	      // add three objects to the scene tree
	      sceneTree.addChild(tn, "Lily the canine");
	      sceneTree.addChild(tn, "Miguel the human");
	      sceneTree.addChild(tn, "table");
	      // add some parts to Miguel
	      tn = sceneTree.find("Miguel the human");

	      // Miguel's left arm
	      tn = sceneTree.addChild(tn, "torso");
	      tn = sceneTree.addChild(tn, "left arm");
	      tn =  sceneTree.addChild(tn, "left hand");
	      sceneTree.addChild(tn, "thumb");
	      sceneTree.addChild(tn, "index finger");
	      sceneTree.addChild(tn, "middle finger");
	      sceneTree.addChild(tn, "ring finger");
	      sceneTree.addChild(tn, "pinky");

	      // Miguel's right arm
	      tn = sceneTree.find("Miguel the human");
	      tn = sceneTree.find(tn, "torso", 0);
	      tn = sceneTree.addChild(tn, "right arm");
	      tn =  sceneTree.addChild(tn, "right hand");
	      sceneTree.addChild(tn, "thumb");
	      sceneTree.addChild(tn, "index finger");
	      sceneTree.addChild(tn, "middle finger");
	      sceneTree.addChild(tn, "ring finger");
	      sceneTree.addChild(tn, "pinky");

	      // add some parts to Lily
	      tn = sceneTree.find("Lily the canine");
	      tn = sceneTree.addChild(tn, "torso");
	      sceneTree.addChild(tn, "right front paw");
	      sceneTree.addChild(tn, "left front paw");
	      sceneTree.addChild(tn, "right rear paw");
	      sceneTree.addChild(tn, "left rear paw");
	      sceneTree.addChild(tn, "spare mutant paw");
	      sceneTree.addChild(tn, "wagging tail");

	      // add some parts to table
	      tn = sceneTree.find("table");
	      sceneTree.addChild(tn, "north east leg");
	      sceneTree.addChild(tn, "north west leg");
	      sceneTree.addChild(tn, "south east leg");
	      sceneTree.addChild(tn, "south west leg");

	      System.out.println("\n------------ Loaded Tree ----------------- \n");
	      sceneTree.display();
	      
	      sceneTree.remove("spare mutant paw");
	      sceneTree.remove("Miguel the human");
	      sceneTree.remove("an imagined higgs boson");
	      
	      System.out.println("\n------------ Virtual (soft) Tree --------------- \n");
	      sceneTree.display();

	      System.out.println("\n----------- Physical (hard) Display ------------- \n");
	      sceneTree.displayPhysical();
	      
	      System.out.println("------- Testing Sizes (compare with above) -------- \n");
	      System.out.println("virtual (soft) size: " + sceneTree.size()  );
	      System.out.println("physiical (hard) size: " + sceneTree.sizePhysical()  );

	      System.out.println("------------ Collecting Garbage ---------------- \n");
	      System.out.println("found soft-deleted nodes? " 
	            + sceneTree.collectGarbage()  );
	      System.out.println("immediate collect again? " 
	            + sceneTree.collectGarbage()  );

	      System.out.println("--------- Hard Display after garb col ------------ \n");

	      sceneTree.displayPhysical();

	      System.out.println("Semi-deleted tree empty? " + sceneTree.empty() + "\n");
	      sceneTree.remove("room");
	      System.out.println("Completely-deleted tree empty? " + sceneTree.empty() 
	         + "\n");
	      
	      sceneTree.display();
	      
	      System.out.println("--------------END OF RUN 1 MAIN 1--------------\n");
	      
	      System.out.println("\n--------------RUN 2 MAIN 2--------------");
	      
	    //------Main Run #3: SoftDel tree with type Card------------------
	      FHsdTree<Card> cardsTree = new FHsdTree<Card>();

	      FHsdTreeNode<Card> cardNode;

	      //create a double root
	      cardNode = cardsTree.addChild(null,new Card('3', Card.Suit.hearts));

	      //add some objects to the tree
	      cardsTree.addChild(cardNode,new Card());
	      cardsTree.addChild(cardNode,new Card('3', Card.Suit.hearts));
	      cardsTree.addChild(cardNode,new Card('X',Card.Suit.diamonds));

	      //add parts to some nodes
	      cardNode = cardsTree.find(new Card('3', Card.Suit.hearts));
	      cardsTree.addChild(cardNode,new Card('5', Card.Suit.diamonds));
	      cardsTree.addChild(cardNode,new Card('Q', Card.Suit.hearts));
	      cardsTree.addChild(cardNode,new Card('9',Card.Suit.hearts));

	      cardNode = cardsTree.find(new Card('X',Card.Suit.diamonds));
	      cardsTree.addChild(cardNode,new Card('T',Card.Suit.clubs));

	      cardNode = cardsTree.find(new Card('2',Card.Suit.clubs));
	      cardsTree.addChild(cardNode,new Card('T',Card.Suit.spades));
	      cardsTree.addChild(cardNode,new Card('5',Card.Suit.hearts));
	      cardsTree.addChild(cardNode,new Card('6',Card.Suit.diamonds));

	      cardNode = cardsTree.find(new Card('A', Card.Suit.hearts));
	      cardsTree.addChild(cardNode,new Card('7', Card.Suit.spades));
	      cardsTree.addChild(cardNode,new Card('2', Card.Suit.diamonds));
	      cardsTree.addChild(cardNode,new Card('4', Card.Suit.spades));
	      cardsTree.addChild(cardNode,new Card('8', Card.Suit.spades));


	      System.out.println("\n------------ Loaded Tree ----------------- \n");
	      cardsTree.display();
	      FHsdTree<Card> cardsTreeClone = cardsTree;

	      System.out.println("\n------Loaded Clone Tree ----------------\n");
	      cardsTreeClone.display();

	      cardsTree.remove(new Card('T',Card.Suit.spades));
	      cardsTree.remove(new Card('5',Card.Suit.diamonds));
	      
	      System.out.println("\n------------ Virtual (soft) Tree + clone ------- \n");
	      cardsTree.display();

	      System.out.println("\n---clone---\n");
	      cardsTreeClone.display();

	      System.out.println("\n------------ Physical tree, real then clone------ \n");
	      cardsTree.displayPhysical();
	      
	      cardsTreeClone.displayPhysical();
	      
	      System.out.println("------- Testing Sizes (compare with above)------- \n");
	      System.out.println("virtual (soft) size: " + cardsTree.size() );
	      System.out.println("physical (hard) size: " + cardsTree.sizePhysical() );
	      System.out.println("clone (hard) size: " + cardsTreeClone.sizePhysical() );

	      System.out.println("------------ Collecting Garbage ----------------- \n");
	      System.out.println("found soft-deleted nodes? "+ cardsTree.collectGarbage());
	      System.out.println("immediate collect again? " + cardsTree.collectGarbage());

	      System.out.println("--------- Hard Display after garb col ------------ \n");
	      cardsTree.displayPhysical();

	      System.out.println("Semi-deleted tree empty? " + cardsTree.empty() );
	      cardsTree.remove(new Card('A', Card.Suit.hearts));
	      System.out.println("Completely-deleted tree empty? " + cardsTree.empty());
	      
	      cardsTree.displayPhysical();
	      
	      System.out.println("--------------END OF RUN 2 MAIN 2--------------\n");
	      
	      System.out.println("\n--------------RUN 3 MAIN 3--------------");

	    //--------MAIN PART TWO: WITH ints--------------------
	      FHsdTree<Integer> numberTree = new FHsdTree<Integer>();
	      FHsdTreeNode<Integer> numNode;
	      
	      numNode = numberTree.addChild(null, 1);

	      //add some objects to the tree
	      numberTree.addChild(numNode, 33);
	      numberTree.addChild(numNode, 12);
	      numberTree.addChild(numNode, -10);

	      //add parts to some nodes
	      numNode = numberTree.find(12);
	      numberTree.addChild(numNode, 542);
	      numberTree.addChild(numNode, 87);

	      numNode = numberTree.find(-33);
	      numberTree.addChild(numNode, 98);
	      numberTree.addChild(numNode, 45);
	      
	      numNode = numberTree.find(-10);
	      numberTree.addChild(numNode, 69);
	      numberTree.addChild(numNode, -85);

	      System.out.println( "\n------------ Loaded Tree ----------------- \n");
	      numberTree.display();

	      numberTree.remove(542);
	      numberTree.remove(98);
	      numberTree.remove(-85);

	      System.out.println("\n------------ Virtual (soft) Tree --------------\n");
	      numberTree.display();

	      System.out.println("\n------------ Physical (hard) Display --------- \n");
	      numberTree.displayPhysical();
	      
	      System.out.println( "------- Testing Sizes (compare with above)------ \n");
	      System.out.println("virtual (soft) size: " + numberTree.size());
	      System.out.println( "physical (hard) size: " + numberTree.sizePhysical());
	      
	      System.out.println("--------- Collecting Garbage -------------- \n");
	      System.out.println("found soft-deleted nodes? "+
	      numberTree.collectGarbage());
	      System.out.println("immediate collect again? " + 
	      numberTree.collectGarbage());
	      System.out.println( "--------- Hard Display after garb col --------- \n");
	      numberTree.displayPhysical();

	      System.out.println( "Semi-deleted tree empty? " + numberTree.empty());
	      numberTree.remove(1);
	      System.out.println( "Completely-deleted tree empty? "+numberTree.empty());
	      
	      numberTree.display();
	      
	      System.out.println("--------------END OF RUN 3 MAIN 3--------------\n");
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
}

/*
--------------RUN 1 MAIN 1--------------
Starting tree empty? true


------------ Loaded Tree ----------------- 

room
 table
  south west leg
  south east leg
  north west leg
  north east leg
 Miguel the human
  torso
   right arm
    right hand
     pinky
     ring finger
     middle finger
     index finger
     thumb
   left arm
    left hand
     pinky
     ring finger
     middle finger
     index finger
     thumb
 Lily the canine
  torso
   wagging tail
   spare mutant paw
   left rear paw
   right rear paw
   left front paw
   right front paw

------------ Virtual (soft) Tree --------------- 

room
 table
  south west leg
  south east leg
  north west leg
  north east leg
 Lily the canine
  torso
   wagging tail
   left rear paw
   right rear paw
   left front paw
   right front paw

----------- Physical (hard) Display ------------- 

room
 (D)
 table
  south west leg
  south east leg
  north west leg
  north east leg
 Lily the canine
  torso
   wagging tail
   left rear paw
   right rear paw
   left front paw
   right front paw
------- Testing Sizes (compare with above) -------- 

virtual (soft) size: 13
physiical (hard) size: 28
------------ Collecting Garbage ---------------- 

found soft-deleted nodes? true
immediate collect again? true
--------- Hard Display after garb col ------------ 

room
 (D)
 table
  south west leg
  south east leg
  north west leg
  north east leg
 Lily the canine
  torso
   wagging tail
   left rear paw
   right rear paw
   left front paw
   right front paw
Semi-deleted tree empty? false

Completely-deleted tree empty? false

--------------END OF RUN 1 MAIN 1--------------


--------------RUN 2 MAIN 2--------------

------------ Loaded Tree ----------------- 

3 of hearts
 ** illegal **
 3 of hearts
 A of spades

------Loaded Clone Tree ----------------

3 of hearts
 ** illegal **
 3 of hearts
 A of spades

------------ Virtual (soft) Tree + clone ------- 

3 of hearts
 ** illegal **
 3 of hearts
 A of spades

---clone---

3 of hearts
 ** illegal **
 3 of hearts
 A of spades

------------ Physical tree, real then clone------ 

3 of hearts
 (D)
 ** illegal **
 3 of hearts
 A of spades
3 of hearts
 (D)
 ** illegal **
 3 of hearts
 A of spades
------- Testing Sizes (compare with above)------- 

virtual (soft) size: 4
physical (hard) size: 4
clone (hard) size: 4
------------ Collecting Garbage ----------------- 

found soft-deleted nodes? false
immediate collect again? false
--------- Hard Display after garb col ------------ 

3 of hearts
 (D)
 ** illegal **
 3 of hearts
 A of spades
Semi-deleted tree empty? false
Completely-deleted tree empty? false
3 of hearts
 (D)
 ** illegal **
 3 of hearts
 A of spades
--------------END OF RUN 2 MAIN 2--------------


--------------RUN 3 MAIN 3--------------

------------ Loaded Tree ----------------- 

1
 -10
  -85
  69
 12
  87
  542
 33

------------ Virtual (soft) Tree --------------

1
 -10
  69
 12
  87
 33

------------ Physical (hard) Display --------- 

1
 (D)
 -10
  69
 12
  87
 33
------- Testing Sizes (compare with above)------ 

virtual (soft) size: 6
physical (hard) size: 6
--------- Collecting Garbage -------------- 

found soft-deleted nodes? false
immediate collect again? false
--------- Hard Display after garb col --------- 

1
 (D)
 -10
  69
 12
  87
 33
Semi-deleted tree empty? false
Completely-deleted tree empty? false
--------------END OF RUN 3 MAIN 3--------------
*/