//Copied from FHtree class. Override, changed, add methods.
public class FHsdTree<E> extends FHtree<E> implements Cloneable
{
	
	protected int sizePhysical() 
	{
		return super.size();
	}
	
	protected FHsdTreeNode<E> mRoot;
   
	public FHsdTree() 
	{ 
		clear();
	}
	public boolean empty() 
	{ 
		return (mSize == 0);
	}
	
	public int size() 
	
	{
		return size((FHsdTreeNode<E>)(mRoot), 0); 
	}
	
	public void clear() 
	{ 
		mSize = 0; mRoot = null; 
	}
   
	//Version 1 of find
	public FHsdTreeNode<E> find(E x) 
	{ 
		return find((FHsdTreeNode<E>)mRoot, x, 0); 
	}
	
	//Version 1 of remove
	public boolean remove(E x) 
	{ 
		return remove((FHsdTreeNode<E>)mRoot, x);
	}
	
	//Version 1 of display
	public void  display()
	{ 
		display((FHsdTreeNode<E>)mRoot, 0);
	}
   
	public < F extends Traverser< ? super E > > 
	void traverse(F func)  
	{
		traverse(func, (FHsdTreeNode<E>)mRoot, 0);
	}
   
	
	
	
	
	
	//addChild has been over written. First, add node child to tree. 
	//If tree is empty, node will be used to initialize the root because the 
	//the treeNode parameter is still null. If the treeNode parameter is valid, the node will
	//be added as a child.
	public FHsdTreeNode<E> addChild( FHsdTreeNode<E> treeNode,  E x )
	{
		// empty tree? - create a root node if user passes in null
		if (mSize == 0)
		{
			if (treeNode != null)
				return null; // error something's fishy.  treeNode can't right (instructor's comment)
			this.mRoot = new FHsdTreeNode<E>(x, null, null, null, false);
			((FHsdTreeNode<E>)mRoot).setMyRoot(mRoot);
			mSize = 1;
			return (FHsdTreeNode<E>)mRoot;
		}
		if (treeNode == null)
			return null; // error inserting into non_null tree with a null parent
		//(instructor's comment)

		if(treeNode.deleted)
			return null;
		if (treeNode.getMyRoot() != mRoot)
			return null;

		// push this node into the head of the sibling list; adjust prev pointers 
		//(instructor's comment)
		FHsdTreeNode<E> newNode = new FHsdTreeNode<E>(x, treeNode.getFirstChild(), null, 
				treeNode, false);  // sb, chld, prv, rt, deleted
		treeNode.setFirstChild(newNode);
		newNode.setMyRoot(mRoot);
		if (newNode.getSib() != null)
			newNode.getSib().setPrev(newNode);
		++mSize;
		return (FHsdTreeNode<E>) newNode;  
	}
   
	
	
	
	
