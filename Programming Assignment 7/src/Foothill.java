
public class Foothill 
{

	public static void main(String[] args) 
	{
		BooleanFunc segA, segB, segC;

		int  inputX;

		int evenFunc[] = {0, 2, 4, 6, 8, 10, 12, 14};
		int greater9Func[] = {10, 11, 12, 13, 14, 15};;
		int greater3Func[] = {0, 1, 2, 3};
      
		segA = new BooleanFunc();
		segB = new BooleanFunc(13);
		segC = new BooleanFunc(100, true);

		segA.setTruthTableUsingTrue(evenFunc);
		segB.setTruthTableUsingTrue(greater9Func);
		segC.setTruthTableUsingFalse(greater3Func);
		
		System.out.println("---------Mutator Tests for BooleanFunc Class---------");
		
        if (segA.setTruthTableUsingTrue(evenFunc))
            System.out.print("set segA\n");
        else
            System.out.print("failed to set segA\n");
        
        if (segB.setTruthTableUsingTrue(greater9Func))
            System.out.print("set segB\n");
        else
            System.out.print("failed set segB\n");
        if (segC.setTruthTableUsingFalse(greater3Func))
            System.out.print("set segC\n\n");
        else
            System.out.println("failed set segC\n");

        System.out.println("---------Overall Tests for BooleanFunc Class---------");

      // testing class BooleanFunc
		System.out.println("before eval()");
		System.out.println(
				"\n  A(x) = "
						+ segA.getState()
						+ "\n  B(x) = "
						+ segB.getState()
						+ "\n  C(x) = "
						+ segC.getState()
						+ "\n" );
		System.out.println("looping with eval()");

		for (inputX = 0; inputX < 10; inputX++)
		{
			segA.eval(inputX);
			segB.eval(inputX);
			segC.eval(inputX);
			System.out.println(
					"Input: " + inputX
					+ "\n  A(x) = "
					+ segA.getState()
					+ "\n  B(x) = "
					+ segB.getState()
					+ "\n  C(x) = "
					+ segC.getState()
					+ "\n" );
			
        System.out.print("instantiating BooleanFunc segACopy \n");
        BooleanFunc segACopy;
        try
        {
            segACopy = segA.clone();
        } 
        catch (CloneNotSupportedException e)
        {
            System.out.println("caught clone exception");
            segACopy = new BooleanFunc();
        }

        System.out.print( "looping with eval()\n");
        for ( inputX = 0; inputX < 16; inputX++ )
        {
           segA.eval( inputX );
           segACopy.eval( inputX );

           System.out.print(
               "Input: " + inputX
              + "\n  ACopy(x) = "
              + segACopy.getState()
              + "\n  A(x) = "
              + segA.getState()
              +"\n\n"
              );
        }

        System.out.print("segA = segC \n");
        segA = segC;
        System.out.print( "looping with eval()\n");
        for ( inputX = 0; inputX < 16; inputX++ )
        {
           segA.eval( inputX );
           segC.eval( inputX );
           System.out.print(
               "Input: " + inputX
              + "\n  A(x) = "
              + segA.getState()
              + "\n  C(x) = "
              + segC.getState()
              +"\n"
              );
        }

        System.out.print("\n----------End BooleanFunc class Tests----------\n\n");	
		}
		
		System.out.print("\n----------Begin Multi Segment Logic Test----------");
		int inputY, k;
	
	    SevenSegmentLogic my7Seg, myCopy;
	      
	    my7Seg = new SevenSegmentLogic();
	      
	    try
	    {
	       myCopy = (SevenSegmentLogic) my7Seg.clone();
	    }
	    catch  ( CloneNotSupportedException e )
	    {
	       System.out.println("** Clone Unsuccessful **");
	       myCopy = new SevenSegmentLogic();
	    }
	      
	    for ( inputY = 0; inputY < 16; inputY++ )
	    {
	       myCopy.eval( inputY );
	       System.out.print("\n| ");
	       for ( k = 0; k < 7; k++ )
	          System.out.print( myCopy.getValOfSeg( k ) + " | ");
	       System.out.println();
	    }
	    
	    System.out.print("===Seven Segment Display truth table=== \n\n");
        System.out.print("Input#: | A | B | C | D | E | F | G |\n");
        for ( inputY = 0; inputY < 16; inputY++ )
        {
           myCopy.eval( inputY );
           System.out.print(inputY + "      | ") ;
           for (k = 0; k < 7; k++)
           {
              if (myCopy.getValOfSeg(k))
                  System.out.print( "T | ");
              else
                  System.out.print("F | ");
           }
           System.out.print("\n");
        }
        
        System.out.print("state of seg5 for last eval:\n");
        if (myCopy.getValOfSeg(5))
            System.out.print("true (expected)\n");
        else
            System.out.print("false\n");

        System.out.print("\nState of Segment 4 for my7Seg:\n");
        if (my7Seg.getValOfSeg(4))
            System.out.print("true (Expected!)\n");
        else
            System.out.print("false\n");

        System.out.print("\nStat"
        		+ "e of Segment 100 for my7Seg:\n");
        if (my7Seg.getValOfSeg(100))
            System.out.print("true\n");
        else
            System.out.print("false/error (expected)\n");
	}
}

