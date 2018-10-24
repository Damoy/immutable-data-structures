package com.dzoum.ids.app;

import com.dzoum.ids.core.redblacktree.mutable.IMutableRedBlackTree;
import com.dzoum.ids.core.redblacktree.mutable.MutableRedBlackTree;

public class AppMutableRedBlackTree {

	public static void main(String[] args) {
		IMutableRedBlackTree mrbt = new MutableRedBlackTree(5);

		mrbt.insert(6);
		mrbt.insert(8);
		mrbt.insert(19);
		mrbt.insert(1);

		mrbt.printWidthOrder();
		mrbt.printPreOrder();
	}

}
