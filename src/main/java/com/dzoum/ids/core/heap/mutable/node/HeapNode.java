package com.dzoum.ids.core.heap.mutable.node;

public class HeapNode implements IHeapNode {

	private IHeapNode leftChild;
	private IHeapNode rightChild;
	private int value;
	private int height;
	
	public HeapNode(int value) {
		this.value = value;
		this.height = 1;
		this.leftChild = null;
		this.rightChild = null;
	}
	
	@Override
	public int getValue() {
		return value;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public IHeapNode getLeftChild() {
		return leftChild;
	}

	@Override
	public IHeapNode getRightChild() {
		return rightChild;
	}

	@Override
	public void setLeftChild(IHeapNode leftChild) {
		this.leftChild = leftChild;
	}

	@Override
	public void setRightChild(IHeapNode rightChild) {
		this.rightChild = rightChild;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null) return false;
		if(!(o instanceof HeapNode)) return false;
		
		HeapNode onode = (HeapNode) o;
		
		boolean value = getValue() == onode.getValue();
		boolean height = getHeight() == onode.getHeight();
		boolean left = getLeftChild() == null ? (onode.getLeftChild() == null)
				: getLeftChild().equals(onode.getLeftChild());
		boolean right = getRightChild() == null ? (onode.getRightChild() == null)
				: getRightChild().equals(onode.getRightChild());
		
		return value && height && left && right;
	}

}