	//Version 2 of find.
	//find has been over written. find method is used to search the tree. 
	//It returns null if the current node is invalid. On the other hand, it will return current node.
	//If find does not find, it will go to siblings and childrens until it finds data x.
	public FHsdTreeNode<E> find(FHsdTreeNode<E> root, E x, int level)
	{
		FHsdTreeNode<E> retval;

		if (mSize == 0 || root == null || root.deleted)
			return null;

		if (root.getData().equals(x))
			return root;

		// otherwise, recurse.  don't process sibs if this was the original call (instructor's comment)
		if ( level > 0 && (retval = find(root.getSib(), x, level)) != null )
			return retval;
		return find(root.getFirstChild(), x, ++level);
	}
   
	
	
	
	
	
	//Version 2 of remove.
	//Overrides remove. If a node is marked as deleted, then the entire child sub-tree
	//is considered gone. If we find that the root is 
    //deleted, null, or the tree is empty, it will return false. Else,
    //the function searches the tree for to remove node using tn. 
	//If it finds this node in the tree and is valid, node will be set as deleted member 
	//to true and return true.
	public boolean remove(FHsdTreeNode<E> root, E x)
	{
		FHsdTreeNode<E> tn = null;

		if (mSize == 0 || root == null || root.deleted)
			return false;
     
		if ( (tn = find(root, x, 0)) != null )
		{
    	    removeNode(tn);
    	    mSize--;
    	    return true;
		}
		return false;
	}
   
	
	
	
	
	
	public void removeNode(FHsdTreeNode<E> nodeToDelete )
	{
		if (nodeToDelete == null || mRoot == null)
			return;
		if (nodeToDelete.myRoot != mRoot)
			return;  // silent error, node does not belong to this tree (instructor's comment)

		// remove all the children of this node (instructor's comment)
		while (nodeToDelete.firstChild != null)
			removeNode(nodeToDelete.getFirstChild());

		if (nodeToDelete.prev == null)
			mRoot = null;  // last node in tree
		else if (nodeToDelete.prev.sib == nodeToDelete)
			nodeToDelete.prev.sib = nodeToDelete.sib; // adjust left sibling (instructor's comment)
		else
			nodeToDelete.prev.firstChild = nodeToDelete.sib;  // adjust parent (instructor's comment)

		// adjust the successor sib's prev pointer (instructor's comment)
		if (nodeToDelete.sib != null)
			nodeToDelete.sib.prev = nodeToDelete.prev;
	}
   
	
	
	
	
	
	public Object clone() throws CloneNotSupportedException
	{
		FHsdTree<E> newObject = (FHsdTree<E>)super.clone();
		newObject.clear();  // can't point to other's data (instructor's comment)

		newObject.mRoot = cloneSubtree(mRoot);
		newObject.mSize = mSize;
		newObject.setMyRoots(newObject.mRoot);
      
		return newObject;
	}
   
	
	
	
	
	
	//----------------------------
	private FHsdTreeNode<E> cloneSubtree(FHsdTreeNode<E> root)
	{
		FHsdTreeNode<E> newNode;
		if (root == null)
			return null;

		// does not set myRoot which must be done by caller (instructor's comment)
		newNode = new FHsdTreeNode<E>
		(
				root.data, 
				cloneSubtree(root.getSib()), cloneSubtree(root.getFirstChild()),
				null, root.deleted
		);
      
		// the prev pointer is set by parent recursive call ... this is the code:
		//(instructor's comment)
		if (newNode.sib != null)
			newNode.sib.prev = newNode;
		if (newNode.firstChild != null)
			newNode.firstChild.prev = newNode;
		return newNode;
	}
   
	
	
	
	
	
	// recursively sets all myRoots to mRoot (instructor's comment)
	protected void setMyRoots(FHsdTreeNode<E> treeNode)
	{
		if (treeNode == null)
			return;

		treeNode.myRoot = mRoot;
		setMyRoots(treeNode.sib);
		setMyRoots(treeNode.firstChild);
	}
   
	
	
	
	
	
	
	// define this as a static member so recursive display() does not need 
	// a local version (instructor's comment)
	final static String blankString = "                                    ";
   
	
	
	
	
	
	
	//Version 2 of display.
	//let be public so client can call on subtree (instructor's comment). display() takes in 2 parameters, level and
	//treeNode. First level, if the function finds an invalid node or searched until the end and have nothing,
	//it will return null. Else, it will output the data.
	public void  display(FHsdTreeNode<E> treeNode, int level) 
	{
		String indent;

		// stop runaway indentation/recursion
		if  (level > (int)blankString.length() - 1)
		{
			System.out.println( blankString + " ... " );
			return;
		}
      
		if (treeNode == null)
			return;

		indent = blankString.substring(0, level);
		
		if(!treeNode.deleted) 
		{
			System.out.println( indent + treeNode.getData() );
			display(treeNode.getFirstChild(), level + 1);
			display(treeNode.getSib(), level);
		}
		else
		if (level > 0 )
		{
			display( treeNode.getSib(), level );
		}
	}
      
	
	
	
	
	
	//Traverser takes in function and apply the function to nodes. 
	//Traverser will not delete nodes.
	// often helper of typical public version, but also callable by on subtree (instructor's comment)
	public <F extends Traverser<? super E>> 
	void traverse(F func, FHsdTreeNode<E> treeNode, int level)
	{
		if (treeNode == null || treeNode.deleted)
			return;

		func.visit(treeNode.getData());
      
		// recursive step done here
		traverse( func, treeNode.getFirstChild(), level + 1);
		if (level > 0 )
			traverse( func,  treeNode.getSib(), level);
	}
	
	
	
	
	
