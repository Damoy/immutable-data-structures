package com.dzoum.ids.core.mutable;

public interface IAVLNode {
	
	public int getKey();
	public int getHeight();

	public IAVLNode getLeftChild();
	public IAVLNode getRightChild();

	public void setKey(int key);
	public void setHeight(int height);
	public void setLeftChild(IAVLNode leftChild);
	public void setRightChild(IAVLNode rightChild);
	
}
