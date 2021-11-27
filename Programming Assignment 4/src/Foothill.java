
public class Foothill 
{
	public static void main (String[] args) 
	{
		//----------------Message Class Test-----------------
		System.out.println("Message Class Test:");
		
		String test;
		Message one = new Message();
		Message two = new Message("Hello Everyone!");
		
		one.setMessage("I love to play League of Legends!\n");
		two.setMessage("Hello Everyone!");
		
		System.out.println(one.toString()); 
		System.out.println(two.toString());

		
		System.out.println("testing Message accessor: ");
		System.out.println(one.getMessage());
		
		System.out.println("Testing mutator: ");
		
		test = "";
		if (one.setMessage(test)) 
			System.out.println("Test is valid. Message mutated");
		else
			System.out.println("Test is invalid. Message is not mutated\n");
		
		System.out.println(one);
		
		System.out.println("Testing message mutator: ");
		test = "testing...1...2...3\n" + "testing...1...2...3";
		if (one.setMessage(test)) 
			System.out.println("Test is valid. Message mutated");
		else
			System.out.println("Test is invalid. Message is not mutated\n");
		
		System.out.println(one);
		
		System.out.println("----------------END OF MESSAGE CLASS TEST-----------------\n\n");
		
		//----------------Email Class Test-----------------
		System.out.println("Email Class Test:");
	     
	      String testAddress;
	      Email emailOne = new Email();
	      Email emailTwo = new Email("Hi Whats Up!", "kobebryant@gmail.com", 
	         "lebronjames@gmail.com");
	      
	      emailOne.setMessage("How's your day?");
	      emailOne.setFromAddress("mother@gmail.com");
	      emailOne.setToAddress("father@foothill.edu");
	      
	      System.out.println(emailOne);
	      System.out.println(emailTwo);
	      
	      System.out.println();
	      System.out.println("Testing Email accessor: ");
	      System.out.println("From address: " + emailTwo.getFromAddress());
	      System.out.println("To address: " + emailTwo.getToAddress());
	      System.out.println("\n");
	      
	      System.out.println("Testing Email mutator: ");
	      
	      testAddress = "iamarobot123";
	      if (emailTwo.setFromAddress(testAddress))
	         System.out.println("Valid email address. Address mutated.");
	      
	      else
	         System.out.println("No @ and DOT. Address not mutated.");
	      
	      System.out.println(emailTwo);
	      System.out.println("\n");
	      
	      System.out.println("Testing Email mutator: ");
	      
	      testAddress = "god@heaven.com";
	      if (emailTwo.setFromAddress(testAddress))
	         System.out.println("Valid email address. Address mutated.");
	      
	      else
	         System.out.println("Invalid email address. Address not mutated.");
	      
	      System.out.println(emailTwo);
	      System.out.println("\n");
	      
	      System.out.println("Testing Email mutator: ");
	      
	      testAddress = "indonesia@asia";
	      if (emailTwo.setToAddress(testAddress))
	         System.out.println("Valid email address. Address mutated.");
	      
	      else
	         System.out.println("Missing DOT char. Address not mutated.");
	      
	      System.out.println(emailTwo);
	      System.out.println("\n");
	      
	      System.out.println("Testing Email mutator: ");
	      
	      testAddress = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx@s.com";
	      if (emailTwo.setToAddress(testAddress))
	         System.out.println(" Valid email address. Address mutated.");
	      
	      else
	         System.out.println(" Address too long. Address not mutated.");
	      
	      System.out.println(emailTwo);
	      System.out.println("\n");
	      
	      System.out.println("----------------END OF EMAIL CLASS TEST-----------------\n\n");
	      
	    //----------------Shweet Class Test-----------------
	      System.out.println("Shweet Class Test:");
	      
	      String testID;
	      Shweet shweetOne = new Shweet();
	      Shweet shweetTwo = new Shweet("Cooking using Gordon Ramsay's Recipe", "Nailed it");
	      
	      shweetOne.setMessage("Yesterday's festival was the best!");
	      shweetOne.setID("victorcastor123");
	      
	      System.out.println(shweetOne);
	      System.out.println("\n");
	      
	      System.out.println(shweetTwo);
	      System.out.println("\n");
	      
	      System.out.println();
	      System.out.println("Testing Shweet accessor: ");
	      System.out.println(shweetTwo.getID());
	      System.out.println("\n");
	      
	      System.out.println("Testing Shweet mutator: ");
	      
	      testID = "awesome--_kobebryant24";
	      if (shweetOne.setID(testID))
	         System.out.println(" Valid ID. ID mutated.");
	      else
	         System.out.println("ID contains illegal characters. ID not mutated.");
	         
	      System.out.println(shweetOne);
	      System.out.println("\n");
	      
	      System.out.println("Testing Shweet mutator: ");
	      
	      testID = "lebron_james23";
	      if (shweetOne.setID(testID))
	         System.out.println(" Valid ID. ID mutated.");
	      else
	         System.out.println(" ID contains illegal characters. ID not mutated.");
	         
	      System.out.println(shweetOne);
	      System.out.println("\n");
	      
	      System.out.println("----------------END OF SHWEET CLASS TEST-----------------\n\n");
	}
}

