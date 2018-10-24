package com.dzoum.ids.core.avl.mutable;

import com.dzoum.ids.core.commons.INode;

/**
 * A mutable AVL's node. 
 */
public interface IMutableAVLNode extends INode {

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
	 * Set the node left child. 
	 */
	public void setLeftChild(IMutableAVLNode leftChild);
	
	/**
	 * Set the node right child.
	 */
	public void setRightChild(IMutableAVLNode rightChild);
	
}