class BooleanFunc implements Cloneable
{
	public static int MAX_TABLE_FOR_CLASS = 65536; // that's 16 binary input lines
	public static int DEFAULT_TABLE_SIZE = 16;
	
	private int tableSize;	
	private boolean[] truthTable;
	private boolean evalReturnIfError;
	private boolean state;
	
	private boolean isValidTable(int tableSize) 
	{
		if (tableSize > MAX_TABLE_FOR_CLASS)
            return false;
        
        return true;
	}
	
	private boolean isValidArray(int arraySize) 
	{
		if (arraySize > tableSize)
			return false;
		
		return true;
	}
	
	private void setTableDefault(boolean tableVal)
    {
        for (int k = 0; k < tableSize; k++)
            truthTable[k] = tableVal;
    }
	
	private boolean initTable(int tableSize)
    {
        if (!isValidTable(tableSize))
            return false;
        
        this.tableSize = tableSize;
        truthTable = new boolean[tableSize];
        
        setTableDefault(evalReturnIfError);
        return true; 
    }
	
	public BooleanFunc() 
	{
		tableSize = DEFAULT_TABLE_SIZE;
		state = evalReturnIfError;
		initTable(tableSize);
	}
	
	public BooleanFunc(int tableSize) 
	{
		if(!isValidTable(tableSize))
            tableSize = DEFAULT_TABLE_SIZE;
        
        this.tableSize = tableSize;
        initTable(tableSize);
        state = evalReturnIfError;
	}

	public BooleanFunc(int tableSize, boolean evalReturnIfError) 
	{
		if (!isValidTable(tableSize))
			tableSize = DEFAULT_TABLE_SIZE;
	    this.tableSize = tableSize;
	    
	    initTable(tableSize);    
        this.evalReturnIfError = evalReturnIfError;
        this.state = evalReturnIfError;
	}
	
	public boolean setTruthTableUsingTrue(int[] inputsThatProduceTrue) 
	{
		if (!isValidArray(inputsThatProduceTrue.length))
            return false;
        
        for (int i = 0; i < inputsThatProduceTrue.length; i++)
        {
            if (inputsThatProduceTrue[i] < tableSize &&
                inputsThatProduceTrue[i] >= 0)
                truthTable[inputsThatProduceTrue[i]] = true;
        }
        
        return true;
	}

	public boolean setTruthTableUsingFalse(int[] inputsThatProduceFalse) 
	{
		if (!isValidArray(inputsThatProduceFalse.length))
            return false;
        
        for (int i = 0; i < inputsThatProduceFalse.length; i++)
        {
            if (inputsThatProduceFalse[i] < tableSize &&
                inputsThatProduceFalse[i] >= 0)
                truthTable[inputsThatProduceFalse[i]] = false;
        }
        
        return true;
	}
	
	public boolean getState() 
	{
		return state;
	}
	
	public boolean eval(int input) 
	{
		if (input < 0 || input>=tableSize) 
			state = evalReturnIfError;
		else
			state = truthTable[input];
					
		return state;
	}
	
	public BooleanFunc clone() throws CloneNotSupportedException
	{
		BooleanFunc newBFunc = (BooleanFunc)super.clone();
		newBFunc.truthTable = new boolean[this.tableSize];
	       
		for (int i = 0; i < tableSize; i++)
			newBFunc.truthTable[i] = this.truthTable[i];
	       
		return newBFunc;
	}
}


class MultiSegmentLogic implements Cloneable
{
	protected int numSegs;
	protected BooleanFunc[] segs;
	
	public MultiSegmentLogic() 
	{
		setNumSegs(0);
	}
	
	public MultiSegmentLogic(int numSegs) 
	{
		if (!setNumSegs(numSegs))
            setNumSegs(0);
	}
	
	public boolean setNumSegs(int numSegs) 
	{
		if (numSegs < 0)
            return false;
     
        this.numSegs = numSegs;
        
        return true;
	}
	
	public boolean setSegment(int segNum, BooleanFunc funcForThisSeg) 
	{
		if (segNum < 0 || segNum >= numSegs)
            return false;
        
        segs[segNum] = funcForThisSeg;
        return true;
	}
	
	public void eval(int input) 
	{
		for (int i = 0; i < numSegs; i++)
            segs[i].eval(input);
	}
	
