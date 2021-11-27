
//Programming Assignment 8 
//Date: 03/02/2020
//Owner: Victor Castor
//Instructor: Dave Harden
//Class: CS1B
//Program: This is build on top of assignment 7.
//This programs main job is to display the sevenSegmentDisplays on consoles.
//sevenSegmentImage class allows other class to draw in the 2-d grid.
//sevenSegmentDisplay class allows creating and showing the new seven segment characters.
//Can create varying sizes.

public class Foothill 
{
	
   public static void main(String[] args)
   {
	   SevenSegmentImage ssi = new SevenSegmentImage();

	      System.out.println(
	         "Testing SevenSegmentImage ===================================");

	      ssi.setSize( 7, 9 );
	      System.out.println("Testing for 'a' only");
	      ssi.turnOnCellsForSegment( 'a' );
	      ssi.display();
	      System.out.println("Testing for 'a' and 'b'");
	      ssi.turnOnCellsForSegment( 'b' );
	      ssi.display();
	      System.out.println("Testing for 'a', 'b', and 'c'");
	      ssi.turnOnCellsForSegment( 'c' );
	      ssi.display();
	      System.out.println("Testing for 'a', 'b', 'c', and 'd'");
	      ssi.turnOnCellsForSegment( 'd' );
	      ssi.display();

	      System.out.println("Testing for 'e' only");
	      ssi.clearImage();
	      ssi.turnOnCellsForSegment( 'e' );
	      ssi.display();
	      System.out.println("Testing for 'e' and 'f'");
	      ssi.turnOnCellsForSegment( 'f' );
	      ssi.display();
	      System.out.println("Testing for 'e', 'f', and 'g'");
	      ssi.turnOnCellsForSegment( 'g' );
	      ssi.display();

	      System.out.println("Testing for invalid input");
	      ssi.clearImage();
	      ssi.turnOnCellsForSegment( 'l' );
	      ssi.display();
	      System.out.println("Testing for another invalid input");
	      ssi.turnOnCellsForSegment( '9' );
	      ssi.display();
	      
	      System.out.println("Testing for Copy ===================================");
	      ssi.clearImage();
	      ssi.turnOnCellsForSegment('a');
	      ssi.turnOnCellsForSegment('b');
	      ssi.turnOnCellsForSegment('c');
	      ssi.turnOnCellsForSegment('d');
	      ssi.turnOnCellsForSegment('e');
	      ssi.turnOnCellsForSegment('f');
	      ssi.turnOnCellsForSegment('g');
	
	      //Copying the SevenSegmentImage
	      SevenSegmentImage copy;
	      copy = ssi;
	      //Display of the copy should be the same.
	      copy.display();
	      System.out.print("\n");
	      ssi.display();
	      
	      //Testing for SevenSegmentDisplay
	      SevenSegmentDisplay  my7SegForCon = new SevenSegmentDisplay( 15, 13 );
	      int j;
	      
	      System.out.println("Testing SevenSegmentDisplay ===================================");

	      my7SegForCon.setSize( 7, 9 );
	      for ( j = 0; j < 16; j++ )
	      {
	         my7SegForCon.eval(j);
	         my7SegForCon.loadConsoleImage();
	         my7SegForCon.consoleDisplay();
	      }

	      for ( j = 5; j < 21; j += 4)
	      {
	         my7SegForCon.setSize( j, 2*j + 1 );
	         my7SegForCon.eval( 5 );
	         my7SegForCon.loadConsoleImage();
	         my7SegForCon.consoleDisplay();
	      }
	      
	      my7SegForCon.setSize(9, 9);

	      my7SegForCon.eval(5);
	      my7SegForCon.loadConsoleImage();
	      my7SegForCon.consoleDisplay();
	      my7SegForCon.eval(7);
	      my7SegForCon.loadConsoleImage();
	      my7SegForCon.consoleDisplay();
	      my7SegForCon.eval(8);
	      my7SegForCon.loadConsoleImage();
	      my7SegForCon.consoleDisplay();
	      my7SegForCon.eval(13);
	      my7SegForCon.loadConsoleImage();
	      my7SegForCon.consoleDisplay();
	      my7SegForCon.eval(3);
	      my7SegForCon.loadConsoleImage();
	      my7SegForCon.consoleDisplay();
   }
}

//Used Intructors Solution
//class BooleanFunc ----------------------------------------------------------
class BooleanFunc implements Cloneable
{
   public static final int MAX_TABLE_FOR_CLASS = 65536; // that's 16 binary
   // inputs
   public static final int DEFAULT_TABLE_SIZE = 16;

