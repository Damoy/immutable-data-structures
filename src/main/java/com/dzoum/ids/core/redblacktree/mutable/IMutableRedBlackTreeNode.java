package com.dzoum.ids.core.redblacktree.mutable;

import com.dzoum.ids.core.commons.INode;

public interface IMutableRedBlackTreeNode extends INode {

	public byte getColor();
	public boolean isRightChild();
	public boolean isLeftChild();
	public boolean isRed();
	public boolean isBlack();
	public boolean hasRedChild();
	public IMutableRedBlackTreeNode getParent();
	public IMutableRedBlackTreeNode getBrother();
	public IMutableRedBlackTreeNode getUncle();
	
	public void setColor(byte color);
	public void setParent(IMutableRedBlackTreeNode parent);
	public void swapColor(IMutableRedBlackTreeNode with);
	public void swapValue(IMutableRedBlackTreeNode with);
	public void moveDown(IMutableRedBlackTreeNode nodeParent);
	
	/**
	 * Get the node left child
	 */
	public IMutableRedBlackTreeNode getLeftChild();
	
	/**
	 * Get the node right child.
	 * @return
	 */
	public IMutableRedBlackTreeNode getRightChild();
	
	/**
	 * Set the node left child. 
	 */
	public void setLeftChild(IMutableRedBlackTreeNode leftChild);
	
	/**
	 * Set the node right child.
	 */
	public void setRightChild(IMutableRedBlackTreeNode rightChild);
	
	// Tools to ease red black tree validity check.
	public int getBlackCount();
	public void setBlackCount(int v);
}
