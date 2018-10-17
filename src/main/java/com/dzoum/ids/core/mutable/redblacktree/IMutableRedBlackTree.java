package com.dzoum.ids.core.mutable.redblacktree;

public interface IMutableRedBlackTree {

	// public IRedBlackTreeNode insert(IRedBlackTreeNode node, int value);
	public void insert(int value);
	public IMutableRedBlackTreeNode remove();
	public IMutableRedBlackTreeNode findNodeSuccessor(IMutableRedBlackTreeNode node);
	
	public void leftRotate(IMutableRedBlackTreeNode node);
	public void rightRotate(IMutableRedBlackTreeNode node);
	
	public String toStringWidthOrder();
	public String toStringPreOrder();
	
	public void printWidthOrder();
	public void printPreOrder();
	
}
