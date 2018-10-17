package com.dzoum.ids.core.mutable.redblacktree;

public class RedBlackTreeNode implements IRedBlackTreeNode {

	public final static byte RED = 0;
	public final static byte BLACK = 1;
	
	private int value;
	private byte color;
	private IRedBlackTreeNode parent;
	private IRedBlackTreeNode leftChild;
	private IRedBlackTreeNode rightChild;
	
	public RedBlackTreeNode(int value, byte color, IRedBlackTreeNode parent, IRedBlackTreeNode leftChild,
			IRedBlackTreeNode rightChild) {
		this.value = value;
		this.color = color;
		this.parent = parent;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}
	
	public RedBlackTreeNode(int value, byte color) {
		this.value = value;
		this.color = color;
		this.parent = null;
		this.leftChild = null;
		this.rightChild = null;
	}

	@Override
	public int getValue() {
		return value;
	}

	@Override
	public byte getColor() {
		return color;
	}

	@Override
	public IRedBlackTreeNode getParent() {
		return parent;
	}

	@Override
	public IRedBlackTreeNode getLeftChild() {
		return leftChild;
	}

	@Override
	public IRedBlackTreeNode getRightChild() {
		return rightChild;
	}

	@Override
	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public void setColor(byte color) {
		this.color = color;
	}

	@Override
	public void setParent(IRedBlackTreeNode parent) {
		this.parent = parent;
	}

	@Override
	public void setLeftChild(IRedBlackTreeNode leftChild) {
		this.leftChild = leftChild;
	}

	@Override
	public void setRightChild(IRedBlackTreeNode rightChild) {
		this.rightChild = rightChild;
	}

	@Override
	public IRedBlackTreeNode getBrother() {
		if(parent == null)
			return null;
		
		// if this is left child of its parent
		if(isLeftChild()){
			return parent.getRightChild();
		}
		
		return parent.getLeftChild();
	}

	@Override
	public IRedBlackTreeNode getUncle() {
		if(parent == null || parent.getParent() == null)
			return null;
	
		if(parent.isLeftChild()){
			return parent.getParent().getRightChild();
		}
		
		return parent.getParent().getLeftChild();
	}

	@Override
	public boolean isRightChild() {
		if(parent == null) return false;
		return parent.getRightChild() == this;
	}

	@Override
	public boolean isLeftChild() {
		if(parent == null) return false;
		return parent.getLeftChild() == this;
	}

	@Override
	public boolean hasRedChild() {
		return (leftChild != null && leftChild.isRed())
				|| (rightChild != null && rightChild.isRed());
	}

	@Override
	public boolean isRed() {
		return color == RedBlackTreeNode.RED;
	}

	@Override
	public boolean isBlack() {
		return color == RedBlackTreeNode.BLACK;
	}

	@Override
	public void moveDown(IRedBlackTreeNode newParent) {
		if(parent != null){
			if(isLeftChild()){
				parent.setLeftChild(newParent);
			} else {
				parent.setRightChild(newParent);
			}
		}
		
		newParent.setParent(parent);
		parent = newParent;
	}

}
