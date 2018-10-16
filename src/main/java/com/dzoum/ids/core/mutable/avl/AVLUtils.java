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
	
}
