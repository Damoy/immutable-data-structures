package com.dzoum.ids.core.mutable.redblacktree;

public interface IMutableRedBlackTreeNode {

	public int getValue();
	public byte getColor();
	public boolean isRightChild();
	public boolean isLeftChild();
	public boolean isRed();
	public boolean isBlack();
	public boolean hasRedChild();
	public IMutableRedBlackTreeNode getParent();
	public IMutableRedBlackTreeNode getLeftChild();
	public IMutableRedBlackTreeNode getRightChild();
	public IMutableRedBlackTreeNode getBrother();
	public IMutableRedBlackTreeNode getUncle();
	
	public void setValue(int value);
	public void setColor(byte color);
	public void setParent(IMutableRedBlackTreeNode parent);
	public void setLeftChild(IMutableRedBlackTreeNode leftChild);
	public void setRightChild(IMutableRedBlackTreeNode rightChild);
	public void swapColor(IMutableRedBlackTreeNode with);
	public void swapValue(IMutableRedBlackTreeNode with);
	public void moveDown(IMutableRedBlackTreeNode nodeParent);
	
}