   private int tableSize;
   private boolean[] truthTable;
   private boolean evalReturnIfError;
   private boolean state;

   // constructors
   public BooleanFunc()
   {
      this(DEFAULT_TABLE_SIZE, false);
   }

   public BooleanFunc(int tableSize)
   {
      this(tableSize, false);
   }

   public BooleanFunc(int tableSize, boolean evalReturnIfError)
   {
      // deal with construction errors in a crude but simple fashion
      if (tableSize > MAX_TABLE_FOR_CLASS || tableSize < 1)
         tableSize = DEFAULT_TABLE_SIZE;
      this.tableSize = tableSize;
      truthTable = new boolean[tableSize];
      setTruthTableUsingTrue( new int[0] ); // anon arg; will set all to false
      this.evalReturnIfError = evalReturnIfError;
      this.state = evalReturnIfError;
   }

   // mutators, acessors
   public boolean setTruthTableUsingTrue(int[] inputsThatProduceTrue)
   {
      int k, kTable;

      if ( inputsThatProduceTrue.length > tableSize )
         return false;

      // they are giving us true values, so we init to false then overwrite
      setTableToConstant( false );

      for ( k = 0; k < inputsThatProduceTrue.length; k++ )
      {
         kTable = inputsThatProduceTrue[k];
         if ( kTable >= 0 && kTable < tableSize )
            truthTable[kTable] = true;
      }

      return true;
   }

   public boolean setTruthTableUsingFalse(int[] inputsThatProduceFalse)
   {
      int k, kTable;

      if ( inputsThatProduceFalse.length > tableSize )
         return false;

      // they are giving us false values, so we init to true then overwrite
      setTableToConstant( true );

      for ( k = 0; k < inputsThatProduceFalse.length; k++ )
      {
         kTable = inputsThatProduceFalse[k];
         if ( kTable >= 0 && kTable < tableSize )
            truthTable[kTable] = false;
      }

      return true;
   }

   public boolean eval(int input)
   {
      if ( !inputInRange( input ) )
         return (state = evalReturnIfError);
      return (state = truthTable[input]);
   }

   public boolean getState()
   {
      return state;
   }

   // deep copy required
   public Object clone() throws CloneNotSupportedException
   {
      int k;

      // array will temporarily point to original object
      BooleanFunc newBf = (BooleanFunc) super.clone();

      newBf.truthTable = new boolean[tableSize];
      for (k = 0; k < tableSize; k++)
         newBf.truthTable[k] = this.truthTable[k];
      return newBf;
   }

   // helpers
   private void setTableToConstant(boolean constVal)
   {
      int k;

      for ( k = 0; k < tableSize; k++ )
         truthTable[k] = constVal;
   }

   private boolean inputInRange(int input)
   {
      if ( input < 0 || input >= tableSize )
         return false;
      else
         return true;
   }
};

//Used Instructors Solution
// class MultiSegmentLogic ----------------------------------------------------
class MultiSegmentLogic  implements Cloneable
{
   protected BooleanFunc[] segs;
   protected int numSegs;

   public MultiSegmentLogic()
   {
      this(0);
   }

   public MultiSegmentLogic( int numSegs )
   {
      if ( !setNumSegs( numSegs ) )
         this.numSegs = 0;
   }

   public boolean setNumSegs( int numSegs )
   {
      int k;

      if ( numSegs < 0 )
         return false;

      this.numSegs = numSegs;

      // allocate new array
      segs = new BooleanFunc[numSegs];
      for (k = 0; k < numSegs; k++)
         segs[k] = new BooleanFunc();

      return true;
   }

   public boolean setSegment( int segNum, BooleanFunc funcForThisSeg )
   {
      if ( !validSeg( segNum ) )
         return false;

      // cloning object so we can pass in anon/temporary BooleanFunc
      try
      {
         segs[segNum] = (BooleanFunc)funcForThisSeg.clone();
      }
      catch ( CloneNotSupportedException e )
      {
         return false;
      }
      return true;
   }

   public void eval( int input )
   {
      int k;
      for ( k = 0; k < numSegs; k++ )
         segs[k].eval( input );  
   }

   // deep copy required
   public Object clone() throws CloneNotSupportedException
   {
      int k;

      // array will temporarily point to original object
      MultiSegmentLogic newMsl = (MultiSegmentLogic) super.clone();

      newMsl.segs = new BooleanFunc[numSegs];
      for (k = 0; k < numSegs; k++)
         newMsl.segs[k] = (BooleanFunc)this.segs[k].clone();
      return newMsl;
   }


