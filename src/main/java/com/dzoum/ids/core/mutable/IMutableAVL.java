package com.dzoum.ids.core.mutable;

public interface IMutableAVL {

	public IAVLNode insert(IAVLNode node, int key);
	public IAVLNode remove(IAVLNode from, int key);
	
	public IAVLNode getMinNode(IAVLNode from);
	public IAVLNode getRoot();
	public int getMinValue(IAVLNode from);
	public boolean isEmpty();
	
	public void setRoot(IAVLNode root);
	public void preOrderPrint();
	public void printWidthPath();  
	
}
