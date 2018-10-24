package com.dzoum.ids.core.avl.mutable;

/**
 * A mutable AVL.
 */
public interface IMutableAVL {
	
	/**
	 * Insert a new element starting search from the inquired node.
	 * 
	 * @param node from
	 * @param value element value
	 * @return modified inquired node or new node if input was null
	 */
	public IMutableAVLNode insert(IMutableAVLNode node, int value);
	
	/**
	 * Remove an element starting search from the inquired node.
	 * 
	 * @param node from
	 * @param value element value
	 * @return modified inquired node or null if input was null
	 */
	public IMutableAVLNode remove(IMutableAVLNode from, int value);
	
	/**
	 * Get the AVL's node that has the minimum value.
	 * 
	 * @param from starting search element
	 */
	public IMutableAVLNode getMinNode(IMutableAVLNode from);
	
	/**
	 * Get the AVL's root.
	 */
	public IMutableAVLNode getRoot();
	
	/**
	 * The the AVL minimum value.
	 */
	public int getMinValue(IMutableAVLNode from);
	
	/**
	 * Is the AVL empty ?
	 */
	public boolean isEmpty();
	
	/**
	 * Set the AVL root.
	 */
	public void setRoot(IMutableAVLNode root);
	
	/**
	 * Printing in pre-order.
	 */
	public void printPreOrder();
	
	/**
	 * Printing in width order.
	 */
	public void printWidthOrder();  
	
	/**
	 * Get width order representation
	 */
	public String toStringWidthOrder();
	
	/**
	 * Get pre-order representation
	 */
	public String toStringPreOrder();
	
}