class Message
{
	public static final int MIN_MSG_LENGTH = 1;
	public static final int MAX_MSG_LENGTH = 100000;
	public static final String DEFAULT_MESSAGE = "(No available message)";
	
	private String message;
	
	// default constructor for message class
	public Message() 
	{
		message = DEFAULT_MESSAGE;
	}
	
	public Message(String message)
	{
		//Testing message if it is available with set massage
		if (!validationHelper (message))
			//If the input is invalid, it will show the default message which is "No available message!"
			this.message = DEFAULT_MESSAGE;
	}
	
	public boolean setMessage(String message)
	{
		if(!validationHelper(message))
			return false;
		else
			this.message = message;	
			return true;
	}
	
	private static boolean validationHelper(String message) 
	{
		//To check if the message is less than the default minimum
		if(message.length() < MIN_MSG_LENGTH)
			return false;
		//To check if the message is more than the default minimum
		if (message.length() > MAX_MSG_LENGTH)
			return false;
		else
			return true;
	} 
	
	//Take message
	public String getMessage() 
	{
		return message;
	}
	
	//Provides a nicely formatted return String for potential screen I/O.
	public String toString() 
	{
		String messageFormat = 
		         " Message ----------------------\n";
		messageFormat += (message);
		messageFormat += "\n-------------------------------\n";
		return messageFormat;
	}
}

class Shweet extends Message
{
	public static final int MAX_SHWITTER_ID_LENGTH = 15;
	public static final int MAX_SHWEET_LENGTH = 140;
	public static final int MIN_SHWITTER_ID_LENGTH = 1;
	public static final String DEFAULT_USER_ID = "(no one)";
   
	private String fromID;
   
	Shweet()
	{
		super();
		fromID = DEFAULT_USER_ID;
	}
	Shweet(String message, String id)
	{
		super();
      
		if (!setID(id))
			fromID = DEFAULT_USER_ID;
      
		if (!setMessage(message))
			setMessage(DEFAULT_MESSAGE);
	}

	public boolean setMessage(String message)
   	{
		if (!isValidShweet(message))
			return false; 
      
		return super.setMessage(message);
   	}
  
	public boolean setID(String id)
	{
		if (!isValidShwitterID(id))
			return false;
      
		fromID = id;
		return true;
	}
   
	public String getID() 
	{
		return fromID;
	}
   
	public String toString()
	{
		String shweetFormat = "Shweet: @" + fromID + "\n" 
				+ (getMessage());
      
		return shweetFormat;
	}
   
	private boolean isValidShwitterID(String id)
	{
		return id.length() >= MIN_SHWITTER_ID_LENGTH 
				&& id.length() <= MAX_SHWITTER_ID_LENGTH
				&& stringHasOnlyAlphaOrNumOrUnderscore(id);
	}
   
	private boolean isValidShweet(String message)
	{
		return message.length() <= MAX_SHWEET_LENGTH;
	}
   
	private boolean stringHasOnlyAlphaOrNumOrUnderscore(String id)
	{
		for (int i = 0; i < id.length(); i++)
			if (!Character.isLetterOrDigit(id.charAt(i)) && id.charAt(i) != '_')
				return false;
      
		return true;
	}

}

