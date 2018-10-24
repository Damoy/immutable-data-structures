package com.dzoum.ids.core.redblacktree.mutable;

public interface IMutableRedBlackTree {

	// public IRedBlackTreeNode insert(IRedBlackTreeNode node, int value);
	public void insert(int value);
	public IMutableRedBlackTreeNode remove();
	
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
	
	public void printWidthOrder();
	public void printPreOrder();
	
}
