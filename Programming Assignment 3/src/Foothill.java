import java.util.Scanner;
public class Foothill 
{
	public static void main(String[] args)
	   {
		Scanner input = new Scanner(System.in);
	      String userResponse;
	      int userInt;
	      int invalid = -1;
	      Automaton myAutomaton;
	      do
	      {
	         System.out.println("Enter Rule (0 - 255): ");
	         try
	         {
	            userResponse = input.nextLine();
	            userInt = Integer.parseInt(userResponse);
	         }
	         catch(NumberFormatException error)
	         {
	            userInt = invalid;
	         }
	      }while(userInt < Automaton.MIN_RULE || userInt > Automaton.MAX_RULE);
	      myAutomaton = new Automaton(userInt);
	      System.out.println("start");
	      
	      for(int i = 0; i < 50; i++)
	      {
	         System.out.println(myAutomaton.toStringCurrentGen());
	         myAutomaton.propagateNewGeneration();
	      }
	      
	      System.out.println("end");
	      input.close();
	   }
}

class Automaton
{
   // class constants
   public final static int RULES_SIZE = 8;
   public final static int BITS_IN_RULE_SIZE 
      = (int)(Math.log(RULES_SIZE) / Math.log(2));
   public final static int MIN_DISPLAY_WIDTH = 21;
   public final static int MAX_DISPLAY_WIDTH = 121;
   public final static int DFLT_DISPLAY_WIDTH = 79;
   public final static int MIN_RULE = 0;
   public final static int MAX_RULE = (int)Math.pow(2,RULES_SIZE) - 1; // 255
   public final static int DFLT_RULE = 1;
   
   public final static String ON_STR = "*";
   public final static String OFF_STR = " ";
   
   // private members
   private boolean rule[];   // allocate rule[8] in constructor!
   private String thisGen;   // same here
   String extremeBit; // bit, "*" or " ", implied everywhere "outside"
   int displayWidth;  // an odd number so it can be perfectly centered
   
   // public constructors, mutators, etc. (need to be written)
   public Automaton(int newRule) 
   {
	   rule = new boolean[RULES_SIZE];
	   thisGen = ON_STR;
	   extremeBit = OFF_STR;
	   
	   setDisplayWidth(DFLT_DISPLAY_WIDTH);
   }
   public void resetFirstGen() 
   {
	   thisGen = ON_STR;
	   extremeBit = OFF_STR;
   }
   public boolean setRule(int newRule) 
   {
	   if (newRule > MAX_RULE || newRule < MIN_RULE);
	   return false;
	   
   }
   public boolean setDisplayWidth(int width) 
   {
	   if (width % 2 == 0 || width > MAX_DISPLAY_WIDTH || width < MAX_DISPLAY_WIDTH)
		   return false;
	   
	   displayWidth = width;
	   
	   return true;
   }
   public String toStringCurrentGen() 
   {
	   String returnString = thisGen;
	   
	   int remove;
	   int currentLength = thisGen.length();
	   if (thisGen.length() < displayWidth)
		   while(returnString.length() < displayWidth)
			   returnString = extremeBit + returnString + extremeBit;
	   else
	   {
		   remove = (currentLength - displayWidth) / 2;
		   returnString = thisGen.substring(remove, currentLength - remove);
	   }
	   return returnString;
   }
public void propagateNewGeneration() 
   {
	   String temp = extremeBit + extremeBit + thisGen + extremeBit + extremeBit;
	   String nextGen = "";
	   String template =  "  *  *  *** *";
	   
	   boolean apply = false;
	   for (int i = 1; i < (temp.length() - 1); i++) 
	   {
		   for (int j = 0;j< RULES_SIZE; j++)
		   {
			   if (temp.charAt (i - 1) == template.charAt(2)
					   && temp.charAt(i) == template.charAt(1)
					   && temp.charAt(i + 1) == template.charAt(0))
					   apply = rule[j];			   
		   }
		   if (apply)
			   nextGen += ON_STR;
		   else nextGen += OFF_STR;
	   }
	   thisGen = nextGen;
	   
	   if (extremeBit == OFF_STR)
		   apply = rule[0];
	   else
		   apply = rule[RULES_SIZE -1];
	   
	   
	   if (apply)
		   extremeBit = ON_STR;
	   else
		   extremeBit = OFF_STR;
   }
}

/*
Enter Rule (0 - 255): 4
start
*

















































end
*/