   // helpers
   protected boolean validSeg( int seg )
   {
      if ( seg < 0 || seg > numSegs - 1 )
         return false;
      return true;
   }
};

//Used Insrtuctors Solution
//class SevenSegmentLogic ----------------------------------------------------
class SevenSegmentLogic extends MultiSegmentLogic
{
   public SevenSegmentLogic()
   {
      super.setNumSegs( 7 );  // careful not to call sibling which does nothing
      loadAllFuncs();
   }
   
   // method to prevent the base-class from changing away from 7
   public boolean setNumSegs( int numSegs )
   {
      if (numSegs != 7)
         return false;
      return true;
   }
   
   public boolean getValOfSeg( int seg )
   {
      if ( !validSeg( seg ) )
         return false;
      return segs[seg].getState();
   }

   private void loadAllFuncs()
   {
      // we use letters, rather than arrays, to help connect with traditional
      // a, b, ... g segements

      // define all in terms of on/true
      int segA[] = { 0, 2, 3, 5, 6, 7, 8, 9, 10, 12, 14, 15 };
      int segB[] = { 0, 1, 2, 3, 4, 7, 8, 9, 10, 13 };
      int segC[] = { 0, 1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 13 };
      int segD[] = { 0, 2, 3, 5, 6, 8, 9, 11, 12, 13, 14 };
      int segE[] = { 0, 2, 6, 8, 10, 11, 12, 13, 14, 15 };
      int segF[] = { 0, 4, 5, 6, 8, 9, 10, 11, 12, 14, 15 };
      int segG[] = { 2, 3, 4, 5, 6, 8, 9, 10, 11, 13, 14, 15 };

      // set error pattern to "E" through second parameter
      BooleanFunc a = new BooleanFunc( 16, true );
      a.setTruthTableUsingTrue( segA );
      BooleanFunc b = new BooleanFunc( 16, false );
      b.setTruthTableUsingTrue( segB );
      BooleanFunc c = new BooleanFunc( 16, false );
      c.setTruthTableUsingTrue( segC );
      BooleanFunc d = new BooleanFunc( 16, true );
      d.setTruthTableUsingTrue( segD );
      BooleanFunc e = new BooleanFunc( 16, true );
      e.setTruthTableUsingTrue( segE );
      BooleanFunc f = new BooleanFunc( 16, true );
      f.setTruthTableUsingTrue( segF );
      BooleanFunc g = new BooleanFunc( 16, true );
      g.setTruthTableUsingTrue( segG );

      // this block could be combined with above; done separately for clarity
      setSegment( 0, a );
      setSegment( 1, b );
      setSegment( 2, c );
      setSegment( 3, d );
      setSegment( 4, e );
      setSegment( 5, f );
      setSegment( 6, g );
   }
}

//class SevenSegmentImage ------------------------------------------------------------
//
class SevenSegmentImage implements Cloneable
{

   public static final int MIN_HEIGHT = 5;
   public static final int MIN_WIDTH = 5;
   public static final int MAX_HEIGHT = 65;
   public static final int MAX_WIDTH = 41;
   public static final String DRAW_CHAR = "*";
   public static final String BLANK_CHAR = " ";

   private boolean[][] data;
   private int topRow, midRow, bottomRow, leftCol, rightCol;

   
   
   
   
   
   //Default constructor for SevenSegmentImage class. Creates min width and min height
   //Initialize boolean member.
   public SevenSegmentImage()
   {
	   setSize(MIN_WIDTH, MIN_HEIGHT);
   }

   
   
   
   
   
   
   //Overload constructor and takes two int parameter. 
   //Check for correct input of width and height then sets both of it.
   //If it fails then it is set to the minimum.
   public SevenSegmentImage(int width, int height)
   {
      if (!setSize(width, height))
    	  setSize(MIN_WIDTH, MIN_HEIGHT);
   }

   
   
   
   
   
   
   //ClearImage is clearing the SevenSegmentImage class by going throw the data array
   //and set all to false to remove it.
   public void clearImage()
   {
      for(int i = 0; i <= bottomRow; i++)
	  for(int j = 0; j <= rightCol; j++)
		  data[i][j]= false;
   }

   
   
   
   
   
   
