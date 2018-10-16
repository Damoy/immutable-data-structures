package com.dzoum.ids.app;

import com.dzoum.ids.core.mutable.avl.IMutableAVLBuilder;
import com.dzoum.ids.core.mutable.avl.MutableAVLBuilder;
import com.dzoum.ids.utils.Utils;

public class AppMutableAVL {

	public static void main(String[] args) {
		// Create the mutable AVL builder in order to build mutable AVLs
		IMutableAVLBuilder builder = new MutableAVLBuilder();
		
		Utils.println("Inserting...");
		// Performs insertions
		builder.insert(9, 5, 10, 0, 6, 11, -1, 1, 2);
		
		// Print a first time >> [WIDTH ORDER] <<
		builder.build().printWidthOrder();
		
		Utils.println("Removing 10...");
		// Remove first element
		builder.remove(10);
		
		// Print another time
		builder.build().printWidthOrder();
	}
	
}
