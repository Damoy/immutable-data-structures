package com.dzoum.ids.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import com.dzoum.ids.core.avl.mutable.IMutableAVL;
import com.dzoum.ids.core.avl.mutable.IMutableAVLNode;
import com.dzoum.ids.core.commons.INode;
import com.dzoum.ids.core.heap.mutable.array.IMutableArrayHeap;
import com.dzoum.ids.core.heap.mutable.node.IHeapNode;
import com.dzoum.ids.core.heap.mutable.node.IMutableNodeHeap;
import com.dzoum.ids.core.redblacktree.mutable.IMutableRedBlackTree;
import com.dzoum.ids.core.redblacktree.mutable.IMutableRedBlackTreeNode;
import com.dzoum.ids.core.redblacktree.mutable.RedBlackTreeException;

public final class Utils {

	private static Random SEED = new Random();

	private Utils() {
	}
	
	public static void randomize() {
		SEED = new Random();
	}
	
	public static void setRandomSeed(long seed) {
		SEED = new Random(seed);
	}

	public static INode getMutableAVLMinNode(IMutableAVL mavl) {
		return mavl.getMinNode(mavl.getRoot());
	}

	public static int getMutableAVLMinNodeValue(IMutableAVL mavl) {
		return mavl.getMinValue(mavl.getRoot());
	}

	public static int getNodeHeight(INode node) {
		if (node == null)
			return 0;

		return node.getHeight();
	}

	public static boolean isMutableAVL(IMutableAVL mavl, int minValue, int maxValue) {
		boolean avlok = isMutableAVLUtil(mavl.getRoot(), minValue, maxValue);
		boolean balanced = isMutableAVLBalanced(mavl);
		return avlok && balanced;
	}

	private static boolean isMutableAVLUtil(IMutableAVLNode node, int min, int max) {
		if (node == null)
			return true;

		if (node.getValue() < min || node.getValue() > max)
			return false;

		return (isMutableAVLUtil(node.getLeftChild(), min, node.getValue() - 1)
				&& isMutableAVLUtil(node.getRightChild(), node.getValue() + 1, max));
	}

	public static boolean isMutableAVLBalanced(IMutableAVL mavl) {
		return isMutableAVLNodeBalanced(mavl.getRoot());
	}

	private static boolean isMutableAVLNodeBalanced(IMutableAVLNode node) {
		if (node == null)
			return true;

		return (Math.abs(getNodeHeight(node.getLeftChild()) - getNodeHeight(node.getRightChild())) <= 1
				&& isMutableAVLNodeBalanced(node.getLeftChild()) && isMutableAVLNodeBalanced(node.getRightChild()));
	}

	public static IMutableRedBlackTreeNode getMutableRedBlackTreeMinNode(IMutableRedBlackTree mrbt) {
		return mrbt.getMinNode(mrbt.getRoot());
	}

	public static int getMutableRedBlackTreeMinNodeValue(IMutableRedBlackTree mrbt) {
		return mrbt.getMinValue(mrbt.getRoot());
	}

	public static boolean isRedBlackTree(IMutableRedBlackTree tree) throws RedBlackTreeException {
		if(tree.getRoot() == null) return true;
		Set<Integer> check = new HashSet<>();
		countBlack(check, tree.getRoot(), null);
		boolean b = check.size() == 1;
		
		if(!b) {
			Iterator<Integer> it = check.iterator();
			Utils.print("Set: ");
			while(it.hasNext()) {
				Utils.print(it.next() + " ");
			}
			Utils.println("");
		}
		
		return b;
	}
	
	private static void countBlack(Set<Integer> set, IMutableRedBlackTreeNode node, IMutableRedBlackTreeNode parent) throws RedBlackTreeException {
		if(node == null) {
			if(parent == null) {
				set.add(0);
			} else {
				set.add(parent.getBlackCount());
			}
			return;
		}
		
		if(node.getParent() != null && node.getParent().isRed() && node.isRed()) {
			throw new RedBlackTreeException("Red red breach !");
		}
		
		int newBlackCount = 0;
		
		if(node.getParent() != null) {
			newBlackCount = node.getParent().getBlackCount();
			
			if(node.isLeftChild()) {
				if(node.getValue() > node.getParent().getValue())
					throw new RedBlackTreeException("Left child superior to parent !");
			} else {
				if(node.getValue() < node.getParent().getValue())
					throw new RedBlackTreeException("Right child inferior to parent !");
			}
		}
		
		if(node.isBlack()) {
			++newBlackCount;
		} 
		
		node.setBlackCount(newBlackCount);
		
		countBlack(set, node.getLeftChild(), node);
		countBlack(set, node.getRightChild(), node);
	}
	
