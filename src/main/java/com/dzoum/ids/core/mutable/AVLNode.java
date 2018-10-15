package com.dzoum.ids.core.mutable;

public class AVLNode implements IAVLNode {

	private int key;
	private int height;
	private IAVLNode leftChild;
	private IAVLNode rightChild;
	
	public AVLNode(int key) {
		this.key = key;
		this.height = 1;
		this.leftChild = null;
		this.rightChild = null;
	}

	@Override
	public int getKey() {
		return key;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public IAVLNode getLeftChild() {
		return leftChild;
	}

	@Override
	public IAVLNode getRightChild() {
		return rightChild;
	}

	@Override
	public void setKey(int key) {
		this.key = key;
	}

	@Override
	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public void setLeftChild(IAVLNode leftChild) {
		this.leftChild = leftChild;
	}

	@Override
	public void setRightChild(IAVLNode rightChild) {
		this.rightChild = rightChild;
	}
	
}
