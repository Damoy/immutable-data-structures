package com.dzoum.ids.core.mutable;

public interface IMutableAVL {

	public IAVLNode insert(IAVLNode node, int key);
	public IAVLNode remove();
	public void setRoot(IAVLNode root);
	public void preOrderPrint(); 
	
}