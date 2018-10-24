package com.dzoum.ids.core.redblacktree.mutable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.dzoum.ids.core.redblacktree.mutable.IMutableRedBlackTree;
import com.dzoum.ids.core.redblacktree.mutable.MutableRedBlackTree;
import com.dzoum.ids.core.redblacktree.mutable.MutableRedBlackTreeNode;

import static com.dzoum.ids.utils.Utils.*;

@RunWith(JUnit4.class)
public class TestMutableReadBlackTree {

	private IMutableRedBlackTree mrbt;
	
	@Test
	public void testEmpty() throws RedBlackTreeException {
		mrbt = new MutableRedBlackTree(5);
		assertEquals(getMRBTMinNode(mrbt), new MutableRedBlackTreeNode(5, MutableRedBlackTreeNode.BLACK));
		assertEquals(getMRBTMinNode(mrbt), mrbt.getRoot());
		assertEquals(getMRBTMinNodeValue(mrbt), 5);
		assertTrue(isValidMRBT(mrbt));
	}
	
	@Test
	public void testInsertions() throws RedBlackTreeException {
		int nb = 50;
		
		for(int count = 0; count < nb; ++count) {
			int insertionsTimes = irand((int) Math.pow(2, 8), (int) Math.pow(2, 20));
			int min = -irand((int) Math.pow(2, 8), (int) Math.pow(2, 16));
			int max = irand((int) Math.pow(2, 8), (int) Math.pow(2, 16));
			
			mrbt = new MutableRedBlackTree(max >> 1);
			
			for(int i = 0; i < insertionsTimes; ++i) {
				mrbt.printWidthOrder();
				assertTrue(isValidMRBT(mrbt));
				mrbt.insert(irand(min, max));
			}
		}
	}
}
