package com.dzoum.ids.core.redblacktree.mutable;

import java.util.LinkedList;
import java.util.Queue;

import com.dzoum.ids.utils.Utils;

public class MutableRedBlackTree implements IMutableRedBlackTree {

	private IMutableRedBlackTreeNode root;
	
	public MutableRedBlackTree(int rootValue) {
		// root is always black
		root = new MutableRedBlackTreeNode(rootValue, MutableRedBlackTreeNode.BLACK);
	}

	@Override
	public void insert(int value) {
		IMutableRedBlackTreeNode newNode = new MutableRedBlackTreeNode(value, MutableRedBlackTreeNode.RED);
		
		if(root == null){
			// if root is null, just set root to new node
			newNode.setColor(MutableRedBlackTreeNode.BLACK);
			root = newNode;
		} else {
			IMutableRedBlackTreeNode searchNode = lookFor(value);
			
			// if the value already exists, stop
			if(searchNode.getValue() == value)
				return;
			
			// lookFor method returns the node if the value is not found 
			// relate the new node with the correct one
			newNode.setParent(searchNode);
			
			if(value < searchNode.getValue()){
				searchNode.setLeftChild(newNode);
			} else {
				searchNode.setRightChild(newNode);
			}
			
			// check for red red breach
			checkRedBreach(newNode);
		}
	}
	
	// look for the node with value inquired one
	// if does not exist, return last tree node while traversing
	private IMutableRedBlackTreeNode lookFor(int nodeValue){
		IMutableRedBlackTreeNode tmp = root;
		
		while(tmp != null){
			if(nodeValue < tmp.getValue()){
				if(tmp.getLeftChild() == null)
					break;
				else
					tmp = tmp.getLeftChild();
			} else if(nodeValue == tmp.getValue()){
				break;
			} else {
				if(tmp.getRightChild() == null)
					break;
				else 
					tmp = tmp.getRightChild();
			}
		}
		
		return tmp;
	}
	
	private void checkRedBreach(IMutableRedBlackTreeNode node){
		// if node is the root, set color to black and return
		if(node == root){
			node.setColor(MutableRedBlackTreeNode.BLACK);
			return;
		}
		
		// create parent, uncle and grand-parent
		IMutableRedBlackTreeNode parent = node.getParent();
		IMutableRedBlackTreeNode uncle = node.getUncle();
		IMutableRedBlackTreeNode grandParent = parent.getParent();
		
		// if the parent is not black
		if(parent.isRed()){
			if(uncle != null && uncle.isRed()){
				// have to perform coloring and recurse
				parent.setColor(MutableRedBlackTreeNode.BLACK);
				uncle.setColor(MutableRedBlackTreeNode.BLACK);
				grandParent.setColor(MutableRedBlackTreeNode.RED);
				checkRedBreach(grandParent);
			} else {
				// else perform LR, LL, RL, RR
				if(parent.isLeftChild()){
					if(node.isLeftChild()){
						parent.swapColor(grandParent);
					} else {
						leftRotate(parent);
						node.swapColor(grandParent);
					}
					
					// for left left and left right 
					rightRotate(grandParent);
				} else {
					if(node.isLeftChild()){
						rightRotate(parent);
						node.swapColor(grandParent);
					} else {
						parent.swapColor(grandParent);
					}
					
					// for right right and right left
					leftRotate(grandParent);
				}
			}
		}
	}

	@Override
	public IMutableRedBlackTreeNode remove() {
		// TODO Auto-generated method stub
		return null;
	}

	// left rotation given the node
	private void leftRotate(IMutableRedBlackTreeNode node) {
		// node's right child is the new parent
		IMutableRedBlackTreeNode newParent = node.getRightChild();
		
		// update the root if current node is root 
		if(node == root){
			root = newParent;
		}
		
		// move down node with the new parent
		node.moveDown(newParent);
		
		// relate node with the new parent's left child
		node.setRightChild(newParent.getLeftChild());
		
		// relate new parent's left child with node
		if(newParent.getLeftChild() != null)
			newParent.getLeftChild().setParent(node);

		// relate new parent with node
		newParent.setLeftChild(node);
	}

	private void rightRotate(IMutableRedBlackTreeNode node) {
		// new parent is node's left child
		IMutableRedBlackTreeNode newParent = node.getLeftChild();
		
		// if current node is the root; update the root
		if(node == root)
			root = newParent;
		
		// move down node with the new parent
		node.moveDown(newParent);
		
		// relate node with the new parent's right child
		node.setLeftChild(newParent.getRightChild());

		// relate node with the new parent's right child
		if(newParent.getRightChild() != null)
			newParent.getRightChild().setParent(node);

		// relate new parent with node
		newParent.setRightChild(node);
	}

	// Finds node that don't have a left child in the given node's subtree
	private IMutableRedBlackTreeNode findNodeSuccessor(IMutableRedBlackTreeNode node) {
		IMutableRedBlackTreeNode successor = node;
		
		while(successor.getLeftChild() != null)
			successor = successor.getLeftChild();
		
		return successor;
	}

	@Override
	public String toStringWidthOrder() {
		if(root == null) return "";
		StringBuilder sb = new StringBuilder();

		Queue<IMutableRedBlackTreeNode> queue = new LinkedList<>();
		IMutableRedBlackTreeNode current = null;
		queue.add(root);
		
		while(!queue.isEmpty()){
			current = queue.poll();
			sb.append(current.toString());
			sb.append(" ");
			
			if(current.getLeftChild() != null)
				queue.add(current.getLeftChild());
			
			if(current.getRightChild() != null)
				queue.add(current.getRightChild());
		}

		return sb.toString();
	}

	@Override
	public String toStringPreOrder() {
		if(root == null) return "";
		StringBuilder sb = new StringBuilder();
		toStringPreOrder(sb, root);
		return sb.toString();
	}
	
	private void toStringPreOrder(StringBuilder sb, IMutableRedBlackTreeNode node){
		if(node == null) return;
		sb.append(node.toString());
		sb.append(" ");
		toStringPreOrder(sb, node.getLeftChild());
		toStringPreOrder(sb, node.getRightChild());
	}

	@Override
	public void printWidthOrder() {
		Utils.println(toStringWidthOrder());
	}

	@Override
	public void printPreOrder() {
		Utils.println(toStringPreOrder());
	}

	@Override
	public IMutableRedBlackTreeNode getMinNode(IMutableRedBlackTreeNode from) {
		if(from == null) return from;
		
		IMutableRedBlackTreeNode min = from;

		while (min.getLeftChild() != null)
			min = min.getLeftChild();

		return min;
	}

	@Override
	public IMutableRedBlackTreeNode getRoot() {
		return root;
	}

	@Override
	public int getMinValue(IMutableRedBlackTreeNode from) {
		IMutableRedBlackTreeNode node = getMinNode(from);
		if(node == null) throw new IllegalStateException("No minimum node found !");
		return node.getValue();
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public void setRoot(IMutableRedBlackTreeNode root) {
		this.root = root;
	}
	
}
