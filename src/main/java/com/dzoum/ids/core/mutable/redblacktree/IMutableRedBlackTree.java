package com.dzoum.ids.core.mutable.redblacktree;

public interface IMutableRedBlackTree {

	public IRedBlackTreeNode insert(IRedBlackTreeNode node, int value);
	public IRedBlackTreeNode remove();
	public void leftRotate(IRedBlackTreeNode node);
	public void rightRotate(IRedBlackTreeNode node);
	
}
