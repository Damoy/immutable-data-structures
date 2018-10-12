package com.dzoum.ids.core.mutable;
//package com.lama.ids.core.mutable;
//
//import javax.xml.soap.Node;
//
//public class MutableAVL implements IMutableAVL {
//
//	private IAVLNode root;
//
//	public MutableAVL() {
//		root = null;
//	}
//
//	@Override
//	public IAVLNode insert(IAVLNode node, int key) {
//		if (node == null)
//			return (new AVLNode(key));
//
//		if (key < node.getKey())
//			node.setLeftChild(insert(node.getLeftChild(), key));
//		else if (key > node.getKey())
//			node.setRightChild(insert(node.getRightChild(), key));
//		else
//			return node; // Duplicate keys not allowed
//
//		// Update height of this ancestor node
//		node.setHeight(1);
//		node.setHeight(1 + max(height(node.getLeftChild()), height(node.getRightChild())));
//
//		int balance = getBalance(node);
//
//		// If this node becomes unbalanced, then there
//		// are 4 cases Left Left Case
//		if (balance > 1 && key < node.left.key)
//			return rightRotate(node);
//
//		// Right Right Case
//		if (balance < -1 && key > node.right.key)
//			return leftRotate(node);
//
//		// Left Right Case
//		if (balance > 1 && key > node.left.key) {
//			node.left = leftRotate(node.left);
//			return rightRotate(node);
//		}
//
//		// Right Left Case
//		if (balance < -1 && key < node.right.key) {
//			node.right = rightRotate(node.right);
//			return leftRotate(node);
//		}
//
//		return node;
//	}
//
//	private int height(IAVLNode node) {
//		if (node == null)
//			return 0;
//
//		return node.getHeight();
//	}
//
//	@Override
//	public IAVLNode remove() {
//		return null;
//	}
//
//}
