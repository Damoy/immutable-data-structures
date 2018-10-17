package com.dzoum.ids.core.mutable.redblacktree;

public interface IRedBlackTreeNode {

	public int getValue();
	public byte getColor();
	public boolean isRightChild();
	public boolean isLeftChild();
	public boolean isRed();
	public boolean isBlack();
	public boolean hasRedChild();
	public IRedBlackTreeNode getParent();
	public IRedBlackTreeNode getLeftChild();
	public IRedBlackTreeNode getRightChild();
	public IRedBlackTreeNode getBrother();
	public IRedBlackTreeNode getUncle();
	
	public void setValue(int value);
	public void setColor(byte color);
	public void setParent(IRedBlackTreeNode parent);
	public void setLeftChild(IRedBlackTreeNode leftChild);
	public void setRightChild(IRedBlackTreeNode rightChild);
	public void moveDown(IRedBlackTreeNode nodeParent);
	
}
