package com.dzoum.ids.core.mutable.avl;

/**
 * {@link IMutableAVLNode}
 */
public class MutableAVLNode implements IMutableAVLNode {

	private int value;
	private int height;
	private IMutableAVLNode leftChild;
	private IMutableAVLNode rightChild;
	
	public MutableAVLNode(int value) {
		this.value = value;
		this.height = 1;
		this.leftChild = null;
		this.rightChild = null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getValue() {
		return value;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getHeight() {
		return height;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IMutableAVLNode getLeftChild() {
		return leftChild;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IMutableAVLNode getRightChild() {
		return rightChild;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setLeftChild(IMutableAVLNode leftChild) {
		this.leftChild = leftChild;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setRightChild(IMutableAVLNode rightChild) {
		this.rightChild = rightChild;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null) return false;
		if(!(o instanceof IMutableAVLNode)) return false;
		
		IMutableAVLNode onode = (IMutableAVLNode) o;
		
		boolean value = getValue() == onode.getValue();
		boolean left = getLeftChild() == null ? (onode.getRightChild() == null)
				: getLeftChild().equals(onode.getRightChild());
		boolean right = getRightChild() == null ? (onode.getRightChild() == null)
				: getRightChild().equals(onode.getRightChild());
		
		return value && left && right;
	}
	
	
}
