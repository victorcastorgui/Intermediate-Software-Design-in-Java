public class Foothill
{                          
    public static void main(String[] args)
    {                                 
    	//PHASE 1
        Card card1, card2, card3;
        card1 = new Card(); //legal
        card2 = new Card('B', Card.Suit.diamonds); //illegal
        card3 = new Card ('2', Card.Suit.diamonds); //legal
        
        String report = "card1: " + card1.toString() + "\n" + "card2: " 
              + card2.toString() + "\n" + "card3: " + card3.toString() + "\n";
        
        System.out.println(report);
        
        card1.set('0', Card.Suit.spades);
              
        card2.set('J', Card.Suit.hearts);

        String methodTest = "METHOD TEST: card1 to be illegal, "
              + "card2 to be J of hearts\n";
        
        String reportTest = "card1: " + card1.toString() + "\n" + "card2: " 
              + card2.toString() + "\n" + "card3: " + card3.toString() + "\n";

        System.out.println(methodTest + reportTest);
        
        //PHASE 2
        Card myCard1, myCard2, myCard3, myCard4, myCard5;
        myCard1 = new Card('4', Card.Suit.diamonds);
        myCard2 = new Card();
        myCard3 = new Card('J', Card.Suit.spades);
        myCard4 = new Card('9', Card.Suit.hearts);
        myCard5 = new Card('T', Card.Suit.diamonds);

        Hand myHand = new Hand();

        for(int i = 0; i < Hand.MAX_CARDS; i++)
        {
           myHand.takeCard(myCard1);
           myHand.takeCard(myCard2);
           myHand.takeCard(myCard3);
           myHand.takeCard(myCard4);
           myHand.takeCard(myCard5);
        }
        
        if (myHand.getNumCards() == Hand.MAX_CARDS)
           System.out.println("Hand full");
        else
           System.out.println("Hand not full");
        
        System.out.println("After deal");
        
        System.out.println(myHand.toString());

        System.out.println("\nTesting inspectCard(): ");
        System.out.println(myHand.inspectCard(3) + "\n" + myHand.inspectCard(0)
              + "\n" + myHand.inspectCard(50) + "\n");     
        
        int numCards = myHand.getNumCards();
        
        for(int i = 0; i < numCards; i++)
        {
           System.out.println("Playing " + myHand.playCard());
        }
        
        if (myHand.getNumCards() == 0)
        {
           System.out.println("\nAfter playing all cards:");
           System.out.println(myHand.toString());
        }
     }
  }

//----------TEST RUN ASSIGNMENT 1----------
//card1: A of Spades
//card2: [invalid]
//card3: 2 of Diamonds
//
//METHOD TEST: card1 to be illegal, card2 to be J of hearts
//card1: [invalid]
//card2: [invalid]
//card3: 2 of Diamonds
//
//4 of Diamonds
//A of Spades
//J of Spades
//9 of Hearts
//T of Diamonds
//4 of Diamonds
//A of Spades
//J of Spades
//9 of Hearts
//T of Diamonds
//4 of Diamonds
//A of Spades
//J of Spades
//9 of Hearts
//T of Diamonds
//4 of Diamonds
//A of Spades
//J of Spades
//9 of Hearts
//T of Diamonds
//4 of Diamonds
//A of Spades
//J of Spades
//9 of Hearts
//T of Diamonds
//4 of Diamonds
//A of Spades
//J of Spades
//9 of Hearts
//T of Diamonds
//Hand not full
//After deal
//Hand: 4 of Diamonds 
//A of Spades 
//J of Spades 
//9 of Hearts 
//T of Diamonds 
//4 of Diamonds 
//A of Spades 
//J of Spades 
//9 of Hearts 
//T of Diamonds 
//4 of Diamonds 
//A of Spades 
//J of Spades 
//9 of Hearts 
//T of Diamonds 
//4 of Diamonds 
//A of Spades 
//J of Spades 
//9 of Hearts 
//T of Diamonds 
//4 of Diamonds 
//A of Spades 
//J of Spades 
//9 of Hearts 
//T of Diamonds 
//4 of Diamonds 
//A of Spades 
//J of Spades 
//9 of Hearts 
//T of Diamonds 
//
//
//Testing inspectCard(): 
//9 of Hearts
//4 of Diamonds
//[invalid]
//
//
//After playing all cards:
//Hand: 4 of Diamonds 
//A of Spades 
//J of Spades 
//9 of Hearts 
//T of Diamonds 
//4 of Diamonds 
//A of Spades 
//J of Spades 
//9 of Hearts 
//T of Diamonds 
//4 of Diamonds 
//A of Spades 
//J of Spades 
//9 of Hearts 
//T of Diamonds 
//4 of Diamonds 
//A of Spades 
//J of Spades 
//9 of Hearts 
//T of Diamonds 
//4 of Diamonds 
//A of Spades 
//J of Spades 
//9 of Hearts 
//T of Diamonds 
//4 of Diamonds 
//A of Spades 
//J of Spades 
//9 of Hearts 
//T of Diamonds 
//
