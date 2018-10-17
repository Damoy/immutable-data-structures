package com.dzoum.ids.core.mutable.avl;

/**
 * AVL-related utilities
 */
public final class AVLUtils {

	private AVLUtils() {}
	
	public static IMutableAVLNode getMAVLMinNode(IMutableAVL mavl) {
		return mavl.getMinNode(mavl.getRoot());
	}
	
	public static int getMAVLMinNodeValue(IMutableAVL mavl) {
		return mavl.getMinValue(mavl.getRoot());
	}
	
	public static int height(IMutableAVLNode node) {
		if (node == null)
			return 0;

		return node.getHeight();
	}
	
	public static boolean isValidMutableAVL(IMutableAVL mavl, int minValue, int maxValue) {
		return isMAVLUtil(mavl.getRoot(), minValue, maxValue);
	}

	private static boolean isMAVLUtil(IMutableAVLNode node, int min, int max) {
		if (node == null)
			return true && isBalanced(node);

		if (node.getValue() < min || node.getValue() > max)
			return false;

		return (isMAVLUtil(node.getLeftChild(), min, node.getValue() - 1)
				&& isMAVLUtil(node.getRightChild(), node.getValue() + 1, max));
	}
	
	public static boolean isBalanced(IMutableAVL mavl){
		return isBalanced(mavl.getRoot());
	}
	
	public static boolean isBalanced(IMutableAVLNode node){
		if(node == null) return true;
		
		int lh = height(node.getLeftChild());
		int rh = height(node.getRightChild());

		if (Math.abs(lh - rh) <= 1 && isBalanced(node.getLeftChild()) && isBalanced(node.getRightChild()))
			return true;

		return false;
	}
	
}