   //turnOnCellsForSegment takes in one parameter. It enables user to enter lowercase
   //and uppercase. There are two helpers to draw the correct image, drawHorizontal
   //drawVertical. switch statements are used to replace if/else to not make the code long.
   public boolean turnOnCellsForSegment(char segment)
   {
       char displayTheLetters;
       //allows both upper and lower case
       displayTheLetters = Character.toUpperCase(segment);
       
       switch (displayTheLetters) 
       {
	       case 'A':
	       drawHorizontal(topRow);
	       break; 
	       
	       case 'B':
	       drawVertical(leftCol, topRow, midRow);
	       break;
	       
	       case 'C':
	       drawHorizontal(midRow);
	       break;
	       
	       case 'D':
	       drawVertical(rightCol, topRow, midRow);
	       break;
	       
	       case 'E':
	       drawVertical(leftCol, midRow, bottomRow);
	       break;
	       
	       case 'F':
	       drawHorizontal(bottomRow);
	       break;
	       
	       case 'G':
	       drawVertical(rightCol, midRow, bottomRow);
	       break;
	       
	       default:
	    	   return false;
       }
       return true;
   }

   
   
   
   
   
   
   //setSize takes in two parameter. Before setting sizes, check if the size given is valid
   //a helper validateSize is used. After setting and checking, it then initializes
   //in the data[][]
   public boolean setSize(int width, int height)
   {
      if(!validateSize(width, height))
    	  return false;
      
      topRow = 0;
      midRow = bottomRow / 1;
      bottomRow = height - 1;
      leftCol = 0;
      rightCol = width - 1;
      
      allocateCleanArray();
      return true;
   }

   
   
   
   
   
   //Outputs the entire image by for loop. The for loop go through the data[][].
   public void display()
   {
	   int rows = bottomRow + 1;
       int cols = rightCol + 1;

       String show = new String();
       for (int i = 0; i < rows; i++)
       {
          for (int j = 0; j < cols; j++)
          {
             if (data[i][j])
                show += DRAW_CHAR;
             else
                show += BLANK_CHAR;
          }
          show += "\n";
       }
       System.out.println(show);
   }
   
   
   
   
   
   
   
   // deep copy required
   public SevenSegmentImage clone() throws CloneNotSupportedException
   {
	   SevenSegmentImage newSevenSegmentImage = (SevenSegmentImage)super.clone();
       
       int tall;
       int wide;

       tall = this.bottomRow + 1;
       wide = this.rightCol + 1;
       newSevenSegmentImage.data = new boolean[tall][wide];
       
       for (int i = 0; i <= this.bottomRow; i++)
           for (int j = 0; j <= this.rightCol; j++)
              newSevenSegmentImage.data[i][j] = this.data[i][j];
     
       return newSevenSegmentImage;
   }

   
   
   
   
   
   //Helper that is used in setSize. It takes in 2 parameters. width and height is divided
   //by 2 in order to make it equal. If width and height exceeded, it will return false
   //Otherwise, it will return true.
   private boolean validateSize(int width, int height)
   {
      if(width < MIN_WIDTH || width > MAX_WIDTH || height < MIN_HEIGHT || height > MAX_HEIGHT
    		  || width % 2 == 0 || height % 2 == 0)
    	  return false;
      return true; 
   }

   
   
   
   
   //Private helper that used to prevent client from instantiate bad data.
   //bottomRow and rightCol are used.
   private void allocateCleanArray()
   {
	   int tall = bottomRow + 1;
	   int wide = rightCol + 1;
      
	   data = new boolean[tall][wide];
	   clearImage();
   }

   
   
   
   
   
   //Helper that takes in one parameter. It sets the row to true and false if any
   //invalid data is detected.
   // helpers - not required, but used by instructor
   public void drawHorizontal(int row)
   {
	   if(row < topRow || row > bottomRow)
		   return;  
     
	   for (int i = leftCol; i <= rightCol; i++ )
		   data[row][i] = true;
   }

   
   
   
   
   
   //Same with drawVertical, but this helper takes in three parameter. 
   //If any invalid data it will turn to false and vice versa.
   public void drawVertical(int col, int startRow, int stopRow)
   {
	   if(startRow < topRow || startRow > bottomRow || stopRow < topRow || stopRow > bottomRow
			  || col < leftCol || col > rightCol)
		   return;
	   for (int i = startRow; i <= stopRow; i++ )
	         data[i][col] = true;
   }
}

//class SevenSegmentDisplay ----------------------------------------------------------
class SevenSegmentDisplay  implements Cloneable
{
   private SevenSegmentImage theImage;
   private SevenSegmentLogic theDisplay;

   
   
   
   
   
   
