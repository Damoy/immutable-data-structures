package com.dzoum.ids.core.mutable.avl;

/**
 * A mutable AVL's node. 
 */
public interface IMutableAVLNode {
	
	/**
	 * Get the node value
	 */
	public int getValue();
	
	/**
	 * Get the node height.
	 */
	public int getHeight();

	/**
	 * Get the node left child
	 */
	public IMutableAVLNode getLeftChild();
	
	/**
	 * Get the node right child.
	 * @return
	 */
	public IMutableAVLNode getRightChild();

	/**
	 * Set the node value.
	 */
	public void setValue(int value);
	
	/**
	 * Set the node height.
	 */
	public void setHeight(int height);
	
	/**
	 * Set the node left child. 
	 */
	public void setLeftChild(IMutableAVLNode leftChild);
	
	/**
	 * Set the node right child.
	 */
	public void setRightChild(IMutableAVLNode rightChild);
	
}