	// Function to check binary tree is a Heap or Not.
	public boolean isHeap(IMutableNodeHeap heap) {
		if (heap.getRoot() == null)
			return true;

		return isHeapComplete(heap.getRoot(), 0,countHeapNodes(heap.getRoot()))
				&& isHeapUtil(heap.getRoot());
	}
	
	// This function checks if the binary tree is complete or not
	private boolean isHeapComplete(IHeapNode root, int index, int nodesNb) {
		// An empty tree is complete
		if (root == null)
			return true;

		// If index assigned to current node is more than
		// number of nodes in tree, then tree is not complete
		if (index >= nodesNb)
			return false;

		// Recur for left and right subtrees
		return isHeapComplete(root.getLeftChild(), (index << 1) + 1, nodesNb)
				&& isHeapComplete(root.getRightChild(), (index << 1) + 2, nodesNb);
	}
	
	// Checks the heap property in the tree.
	private boolean isHeapUtil(IHeapNode root) {
		// Base case : single node satisfies property
		if (root.getLeftChild() == null && root.getRightChild() == null)
			return true;

		// node will be in second last level
		if (root.getRightChild() == null) {
			// check heap property at Node
			// No recursive call , because no need to check last level
			return root.getValue() >= root.getLeftChild().getValue();
		} else {
			// Check heap property at Node and
			// Recursive check heap property at left and right subtree
			if (root.getValue() >= root.getLeftChild().getValue() && root.getValue() >= root.getRightChild().getValue())
				return isHeapUtil(root.getLeftChild()) && isHeapUtil(root.getRightChild());
			else
				return false;
		}
	}
	
	private int countHeapNodes(IHeapNode root) {
		if (root == null)
			return 0;
		
		return (1 + countHeapNodes(root.getLeftChild()) + countHeapNodes(root.getRightChild()));
	}
	
	private int countHeapNodes(IMutableArrayHeap heap, int index) {
		if (index >= heap.getCapacity())
			return 0;
		
		return (1 + countHeapNodes(heap, (index << 1)) + countHeapNodes(heap, (index << 1) + 1));
	}

	public static int max(int n1, int n2) {
		return n1 > n2 ? n1 : n2;
	}

	/**
	 * Assert a condition.
	 * 
	 * @param cond
	 *            the condition to check
	 * @param err
	 *            message to throw
	 * @param eclazz
	 *            exception class to use
	 */
	public static void assertTrue(boolean cond, String err, Class<? extends Exception> eclazz) {
		if (!cond) {
			try {
				Constructor<? extends Exception> ce = eclazz.getConstructor(String.class);
				ce.newInstance(err);
			} catch (NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}

	public static <T> void println(T o) {
		System.out.println(o.toString());
	}

	public static <T> void print(T o) {
		System.out.print(o.toString());
	}

	/**
	 * Get random integer in range [min,max].
	 */
	public static int irand(int min, int max) {
		return SEED.nextInt((max - min) + 1) + min;
	}

	/**
	 * Get log2 of inquired value.
	 */
	public static double log2(double value) {
		return Math.log10(value) / Math.log10(2);
	}

	/**
	 * Write some content to a file. Overrides the file if it exists.
	 * 
	 * @param title
	 *            the file title
	 * @param content
	 *            the text to write
	 */
	public static void writeToFile(String title, String extension, String content) {
		title = filterTitle(title);
		title = "./res/bench/" + title;
		File file = new File(title);
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(content);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static int compareInts(int v1, int v2) {
		return v1 > v2 ? 1 : v1 == v2 ? 0 : -1;
	}

	private static String filterTitle(String title) {
		return title.replaceAll("[ *$^!;:,=)('\"&]", "_");
	}

	public static final class Pair<A, B> {
		public final A first;
		public final B second;

		public Pair(A first, B second) {
			this.first = first;
			this.second = second;
		}
	}

}