	//Size takes in two parameters. If treeNode is null, it will just return the current value. 
	//The function will always check for valid siblings. If it finds child, sibling, it will 
	//continue to add
	private int size(FHsdTreeNode<E> treeNode, int level) 
	{
		if(treeNode == null)
			return 0;
		
		int ans = 0;
		
		if (!treeNode.deleted) ans++;
		
		if (treeNode.getFirstChild()!=null && !treeNode.deleted); 
			ans += size(treeNode.getFirstChild(), level+1);
			
		if (treeNode.getSib()!=null && level!=0)
			ans += size(treeNode.getSib(), level);
		
		return ans;
	}
	
	
	
	
	
	//It just has a new name in this regime since the old name, size(), is now used 
	//to return the virtual size of the tree (a count of non-deleted nodes.
	public int sizePhysical(FHsdTreeNode<E> treeNode, int level) 
	{
		if(treeNode == null)
			return 0;
		
		int ans = 0;
		
		if (!treeNode.deleted) ans++;
		
		if (treeNode.getFirstChild()!=null && !treeNode.deleted); 
			ans += size(treeNode.getFirstChild(), level+1);
			
		if (treeNode.getSib()!=null && level!=0)
			ans += size(treeNode.getSib(), level);
		
		return ans;
	}
	
	
	
	
	
	//Version 1 of displayPhysical.
	public void displayPhysical() 
	{
		displayPhysical((FHsdTreeNode<E>)(mRoot), 0);
	}
	
	
	
	
	
	
	//Version 2 of displayPhysical.
	//Same like the display function that the instructor gave. It takes two parameter.
	//However, if the node is deleted, it will print out " (D)" instead of nothing.
	public void displayPhysical(FHsdTreeNode<E> treeNode, int level) 
	{
		String indent;

		// stop runaway indentation/recursion
		if  (level > (int)blankString.length() - 1)
		{
			System.out.println( blankString + " ... " );
			return;
		}
      
		if (treeNode == null)
			return;

		indent = blankString.substring(0, level);
		
		if(!treeNode.deleted) 
		{
			System.out.println( indent + treeNode.getData() );
			System.out.println(" (D)");
			display(treeNode.getFirstChild(), level + 1);
			display(treeNode.getSib(), level);
		}
		else
		if (level > 0 )
		{
			display( treeNode.getSib(), level );
		}
	}

	
	
	
	
	
	//This boolean function check whether the sizePhysical is smaller than size.
    //It will remove the nodes if the size is bigger than sizePhysical.
    //However, it will return true if the sizePhysical is bigger than size.
	public boolean collectGarbage() 
	{
		if(size()==0) 
		{
			return false;
		}
		
		if(sizePhysical()>size()) 
		{
			collectGarbage((FHsdTreeNode<E>)(mRoot));
		}
		
		return sizePhysical()>size();
	}
	
	
	
	
	
	
    //If the treeNode equals to null then it will return to false.
	//Then it deletes the deleted node. Then continues to the firstChild and siblings.
	public boolean collectGarbage(FHsdTreeNode<E> treeNode) 
	{
		if(treeNode==null)
			return false;
		boolean test = false;
		
		if (treeNode.deleted == true) 
		{
			removeNode(treeNode);
			test = true;
		}
		else
		{
			test |= collectGarbage(treeNode.getSib()) || 
					collectGarbage(treeNode.getFirstChild());
		}
		return test;
	}
}
