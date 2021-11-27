import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class FoothillPhase2 
{
   static int NUM_CARDS_PER_HAND = 7;
   static int  NUM_PLAYERS = 2;
   static JLabel[] computerLabels = new JLabel[NUM_CARDS_PER_HAND];
   static JLabel[] humanLabels = new JLabel[NUM_CARDS_PER_HAND];  
   static JLabel[] playedCardLabels  = new JLabel[NUM_PLAYERS]; 
   static JLabel[] playLabelText  = new JLabel[NUM_PLAYERS]; 
   
   public static void main(String[] args)
   {
      int k;
      Icon tempIcon;
      
      // establish main frame in which program will run
      CardTable myCardTable 
         = new CardTable("CS 1B CardTable", NUM_CARDS_PER_HAND, NUM_PLAYERS);
      myCardTable.setSize(800, 600);
      myCardTable.setLocationRelativeTo(null);
      myCardTable.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // show everything to the user
      myCardTable.setVisible(true);

      // CREATE LABELS ----------------------------------------------------
      for (k = 0; k < NUM_CARDS_PER_HAND; k++)
      {
          tempIcon = GUICard.getIcon(generateRandomCard());
          humanLabels[k] = new JLabel(tempIcon);
          computerLabels[k] = new JLabel(GUICard.getBackCardIcon());
          myCardTable.computerPanel.add(computerLabels[k]);
          myCardTable.playerPanel.add(humanLabels[k]);
      }
       
      for (k = 0; k < NUM_PLAYERS; k++)
      {
          tempIcon = GUICard.getIcon(generateRandomCard());
          playedCardLabels[k] = new JLabel(tempIcon);
          if (k > 0)
              playLabelText[k] = new JLabel("You", JLabel.CENTER);
          else
              playLabelText[k] = new JLabel("Player " + k + 1, JLabel.CENTER);
      }
      
      playLabelText[0] = new JLabel("Computer", JLabel.CENTER);
      playLabelText[1] = new JLabel("You", JLabel.CENTER);
		
      // ADD LABELS TO PANELS -----------------------------------------
      for(k = 0; k < NUM_CARDS_PER_HAND; k++)
      {
    	  myCardTable.computerPanel.add(computerLabels[k]);
    	  myCardTable.playerPanel.add(humanLabels[k]);
      }
      
      // and two random cards in the play region (simulating a computer/hum ply)
      for (k = 0; k < NUM_PLAYERS; k++)
          myCardTable.playingPanel.add(playedCardLabels[k]);
       
       // two playing area labels
      for (k = 0; k < NUM_PLAYERS; k++)
          myCardTable.playingPanel.add(playLabelText[k]);

      //BORDER
      myCardTable.computerPanel.setBorder(new TitledBorder("Computer Hand"));
      myCardTable.playingPanel.setBorder(new TitledBorder("Playing Area"));
      myCardTable.playerPanel.setBorder(new TitledBorder("Your Hand"));
       
      // show everything to the user
      myCardTable.setVisible(true);
   }
      
      
      static Card generateRandomCard()
      {
    	  int randomValue = (int)Math.round((Math.random()*13));
    	  int randomSuit = (int)Math.round((Math.random()*3));
    	  
    	  return new Card(GUICard.turnIntIntoCardValueChar(randomValue), 
    			  GUICard.turnIntIntoSuit(randomSuit));
      }
}
   
class CardTable extends JFrame
{
	private static final long serialVersionUID = 1L;
	static int MIN_CARDS_PER_HAND = 1;
	static int MAX_CARDS_PER_HAND = 57;
  	static int MAX_PLAYERS = 2;  // for now, we only allow 2 person games
  	static int MIN_PLAYERS = 1;
  	static int DEFAULT_NUM_CARDS_PER_HAND = 6;
  	
  	private int numCardsPerHand;
  	private int numPlayers;
  	
  	JPanel playerPanel, computerPanel, playingPanel;
   	
  	CardTable (String title, int numCardsPerHand, int numPlayers) 
  	{
	   super(title);
	   
	   if(!isValidHand(numCardsPerHand))
		   this.numCardsPerHand = DEFAULT_NUM_CARDS_PER_HAND;
	   else
		   this.numCardsPerHand = numCardsPerHand;
	   
	   if(!isValidNumPlayers(numPlayers))
		   this.numPlayers = MAX_PLAYERS;
	   else
		   this.numPlayers = numPlayers;
	   
	   playerPanel = new JPanel(new GridLayout(1, numCardsPerHand, 10, 10));
	   computerPanel = new JPanel(new GridLayout(1, numCardsPerHand, 10, 10));
	   playingPanel = new JPanel (new GridLayout(2, numPlayers, 10, 10));
	      
	   setLayout(new BorderLayout(20, 10));
	   add(computerPanel, BorderLayout.NORTH);
	   add(playingPanel, BorderLayout.CENTER);
	   add(playerPanel, BorderLayout.SOUTH);
  	}

