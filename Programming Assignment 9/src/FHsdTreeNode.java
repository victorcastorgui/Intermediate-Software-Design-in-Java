public class FHsdTreeNode<E> extends FHtreeNode<E>
{
	protected boolean deleted;
	
	
	
	
	
	
	//Default constructor for FHsdTreeNode class. Calls base class and sets deleted to false.
	//Super is used to call constructors.
	public FHsdTreeNode()
	{
		super();
		deleted = false;
	}
	
	
	
	
	
	
	//Overloaded constructor for FHsdTreeNode class. Calls base class, the parameters, and set
	//boolean this.deleted to deleted. Super is used to call constructors.
	public FHsdTreeNode(E d, FHtreeNode<E> sb, FHtreeNode<E> chld, FHtreeNode<E> prv, boolean deleted)
	{
		super(d, sb, chld, prv);
		this.deleted = deleted;
	}
	
	
	
	
	
	
	//Accessors and mutators. Get and return.
	public E getData() 
	{
		return data;
	}
	public FHsdTreeNode<E> getFirstChild()
	{
		return (FHsdTreeNode<E>) 
				firstChild;
	}
	
	public FHsdTreeNode<E> getPrev()
	{
		return (FHsdTreeNode<E>) 
				prev;
	}
	
	public FHsdTreeNode<E> getMyRoot()
	{
		return (FHsdTreeNode<E>) 
				myRoot;
	}
	
	public FHsdTreeNode<E> getSib()
	{
		return (FHsdTreeNode<E>) 
				sib;
	}
	
	
	
	
	
	
	//Basic mutator for sib member of the parent class or superclass. It takes in one parameter
	//and set it to false if sb = null. Else, if sib = sb it returns true;
	public boolean setSib(FHtreeNode<E> sb) 
	{
		if (sb==null)
			return false;
		
		this.sib = sb;
		return true;
	}
	
	
	
	
	
	
	//Basic mutator for prev member of the parent class or superclass. It takes in one parameter
	//and set it to false if prev = null. Else, if this.prev = prev it returns true;
	public boolean setPrev(FHtreeNode<E> prev) 
	{
		if(prev==null)
			return false;
		
		this.prev = prev;
		return true;
	}
	
	
	
	
	
	
	//Basic mutator for firstChild member of the parent class or superclass. It takes in one parameter
	//and set it to false if firstChild = null. Else, if this.firstChild = firstChild it 
	//returns true;
	public boolean setFirstChild(FHtreeNode<E> firstChild) 
	{
		if(firstChild==null)
			return false;
		
		this.firstChild = firstChild;
		return true;
	}
	
	
	
	
	
	
	//Basic mutator for myRoot member of the parent class or superclass. It takes in one parameter
	//and set it to false if myRoot = null. Else, if this.myRoot = myRoot it returns true;
	public boolean setMyRoot(FHtreeNode<E> myRoot) 
	{
		if(myRoot==null)
			return false;
		
		this.myRoot = myRoot;
		return true;
	}
}
