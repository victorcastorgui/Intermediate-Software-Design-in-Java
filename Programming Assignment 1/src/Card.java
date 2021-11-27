public class Card {
	enum Suit {clubs, diamonds, hearts, spades}
		private char value;
		private Suit suit;
		private boolean errorFlag;
		
		public Card() 
		{
			this.set('A', Suit.spades);
		}
		
		public Card(char value, Suit suit) 
		{
			this.set(value, suit);
		}
		
		public String toString() 
		{
			if (errorFlag == true)
				return "[invalid]";
			String card = Character.toString(value) + " of " + suit;
		      
	        switch (suit)
	        {   
	         case clubs:
	            card += "Clubs";
	            break;
	         case diamonds:
	            card += "Diamonds";
	            break;
	         case hearts:
	            card += "Hearts";
	            break;
	         case spades:
	            card += "Spades";
	            break;
	         default:
	            break;
	        }
			return card;
		}
		
		public boolean set(char value, Suit suit)
		{
			if (errorFlag) return false;
			errorFlag = !isValid(value, suit);
			this.value = value;
			this.suit = suit;
			return true;
		}
		
		private static boolean isValid(char value, Suit suit) 
		{
			if (suit!=Suit.diamonds && suit!=Suit.clubs && suit!=Suit.spades 
			&&suit!=Suit.hearts)
			{
				return false;
			}
			if (value!='A' && value!='2' && value!='3' && value!='4' && value!='5' && value!='6'
			&& value!='7' && value!='8' && value!='9' && value!='T' && value!='J' && value!='Q' 
			&& value!='K') 
			{
				return false;
			}
			return true;
	
		}
		public char getValue() 
		{
			return value;
		}
		public Suit getSuit() 
		{
			return suit;
		}
		public boolean equals(Card card) 
		{
			if (this.suit == card.suit && this.errorFlag == card.errorFlag && this.value == card.value) 
			{
				return true;
			}
				return false;
		}

		public boolean geterrorFlag() {
			return errorFlag;
		}
}
