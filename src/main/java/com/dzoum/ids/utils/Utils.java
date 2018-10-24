package com.dzoum.ids.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.dzoum.ids.core.avl.mutable.IMutableAVL;
import com.dzoum.ids.core.avl.mutable.IMutableAVLNode;
import com.dzoum.ids.core.commons.INode;
import com.dzoum.ids.core.redblacktree.mutable.IMutableRedBlackTree;
import com.dzoum.ids.core.redblacktree.mutable.IMutableRedBlackTreeNode;
import com.dzoum.ids.core.redblacktree.mutable.MutableRedBlackTreeNode;
import com.dzoum.ids.core.redblacktree.mutable.RedBlackTreeException;

public final class Utils {

	private final static Random SEED = new Random();
	private final static Map<String, Integer> MEMORY = new HashMap<>();

	private Utils() {
	}

	public static INode getMAVLMinNode(IMutableAVL mavl) {
		return mavl.getMinNode(mavl.getRoot());
	}

	public static int getMAVLMinNodeValue(IMutableAVL mavl) {
		return mavl.getMinValue(mavl.getRoot());
	}

	public static int getNodeHeight(INode node) {
		if (node == null)
			return 0;

		return node.getHeight();
	}

	public static boolean isValidMutableAVL(IMutableAVL mavl, int minValue, int maxValue) {
		return isMAVLUtil(mavl.getRoot(), minValue, maxValue) && isMAVLBalanced(mavl);
	}

	private static boolean isMAVLUtil(IMutableAVLNode node, int min, int max) {
		if (node == null)
			return true;

		if (node.getValue() < min || node.getValue() > max)
			return false;

		return (isMAVLUtil(node.getLeftChild(), min, node.getValue() - 1)
				&& isMAVLUtil(node.getRightChild(), node.getValue() + 1, max));
	}

	public static boolean isMAVLBalanced(IMutableAVL mavl) {
		return isMAVLNodeBalanced(mavl.getRoot());
	}

	public static boolean isMAVLNodeBalanced(IMutableAVLNode node) {
		if (node == null)
			return true;

		return (Math.abs(getNodeHeight(node.getLeftChild()) - getNodeHeight(node.getRightChild())) <= 1
				&& isMAVLNodeBalanced(node.getLeftChild()) && isMAVLNodeBalanced(node.getRightChild()));
	}

	public static IMutableRedBlackTreeNode getMRBTMinNode(IMutableRedBlackTree mrbt) {
		return mrbt.getMinNode(mrbt.getRoot());
	}

	public static int getMRBTMinNodeValue(IMutableRedBlackTree mrbt) {
		return mrbt.getMinValue(mrbt.getRoot());
	}

	public static boolean isValidMRBT(IMutableRedBlackTree mrbt) throws RedBlackTreeException {
		boolean rootIsBlack = mrbt.getRoot().isBlack();
		if(!rootIsBlack) return false;
		
		int left = getBlackPathsCount(mrbt.getRoot().getLeftChild());
		int right = getBlackPathsCount(mrbt.getRoot().getRightChild());
		return left == right;
	}
	
	private static int getBlackPathsCount(IMutableRedBlackTreeNode node) throws RedBlackTreeException {
		if(node == null) return 0;
		
		// check red red breach
		if(node.getParent() != null && node.getParent().isRed() && node.isRed()) {
			throw new RedBlackTreeException("Red Red breach !");
		}
		
		if(node.getParent() != null) {
			if(node.isLeftChild()) {
				if(node.getValue() > node.getParent().getValue())
					throw new RedBlackTreeException("Left child superior to parent !");
			} else {
				if(node.getValue() < node.getParent().getValue())
					throw new RedBlackTreeException("Right child inferior to parent !");
			}
		}
		
		int currentBlackCount = node.isBlack() ? 1 : 0;
		return currentBlackCount + getBlackPathsCount(node.getLeftChild())
			+ getBlackPathsCount(node.getRightChild());
	}
		
// 		boolean leftChildNull = node.getLeftChild() == null;
// 		boolean rightChildNull = node.getLeftChild() == null;
//		if(leftChildNull) {
//			if(rightChildNull) {
//				return expectedColor == MutableRedBlackTreeNode.BLACK ? 1 : 0;
//			} else {
//				int currentBlackCount = expectedColor == MutableRedBlackTreeNode.BLACK ? 1 : 0;
//				return currentBlackCount + getBlackPathsCount(node.getLeftChild(), nextLayerColor)
//					+ getBlackPathsCount(node.getRightChild(), nextLayerColor);
//			}

//	public static boolean isMRBTBalanced(IMutableRedBlackTree mrbt) {
//		return isMRBTNodeBalanced(mrbt.getRoot());
//	}
//
//	public static boolean isMRBTNodeBalanced(IMutableRedBlackTreeNode node) {
//		if (node == null)
//			return true;
//
//		return (Math.abs(getNodeHeight(node.getLeftChild()) - getNodeHeight(node.getRightChild())) <= 1
//				&& isMRBTNodeBalanced(node.getLeftChild()) && isMRBTNodeBalanced(node.getRightChild()));
//	}

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
	public static void writeToFile(String title, String content) {
		title = filterTitle(title);
		String ftitle = getOutputFilePath();

		if (MEMORY.containsKey(title)) {
			int lastId = MEMORY.get(title);
			ftitle += title + (++lastId);
			MEMORY.put(ftitle, lastId);
		} else {
			MEMORY.put(title, 0);
			ftitle += title;
		}

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(ftitle)));
			bw.write(content);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static final class Pair<A, B> {
		public final A first;
		public final B second;

		public Pair(A first, B second) {
			this.first = first;
			this.second = second;
		}
	}

	public static int compareInts(int v1, int v2) {
		return v1 > v2 ? 1 : v1 == v2 ? 0 : -1;
	}

	private static String filterTitle(String title) {
		return title.replaceAll("[ *$^!;:,=)('\"&]", "_");
	}

	private static String getOutputFilePath() {
		return "./src/main/resources/output/";
	}

}
