package com.dzoum.ids.core.mutable.redblacktree;

import com.dzoum.ids.core.mutable.avl.IMutableAVLNode;

public class MutableRedBlackTree implements IMutableRedBlackTree {

	private IRedBlackTreeNode root;
	
	public MutableRedBlackTree(int rootValue) {
		// root is always black
		root = new RedBlackTreeNode(rootValue, RedBlackTreeNode.BLACK);
	}

	@Override
	public IRedBlackTreeNode insert(IRedBlackTreeNode node, int value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IRedBlackTreeNode remove() {
		// TODO Auto-generated method stub
		return null;
	}

	// left rotation given the node
	@Override
	public void leftRotate(IRedBlackTreeNode node) {
		// node's right child is the new parent
		IRedBlackTreeNode newParent = node.getRightChild();
		
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

	@Override
	public void rightRotate(IRedBlackTreeNode node) {
		
	}
	


}
