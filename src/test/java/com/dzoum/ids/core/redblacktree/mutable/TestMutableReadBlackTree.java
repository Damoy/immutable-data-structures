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
		assertEquals(getMutableRedBlackTreeMinNode(mrbt), new MutableRedBlackTreeNode(5, MutableRedBlackTreeNode.BLACK));
		assertEquals(getMutableRedBlackTreeMinNode(mrbt), mrbt.getRoot());
		assertEquals(getMutableRedBlackTreeMinNodeValue(mrbt), 5);
		assertTrue(isRedBlackTree(mrbt));
	}
	
	@Test
	public void testInsertions() throws RedBlackTreeException {
		int nb = 50;
		
		for(int count = 0; count < nb; ++count) {
			int insertionsTimes = irand((int) Math.pow(2, 8), (int) Math.pow(2, 12));
			int min = -irand((int) Math.pow(2, 8), (int) Math.pow(2, 12));
			int max = irand((int) Math.pow(2, 8), (int) Math.pow(2, 12));
			
			mrbt = new MutableRedBlackTree();
			
			for(int i = 0; i < insertionsTimes; ++i) {
				mrbt.insert(irand(min, max));
				assertTrue(isRedBlackTree(mrbt));
			}
		}
	}
	
	@Test
	public void testRemovals() throws RedBlackTreeException {
		int nb = 50;

		for(int count = 0; count < nb; ++count) {
			int insertionsTimes = irand((int) Math.pow(2, 8), (int) Math.pow(2, 12));
			int min = -irand((int) Math.pow(2, 8), (int) Math.pow(2, 12));
			int max = irand((int) Math.pow(2, 8), (int) Math.pow(2, 12));

			mrbt = new MutableRedBlackTree();
			
			// first, insert
			for(int i = 0; i < insertionsTimes; ++i) {
				mrbt.insert(irand(min, max));
			}
			
			mrbt = new MutableRedBlackTree();
			
			for(int i = 0; i < insertionsTimes; ++i) {
				mrbt.remove();
				assertTrue(isRedBlackTree(mrbt));
			}
		}
	}
	
	@Test
	public void testRemovalGivenValue() throws RedBlackTreeException {
		int nb = 50;

		for(int count = 0; count < nb; ++count) {
			int insertionsTimes = irand((int) Math.pow(2, 8), (int) Math.pow(2, 12));
			int min = -irand((int) Math.pow(2, 8), (int) Math.pow(2, 12));
			int max = irand((int) Math.pow(2, 8), (int) Math.pow(2, 12));

			mrbt = new MutableRedBlackTree();
			int[] data = new int[insertionsTimes];
			
			// first, insert
			for(int i = 0; i < insertionsTimes; ++i) {
				data[i] = irand(min, max);
				mrbt.insert(data[i]);
			}
			
			mrbt = new MutableRedBlackTree();
			
			for(int i = 0; i < insertionsTimes; ++i) {
				mrbt.removeGivenValue(data[irand(0, data.length - 1)]);
				assertTrue(isRedBlackTree(mrbt));
			}
		}
	}
	
	@Test
	public void testInsertionsRemovals() throws RedBlackTreeException {
		int nb = 50;

		for(int count = 0; count < nb; ++count) {
			int insertionsTimes = irand((int) Math.pow(2, 8), (int) Math.pow(2, 12));
			int min = -irand((int) Math.pow(2, 8), (int) Math.pow(2, 12));
			int max = irand((int) Math.pow(2, 8), (int) Math.pow(2, 12));

			mrbt = new MutableRedBlackTree();
			
			// insert 2 times, remove one time
			for(int i = 0; i < insertionsTimes; ++i) {
				mrbt.insert(irand(min, max));
				assertTrue(isRedBlackTree(mrbt));
				mrbt.insert(irand(min, max));
				assertTrue(isRedBlackTree(mrbt));
				mrbt.remove();
				assertTrue(isRedBlackTree(mrbt));
			}
		}
	}
}
