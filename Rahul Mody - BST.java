/*
 *
 *  BinarySearchTree.java
 *  
 */

import java.util.*;
import java.util.Iterator;


public class BinarySearchTree<E extends Comparable<? super E>> extends BinaryTree<E> 
{
	// comparator used to compare different data type that hold similar values, it eases the need to constantly cast and potentially run in to type mismatch problem.
	private Comparator<E> temp ; 
	
	//compares the two given variables using the compareTo method and returns the result
	private int compare(E a , E b)
	{
		if(temp == null)
		{
			return a.compareTo(b);
		}
		
		else
		{
			return temp.compare(a,b);
		}
	}
	
	// Insert method with parameter from the user perspective. They only wish to enter a data point regardless of a structure.
	public void insert (E data)
	{
		root = insert(root, data);
	}
	

	// overloaded insert that returns the root of the tree after alterations. 
	//The method runs recursively to place the node at the right place based on the rules for BST.
	private Node<E> insert(Node<E> n , E data)
	{
		if(n == null)
		{
			return new Node<E>(data);
		}
		
		if(compare(data, n.data) == 0)
		{
			return n;
		}
		
		if(compare(data, n.data) < 0)
		{
			n.left = insert(n.left, data);
		}
		
		else
		{
			n.right = insert(n.right, data);
		}
		
		return n;
	}
	
	// Iterator used as the standard way to iterate the tree without the use of loops
	public Iterator<E> iterator()
	{
		Vector<E> v = new Vector<E>();
		traverse(v, root);
		return v.iterator();
	}
	
	// Remove method with parameter from the user perspective. They have access only the data
	public void remove (E data)
	{
		root = remove(root, data);
		
	}
	
	//Uses the root node and the data point to be removed, traverses the tree and returns the removed node.
	private Node<E> remove(Node<E> r, E data)
	{
		if(r == null) ;
		
		else
		{
			if(compare(data, r.data) < 0)
			{
				r.left = remove (r.left, data);
			}
			
			else
			{
				if(compare(data, r.data) > 0)
				{
					r.right = remove(r.right, data);
				}
				
				else
				{
					if(r.left == null)
					{
						return r.right;
					}
					else
					{
						if(r.right == null)
						{
							return r.left;
						}
						else
						{
							r.left = remove(r.left, r.data);
						}
					}
				}
			}
		}
		
		return r;
	}
	

   // Search method with parameter from the user perspective.
   public boolean search (E data)
	{
		return search(root, data);
	}
	
	//Recursively searches the tree for a given data value in the tree. Returns true if given data point it found.
	public boolean search(Node<E> s , E data)
	{
		if(s == null)
		{
			return false;
		}
		
		else
		{
			if(compare(data, s.data) == 0)
			{
				return true;
			}
			
			else
			{
				if(compare(data, s.data) < 0)
				{
					return search(s.left, data);
				}
				
				else
				{
					return search(s.right, data);
				}
			}
		}
	}
	
	// Given Traverse method
	private void traverse(Vector<E> v, Node<E> p)
	{
		if(p != null)
		{
			traverse(v, p.left);
			v.add(p.data);
			traverse(v, p.right);
		}
	}
		
}