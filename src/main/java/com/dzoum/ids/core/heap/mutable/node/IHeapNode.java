package com.dzoum.ids.core.heap.mutable.node;

import com.dzoum.ids.core.commons.INode;

public interface IHeapNode extends INode {

	/**
	 * Get the node left child
	 */
	public IHeapNode getLeftChild();
	
	/**
	 * Get the node right child.
	 * @return
	 */
	public IHeapNode getRightChild();
	
	/**
	 * Set the node left child. 
	 */
	public void setLeftChild(IHeapNode leftChild);
	
	/**
	 * Set the node right child.
	 */
	public void setRightChild(IHeapNode rightChild);
	
}