class Email extends Message
{
	public static final int MIN_EMAIL_ADDRESS_LENGTH = 3;
	public static final int MAX_EMAIL_ADDRESS_LENGTH = 64; //Maximum allowed local address length is 64
	public static final String DEFAULT_ADDRESS = "(Email address is not Valid)";
	   
	private String toAddress;
	private String fromAddress;
	
	//Default constructor for Email class
	public Email()
	{
		//Set toAddress and fromAddress to default values
		toAddress = DEFAULT_ADDRESS;
		fromAddress = DEFAULT_ADDRESS;
	}

	public Email(String message, String toAddress, String fromAddress) 
	{
		if(!setFromAddress(fromAddress))
			this.fromAddress = DEFAULT_ADDRESS;
		if(!setToAddress(toAddress))
			this.toAddress = DEFAULT_ADDRESS;
	}

	public boolean setToAddress(String Address) 
	{
		if(!isValidEmailAddress(Address))
			return false;
		Address = toAddress;
		return false;
	}

	public boolean setFromAddress(String Address) 
	{
		
		if(!isValidEmailAddress(Address))
			return false;
		Address = fromAddress;
		return false;
	}

	private static boolean isValidEmailAddress(String Address) 
	{
		if(Address.length() > MAX_EMAIL_ADDRESS_LENGTH || Address.length() < MIN_EMAIL_ADDRESS_LENGTH
			|| Address.indexOf('@') == -1 || Address.indexOf('.') == -1)
		return false;
		
		return true;
	}
	
	public String getToAddress() 
	{
		return toAddress;
	}
	
	public String getFromAddress() 
	{
		return fromAddress;
	}
	
	public String toString()
    {
        String emailFormat = super.toString();
        emailFormat += "\nFrom: " + fromAddress + "\n" + "To: " +toAddress +"\n";
        
        return emailFormat;
    }
}



//TEST RUN

/*
Message Class Test:
Message ----------------------
I love to play League of Legends!

-------------------------------

Message ----------------------
Hello Everyone!
-------------------------------

testing Message accessor: 
I love to play League of Legends!

Testing mutator: 
Test is invalid. Message is not mutated

Message ----------------------
I love to play League of Legends!

-------------------------------

Testing message mutator: 
Test is valid. Message mutated
Message ----------------------
testing...1...2...3
testing...1...2...3
-------------------------------

----------------END OF MESSAGE CLASS TEST-----------------


Email Class Test:
Message ----------------------
How's your day?
-------------------------------

From: (Email address is not Valid)
To: (Email address is not Valid)

Message ----------------------
(No available message)
-------------------------------

From: (Email address is not Valid)
To: (Email address is not Valid)


Testing Email accessor: 
From address: (Email address is not Valid)
To address: (Email address is not Valid)


Testing Email mutator: 
No @ and DOT. Address not mutated.
Message ----------------------
(No available message)
-------------------------------

From: (Email address is not Valid)
To: (Email address is not Valid)



Testing Email mutator: 
Invalid email address. Address not mutated.
Message ----------------------
(No available message)
-------------------------------

From: (Email address is not Valid)
To: (Email address is not Valid)



Testing Email mutator: 
Missing DOT char. Address not mutated.
Message ----------------------
(No available message)
-------------------------------

From: (Email address is not Valid)
To: (Email address is not Valid)



Testing Email mutator: 
Address too long. Address not mutated.
Message ----------------------
(No available message)
-------------------------------

From: (Email address is not Valid)
To: (Email address is not Valid)



----------------END OF EMAIL CLASS TEST-----------------


Shweet Class Test:
Shweet: @victorcastor123
Yesterday's festival was the best!


Shweet: @(no one)
Cooking using Gordon Ramsay's Recipe



Testing Shweet accessor: 
(no one)


Testing Shweet mutator: 
ID contains illegal characters. ID not mutated.
Shweet: @victorcastor123
Yesterday's festival was the best!


Testing Shweet mutator: 
Valid ID. ID mutated.
Shweet: @lebron_james23
Yesterday's festival was the best!


----------------END OF SHWEET CLASS TEST-----------------
*/