	public MultiSegmentLogic clone() throws CloneNotSupportedException
	{
		MultiSegmentLogic newMultiSegmentLogic = (MultiSegmentLogic)super.clone();
	       newMultiSegmentLogic.segs = new BooleanFunc[this.numSegs];
	       
	       for (int i = 0; i < numSegs; i++)
	           newMultiSegmentLogic.segs[i] = this.segs[i];
	       
	       return newMultiSegmentLogic;
	}
}

class SevenSegmentLogic extends MultiSegmentLogic
{
	public boolean getValOfSeg(int seg) 
	{
		if (seg < 0 || seg >= numSegs)
            return false;
        
        return segs[seg].getState();
        
	}
}

/*
---------Mutator Tests for BooleanFunc Class---------
set segA
set segB
set segC

---------Overall Tests for BooleanFunc Class---------
before eval()

  A(x) = false
  B(x) = false
  C(x) = true

looping with eval()
Input: 0
  A(x) = true
  B(x) = false
  C(x) = false

instantiating BooleanFunc segACopy 
looping with eval()
Input: 0
  ACopy(x) = true
  A(x) = true

Input: 1
  ACopy(x) = false
  A(x) = false

Input: 2
  ACopy(x) = true
  A(x) = true

Input: 3
  ACopy(x) = false
  A(x) = false

Input: 4
  ACopy(x) = true
  A(x) = true

Input: 5
  ACopy(x) = false
  A(x) = false

Input: 6
  ACopy(x) = true
  A(x) = true

Input: 7
  ACopy(x) = false
  A(x) = false

Input: 8
  ACopy(x) = true
  A(x) = true

Input: 9
  ACopy(x) = false
  A(x) = false

Input: 10
  ACopy(x) = true
  A(x) = true

Input: 11
  ACopy(x) = false
  A(x) = false

Input: 12
  ACopy(x) = true
  A(x) = true

Input: 13
  ACopy(x) = false
  A(x) = false

Input: 14
  ACopy(x) = true
  A(x) = true

Input: 15
  ACopy(x) = false
  A(x) = false

segA = segC 
looping with eval()
Input: 0
  A(x) = false
  C(x) = false
Input: 1
  A(x) = false
  C(x) = false
Input: 2
  A(x) = false
  C(x) = false
Input: 3
  A(x) = false
  C(x) = false
Input: 4
  A(x) = false
  C(x) = false
Input: 5
  A(x) = false
  C(x) = false
Input: 6
  A(x) = false
  C(x) = false
Input: 7
  A(x) = false
  C(x) = false
Input: 8
  A(x) = false
  C(x) = false
Input: 9
  A(x) = false
  C(x) = false
Input: 10
  A(x) = false
  C(x) = false
Input: 11
  A(x) = false
  C(x) = false
Input: 12
  A(x) = false
  C(x) = false
Input: 13
  A(x) = false
  C(x) = false
Input: 14
  A(x) = false
  C(x) = false
Input: 15
  A(x) = false
  C(x) = false

----------End BooleanFunc class Tests----------


----------Begin Multi Segment Logic Test----------
| false | false | false | false | false | false | false | 

| false | false | false | false | false | false | false | 

| false | false | false | false | false | false | false | 

| false | false | false | false | false | false | false | 

| false | false | false | false | false | false | false | 

| false | false | false | false | false | false | false | 

| false | false | false | false | false | false | false | 

| false | false | false | false | false | false | false | 

| false | false | false | false | false | false | false | 

| false | false | false | false | false | false | false | 

| false | false | false | false | false | false | false | 

| false | false | false | false | false | false | false | 

| false | false | false | false | false | false | false | 

| false | false | false | false | false | false | false | 

| false | false | false | false | false | false | false | 

| false | false | false | false | false | false | false | 
===Seven Segment Display truth table=== 

Input#: | A | B | C | D | E | F | G |
0      | F | F | F | F | F | F | F | 
1      | F | F | F | F | F | F | F | 
2      | F | F | F | F | F | F | F | 
3      | F | F | F | F | F | F | F | 
4      | F | F | F | F | F | F | F | 
5      | F | F | F | F | F | F | F | 
6      | F | F | F | F | F | F | F | 
7      | F | F | F | F | F | F | F | 
8      | F | F | F | F | F | F | F | 
9      | F | F | F | F | F | F | F | 
10      | F | F | F | F | F | F | F | 
11      | F | F | F | F | F | F | F | 
12      | F | F | F | F | F | F | F | 
13      | F | F | F | F | F | F | F | 
14      | F | F | F | F | F | F | F | 
15      | F | F | F | F | F | F | F | 

==Tests for SSL getValOfSeg==

state of seg5 for last eval:
false

State of Segment 4 for my7Seg:
false

State of Segment 100 for my7Seg:
false/error (expected)
*/