   //Default constructor. Initializes theImage and theDisplay.
   public SevenSegmentDisplay()
   {
	  theDisplay = new SevenSegmentLogic();
      theImage = new SevenSegmentImage();
   }
   
   
   
   
   
   
   //Overloaded constructor. It takes two parameters for setSize function.
   public SevenSegmentDisplay( int width, int height )
   {
      theImage = new SevenSegmentImage(width, height);
      theDisplay = new SevenSegmentLogic();
   }
   
   
   
   
   
   
   //boolean function that takes in 2 parameters. This function directly return value.
   public boolean setSize(int width, int height)
   {
      return theImage.setSize(width, height);
   }
   
   
   
   
   
   
   //clearing the theImage data. Then
   public void loadConsoleImage()
   {
      theImage.clearImage();
      char letter = 'a';
      for (int i = 0; i < 7; i++)
      {
         if (theDisplay.getValOfSeg(i))
            theImage.turnOnCellsForSegment(letter);
         letter++;
      }
   }
   
   
   
   
   
   
   
   //Calls the display function.
   public void consoleDisplay()
   {
      theImage.display();
   }
   
   
   
   
   
   
   //Pass input to theDisplay.
   public void eval( int input )
   {
      theDisplay.eval(input);
   }

   
   
   
   
   
   //deep copy required
   public Object clone() throws CloneNotSupportedException
   {
	   SevenSegmentDisplay newSevenSegmentDisplay = (SevenSegmentDisplay)super.clone();
       
       newSevenSegmentDisplay.theImage = this.theImage.clone();
       newSevenSegmentDisplay.theDisplay = (SevenSegmentLogic) this.theDisplay.clone();
     
       return newSevenSegmentDisplay;
   }
}

/*
Testing SevenSegmentImage ===================================
Testing for 'a' only
*******
       
       
       
       
       
       
       
       

Testing for 'a' and 'b'
*******
*      
*      
*      
*      
       
       
       
       

Testing for 'a', 'b', and 'c'
*******
*      
*      
*      
*******
       
       
       
       

Testing for 'a', 'b', 'c', and 'd'
*******
*     *
*     *
*     *
*******
       
       
       
       

Testing for 'e' only
       
       
       
       
*      
*      
*      
*      
*      

Testing for 'e' and 'f'
       
       
       
       
*      
*      
*      
*      
*******

Testing for 'e', 'f', and 'g'
       
       
       
       
*     *
*     *
*     *
*     *
*******

Testing for invalid input
       
       
       
       
       
       
       
       
       

Testing for another invalid input
       
       
       
       
       
       
       
       
       

Testing for Copy ===================================
*******
*     *
*     *
*     *
*******
*     *
*     *
*     *
*******


*******
*     *
*     *
*     *
*******
*     *
*     *
*     *
*******

Testing SevenSegmentDisplay ===================================
*******
       
       
       
       
       
       
       
*******

       
       
       
       
       
       
       
       
       

*******
       
       
       
       
       
       
       
       

*******
       
       
       
       
       
       
       
       

       
       
       
       
       
       
       
       
*******

*******
       
       
       
       
       
       
       
*******

*******
       
       
       
       
       
       
       
*******

*******
       
       
       
       
       
       
       
       

*******
       
       
       
       
       
       
       
*******

*******
       
       
       
       
       
       
       
*******

*******
       
       
       
       
       
       
       
*******

       
       
       
       
       
       
       
       
*******

*******
       
       
       
       
       
       
       
*******

       
       
       
       
       
       
       
       
       

*******
       
       
       
       
       
       
       
*******

*******
       
       
       
       
       
       
       
*******

*****
    *
    *
    *
    *
    *
    *
    *
*****
    *
*****

*********
        *
        *
        *
        *
        *
        *
        *
        *
        *
*********
        *
        *
        *
        *
        *
        *
        *
*********

*************
            *
            *
            *
            *
            *
            *
            *
            *
            *
            *
            *
            *
            *
            *
            *
            *
            *
*************
            *
            *
            *
            *
            *
            *
            *
*************

*****************
                *
                *
                *
                *
                *
                *
                *
                *
                *
                *
                *
                *
                *
                *
                *
                *
                *
                *
                *
                *
                *
                *
                *
                *
                *
*****************
                *
                *
                *
                *
                *
                *
                *
*****************

*********
         
         
         
         
         
         
         
*********

*********
         
         
         
         
         
         
         
         

*********
         
         
         
         
         
         
         
*********

         
         
         
         
         
         
         
         
         

*********
         
         
         
         
         
         
         
         

*/

