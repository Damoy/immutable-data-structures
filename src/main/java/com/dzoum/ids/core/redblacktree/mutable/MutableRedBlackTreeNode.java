package com.dzoum.ids.core.redblacktree.mutable;

import static com.dzoum.ids.utils.Utils.*;

public class MutableRedBlackTreeNode implements IMutableRedBlackTreeNode {

	public final static byte RED = 0;
	public final static byte BLACK = 1;
	
	private int value;
	private int height;
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
	
	@Override
	public boolean equals(Object o) {
		if(o == null) return false;
		if(!(o instanceof IMutableRedBlackTreeNode)) return false;
		
		IMutableRedBlackTreeNode onode = (IMutableRedBlackTreeNode) o;
		
		boolean value = getValue() == onode.getValue();
		boolean color = getColor() == onode.getColor();
		
		boolean parent = getParent() == null ? (onode.getParent() == null)
				: getParent().equals(onode.getParent());
		
		boolean left = getLeftChild() == null ? (onode.getLeftChild() == null)
				: getLeftChild().equals(onode.getLeftChild());
		
		boolean right = getRightChild() == null ? (onode.getRightChild() == null)
				: getRightChild().equals(onode.getRightChild());
		
		return value && color && parent && left && right;
	}
	
	private String getColorRepresentation(byte color){
		if(color == RED) return "R";
		if(color == BLACK) return "B";
		throw new IllegalStateException("Unknown color !");
	}

	@Override
	public int getHeight() {
		height = max(getNodeHeight(getLeftChild()), getNodeHeight(getRightChild())) + 1;
		return height;
	}
	
	@Override
	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public void setLeftChild(IMutableRedBlackTreeNode leftChild) {
		this.leftChild = leftChild;
	}

	@Override
	public void setRightChild(IMutableRedBlackTreeNode rightChild) {
		this.rightChild = rightChild;
	}

}
