package com.dzoum.ids.core.mutable;

// https://www.geeksforgeeks.org/avl-tree-set-1-insertion/
public class MutableAVL implements IMutableAVL {

	private IAVLNode root;

	public MutableAVL(IAVLNode root) {
		this.root = root;
	}

	@Override
	public IAVLNode insert(IAVLNode node, int key) {
		if (node == null)
			return (new AVLNode(key));

		if (key < node.getKey())
			node.setLeftChild(insert(node.getLeftChild(), key));
		else if (key > node.getKey())
			node.setRightChild(insert(node.getRightChild(), key));
		else
			return node; // Duplicate keys not allowed

		// Update height of this ancestor node
		node.setHeight(1 + max(height(node.getLeftChild()), height(node.getRightChild())));

		int balance = getBalance(node);

		// If this node becomes unbalanced, then there
		// are 4 cases Left Left Case
		if (balance > 1 && key < node.getLeftChild().getKey())
			return rightRotate(node);

		// Right Right Case
		if (balance < -1 && key > node.getRightChild().getKey())
			return leftRotate(node);

		// Left Right Case
		if (balance > 1 && key > node.getLeftChild().getKey()) {
			node.setLeftChild(leftRotate(node.getLeftChild()));
			return rightRotate(node);
		}

		// Right Left Case
		if (balance < -1 && key < node.getRightChild().getKey()) {
			node.setRightChild(rightRotate(node.getRightChild()));
			return leftRotate(node);
		}

		return node;
	}

	private int getBalance(IAVLNode node) {
		if (node == null)
			return 0;

		return height(node.getLeftChild()) - height(node.getRightChild());
	}

	private IAVLNode rightRotate(IAVLNode node) {
		IAVLNode left = node.getLeftChild();
		IAVLNode lRightChild = left.getRightChild();

		// Perform rotation
		left.setRightChild(node);
		node.setLeftChild(lRightChild);

		// Update heights
		node.setHeight(max(height(node.getLeftChild()), height(node.getRightChild())) + 1);
		left.setHeight(max(height(left.getLeftChild()), height(left.getRightChild())) + 1);

		// Return new root
		return left;
	}

	private IAVLNode leftRotate(IAVLNode node) {
		IAVLNode right = node.getRightChild();
		IAVLNode rightLeftChild = right.getLeftChild();

		// Perform rotation
		right.setLeftChild(node);
		node.setRightChild(rightLeftChild);

		// Update heights
		node.setHeight(max(height(node.getLeftChild()), height(node.getRightChild())) + 1);
		right.setHeight(max(height(right.getLeftChild()), height(right.getLeftChild())) + 1);

		// Return new root
		return right;
	}

	private int max(int n1, int n2) {
		return n1 > n2 ? n1 : n2;
	}

	private int height(IAVLNode node) {
		if (node == null)
			return 0;

		return node.getHeight();
	}

	@Override
	public void preOrderPrint() {
		if (root != null) {
			System.out.print(root.getKey() + " ");
			preOrderPrint(root.getLeftChild());
			preOrderPrint(root.getRightChild());
		}
		
	}
	
	private void preOrderPrint(IAVLNode node){
		if (node != null) {
			System.out.print(node.getKey() + " ");
			preOrderPrint(node.getLeftChild());
			preOrderPrint(node.getRightChild());
		}
	}
	
	@Override
	public void setRoot(IAVLNode root){
		this.root = root;
	}

	@Override
	public IAVLNode remove() {
		return null;
	}
	
//	public static IMutableAVL build(int rootKey, int... insertions){
//		IAVLNode root = new AVLNode(rootKey);
//		IMutableAVL mavl = new MutableAVL(root);
//		
//		for(int i = 0; i < insertions.length; ++i){
//			root = mavl.insert(root, insertions[i]);
//		}
//		
//		return mavl;
//	}

}