	public int getNumCardsPerHand() 
	{
		return numCardsPerHand;
	}
	
	public int getNumPlayers() 
	{
		return numPlayers;
	}
	
	private boolean isValidHand (int numCards) 
	{
		return numCards <= MAX_CARDS_PER_HAND && numCards >= MIN_CARDS_PER_HAND;
	}
	
	private boolean isValidNumPlayers(int players) 
	{
		return players >= MIN_PLAYERS && players <= MAX_PLAYERS;
	}
}

class GUICard
{
	private static Icon[][] iconCards = new ImageIcon[14][4]; // 14 = A thru K (+ joker optional)
	private static Icon iconBack;
	private static boolean iconsLoaded = false;
	
	private static String cardValsConvertAssist = "23456789TJQKAX";
	private static String suitValsConvertAssist  = "CDHS";
	private static Card.Suit suitConvertAssist[] = 
	{
		Card.Suit.clubs,
		Card.Suit.diamonds,
		Card.Suit.hearts,
		Card.Suit.spades
	};
	
	static void loadIcons() 
	{
		String imageFileName;
		int intSuit, intVal;
		
		if(iconsLoaded)
			return;
			
		for (intVal = 0; intVal < 14; intVal++)
			for(intSuit = 0; intSuit < 4; intSuit++) 
			{
				imageFileName = "images/" 
						+ turnIntIntoCardValueChar(intVal) 
						+ turnIntIntoCardSuitChar(intSuit) 
						+ ".gif";
				iconCards[intVal][intSuit] = new ImageIcon(imageFileName);
			}
		
		iconBack = new ImageIcon("images/BK.gif");
		
		iconsLoaded = true;
	}
	
	public static char turnIntIntoCardValueChar(int k)
	{
	    if( k < 0 || k > 13)
	       return '?'; 
	    return cardValsConvertAssist.charAt(k);
	}
	
	public static char turnIntIntoCardSuitChar(int k) 
	{
		if(k < 0 || k > 13)
			return '?';
		return suitValsConvertAssist.charAt(k);
	}
	
	public static Card.Suit turnIntIntoSuit(int k)
	{
		if(k < 0 || k > suitConvertAssist.length-1)
			return null;
		
		return suitConvertAssist[k];
	}
	
	static public Icon getIcon(Card card) 
	{
		loadIcons();
		return iconCards[valuesAsInt(card)][suitAsInt(card)];
	}
	
	static public Icon getBackCardIcon() 
	{
		loadIcons();
		return iconBack;
	}   
	
	private static int valuesAsInt (Card card)
	{
		char Value = card.getValue();
		
		for(int i = 0; i < 14; i++) 
			if(Value == cardValsConvertAssist(i)) 
			{
				return i;
			}
		
		return Value;
	}
	
	private static int suitAsInt(Card card) 
	{
		int Suit = 0;
		Card.Suit charSuit = card.getSuit();
		
		for (int i = 0; i < 4; i++)
			if (charSuit == suitConvertAssist[i])
				Suit = i;
		
		return Suit;
	}
}

class Card
{
   public static final char DEFAULT_VALUE = 'A';
   public static final Suit DEFAULT_SUIT = Suit.spades;
   
   public enum Suit {clubs, diamonds, hearts, spades};
   
   private char value;
   private Suit suit;
   private boolean errorFlag;
   
   Card()
   {
      value = DEFAULT_VALUE;
      suit = DEFAULT_SUIT;
      errorFlag = false;
   }
   
   Card(char value, Suit suit)
   {
      set(value, suit);
   }
  
   public boolean set(char value, Suit suit)
   {
      char upperCaseValue;
      
      this.suit = suit;
      
      upperCaseValue = Character.toUpperCase(value);
      
      if (isValid(upperCaseValue, suit))
      {
         errorFlag = false;
         this.value = upperCaseValue;
      }
      else
         errorFlag = true;
      
      return errorFlag;
   }
   
   public String toString()
   {
      String cardData;
      
      if (errorFlag)
         cardData = "*** invalid ***";
      else
         cardData = value + " of " + suit;
      
      
      return cardData;
   }
   
   private static boolean isValid(char value, Suit suit)
   {
      return (value == 'A' || value == 'K' || value == 'Q' || value == 'J'
         || value == 'T' || (value >= '2' && value <= '9') || value == 'X');
   }
   
   public boolean equals(Card card)
   {
      return value == card.value && suit == card.suit;
   }
  
   public char getValue() {return value;}
  
   public Suit getSuit() {return suit;}
  
   public boolean getErrorFlag() {return errorFlag;}
}

