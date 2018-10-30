package com.dzoum.ids.core.redblacktree.mutable;

public interface IMutableRedBlackTree {

	public void insert(int value);
	
	/**
	 * Remove the roots.
	 */
	public IMutableRedBlackTreeNode remove();
	
	/**
	 * Remove given node.
	 */
	public void remove(IMutableRedBlackTreeNode node);
	
	/**
	 * Remove node of given value.
	 */
	public IMutableRedBlackTreeNode removeGivenValue(int value);
	
	/**
	 * Look for the node with value inquired one,
	 * if does not exist, return last tree node while traversing.
	 */
	public IMutableRedBlackTreeNode search(int value);
	
	/**
	 * Get the red-black tree's node that has the minimum value.
	 * 
	 * @param from starting search element
	 */
	public IMutableRedBlackTreeNode getMinNode(IMutableRedBlackTreeNode from);
	
	/**
	 * Get the red-black tree's root.
	 */
	public IMutableRedBlackTreeNode getRoot();
	
	/**
	 * Get the red-black tree minimum value.
	 */
	public int getMinValue(IMutableRedBlackTreeNode from);
	
	/**
	 * Is the red-black tree empty ?
	 */
	public boolean isEmpty();
	
	/**
	 * Set the red-black tree root.
	 */
	public void setRoot(IMutableRedBlackTreeNode root);
	
	public String toStringWidthOrder();
	public String toStringPreOrder();
	
	public void printColoredWidthOrder();
	public void printWidthOrder();
	public void printPreOrder();
	
}
