package com.dzoum.ids.core.mutable.redblacktree;

public class MutableRedBlackTreeNode implements IMutableRedBlackTreeNode {

	public final static byte RED = 0;
	public final static byte BLACK = 1;
	public final static byte NULL = 2;
	
	private int value;
	private byte color;
	private IMutableRedBlackTreeNode parent;
	private IMutableRedBlackTreeNode leftChild;
	private IMutableRedBlackTreeNode rightChild;
	
	public MutableRedBlackTreeNode(int value, byte color, IMutableRedBlackTreeNode parent, IMutableRedBlackTreeNode leftChild,
			IMutableRedBlackTreeNode rightChild) {
		this.value = value;
		this.color = color;
		this.parent = parent;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}
	
	public MutableRedBlackTreeNode(int value, byte color) {
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
	public IMutableRedBlackTreeNode getParent() {
		return parent;
	}

	@Override
	public IMutableRedBlackTreeNode getLeftChild() {
		return leftChild;
	}

	@Override
	public IMutableRedBlackTreeNode getRightChild() {
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
	public void setParent(IMutableRedBlackTreeNode parent) {
		this.parent = parent;
	}

	@Override
	public void setLeftChild(IMutableRedBlackTreeNode leftChild) {
		this.leftChild = leftChild;
	}

	@Override
	public void setRightChild(IMutableRedBlackTreeNode rightChild) {
		this.rightChild = rightChild;
	}

	@Override
	public IMutableRedBlackTreeNode getBrother() {
		if(parent == null)
			return null;
		
		// if this is left child of its parent
		if(isLeftChild()){
			return parent.getRightChild();
		}
		
		return parent.getLeftChild();
	}

	@Override
	public IMutableRedBlackTreeNode getUncle() {
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
		return color == MutableRedBlackTreeNode.RED;
	}

	@Override
	public boolean isBlack() {
		return color == MutableRedBlackTreeNode.BLACK;
	}

	@Override
	public void moveDown(IMutableRedBlackTreeNode newParent) {
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

	@Override
	public void swapColor(IMutableRedBlackTreeNode with) {
		if(with == null) return;
		byte thisColor = color;
		color = with.getColor();
		with.setColor(thisColor);
	}

	@Override
	public void swapValue(IMutableRedBlackTreeNode with) {
		if(with == null) return;
		int thisValue = value;
		value = with.getValue();
		with.setValue(thisValue);
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(getColorRepresentation(color));
		sb.append("|");
		sb.append(value);
		sb.append("]");
		return sb.toString();
	}
	
	private String getColorRepresentation(byte color){
		if(color == RED) return "R";
		if(color == BLACK) return "B";
		throw new IllegalStateException("Unknown color !");
	}

}
