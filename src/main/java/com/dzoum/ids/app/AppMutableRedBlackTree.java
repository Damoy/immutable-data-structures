package com.dzoum.ids.app;

import com.dzoum.ids.core.redblacktree.mutable.IMutableRedBlackTree;
import com.dzoum.ids.core.redblacktree.mutable.MutableRedBlackTree;

public class AppMutableRedBlackTree {

	public static void main(String[] args) {
		IMutableRedBlackTree mrbt = new MutableRedBlackTree();

		// Inserting
		mrbt.insert(7);
		mrbt.insert(3);
		mrbt.insert(18);
		mrbt.insert(10);
		mrbt.insert(22);
		mrbt.insert(8);
		mrbt.insert(11);
		mrbt.insert(26);
		mrbt.insert(2);
		mrbt.insert(6);
		mrbt.insert(13);

		mrbt.printWidthOrder();
		mrbt.printColoredWidthOrder();
		
		// Deleting
		mrbt.removeGivenValue(18); 
		mrbt.removeGivenValue(11); 
		mrbt.removeGivenValue(3); 
		mrbt.removeGivenValue(10); 
		mrbt.removeGivenValue(22);
		
		mrbt.printWidthOrder();
		mrbt.printColoredWidthOrder();
	}

}