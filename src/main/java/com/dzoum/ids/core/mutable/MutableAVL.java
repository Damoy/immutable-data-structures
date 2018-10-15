package com.dzoum.ids.core.mutable;

import com.dzoum.ids.utils.Utils;

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
		StringBuilder sb = new StringBuilder();

		if (root != null) {
			sb.append(root.getKey());
			sb.append(" ");
			preOrderPrint(sb, root.getLeftChild());
			preOrderPrint(sb, root.getRightChild());
		}

		Utils.println(sb.toString());
	}
	
	@Override
	public void printWidthPath() {
		StringBuilder sb = new StringBuilder();
		
		IAVLNode oldFather = root;
		IAVLNode father = root;
		IAVLNode leftChild = father.getLeftChild();
		IAVLNode rightChild = father.getRightChild();
		
		while(father != null){
			sb.append(father.getKey());
			sb.append(" ");
			
			if(leftChild != null){
				sb.append(leftChild.getKey());
				sb.append(" ");
			}
			
			if(rightChild != null){
				sb.append(rightChild.getKey());
				sb.append(" ");
			}

			// TODO
			// if(oldFather == )
			if(leftChild != null){
				father = leftChild;
			}
			
			leftChild = father.getLeftChild();
			rightChild = father.getRightChild();
		}

		Utils.println(sb.toString());
	}

	private void preOrderPrint(StringBuilder sb, IAVLNode node) {
		if (node != null) {
			sb.append(node.getKey());
			sb.append(" ");
			preOrderPrint(sb, node.getLeftChild());
			preOrderPrint(sb, node.getRightChild());
		}
	}

	@Override
	public void setRoot(IAVLNode root) {
		this.root = root;
	}

	@Override
	public IAVLNode remove(IAVLNode node, int key) {
		if (node == null)
			return node;

		// If the key to be deleted is smaller than
		// the root's key then it is located in the left subtree
		if (key < node.getKey())
			node.setLeftChild(remove(node.getLeftChild(), key));

		// If the key to be deleted is greater than the
		// root's key, then it lies in right subtree
		else if (key > node.getKey())
			node.setRightChild(remove(node.getRightChild(), key));

		// if key is same as root's key, then this is the node
		// to be deleted
		else {
			// node with only one child or no child
			if ((node.getLeftChild() == null) || (node.getRightChild() == null)) {
				IAVLNode tmp = null;
				if (tmp == node.getLeftChild())
					tmp = node.getRightChild();
				else
					tmp = node.getLeftChild();

				// No child case
				if (tmp == null) {
					tmp = node;
					node = null;
				} else {
					node = tmp;
				}
			} else {
				// node with two children: Get the inorder
				// successor (smallest in the right subtree)
				IAVLNode tmp = getMinNode(node.getRightChild());

				// Copy the inorder successor's data to this node
				node.setKey(tmp.getKey());

				// Delete the inorder successor
				node.setRightChild(remove(node.getRightChild(), tmp.getKey()));
			}
		}

		// If the tree had only one node then return
		if (node == null)
			return node;

		// STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
		node.setHeight(max(height(node.getLeftChild()), height(node.getRightChild())) + 1);

		// STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether
		// this node became unbalanced)
		int balance = getBalance(node);

		// If this node becomes unbalanced, then there are 4 cases
		// Left Left Case
		if (balance > 1 && getBalance(node.getLeftChild()) >= 0)
			return rightRotate(node);

		// Left Right Case
		if (balance > 1 && getBalance(node.getLeftChild()) < 0) {
			node.setLeftChild(leftRotate(node.getLeftChild()));
			return rightRotate(node);
		}

		// Right Right Case
		if (balance < -1 && getBalance(node.getRightChild()) <= 0)
			return leftRotate(node);

		// Right Left Case
		if (balance < -1 && getBalance(node.getRightChild()) > 0) {
			node.setRightChild(rightRotate(node.getRightChild()));
			return leftRotate(node);
		}

		return node;
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public IAVLNode getMinNode(IAVLNode from) {
		IAVLNode min = from;

		while (min.getLeftChild() != null)
			min = min.getLeftChild();

		return min;
	}

	@Override
	public int getMinValue(IAVLNode from) {
		return getMinNode(from).getKey();
	}

	@Override
	public IAVLNode getRoot() {
		return root;
	}

}
