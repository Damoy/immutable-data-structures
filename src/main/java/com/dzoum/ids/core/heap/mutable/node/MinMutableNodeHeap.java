package com.dzoum.ids.core.heap.mutable.node;

// TODO
public class MinMutableNodeHeap implements IMutableNodeHeap {

	private IHeapNode root;
	private int capacity;
	private int currentSize;
	
	public MinMutableNodeHeap(int capacity) {
		this.capacity = capacity;
		this.currentSize = 0;
		this.root = null;
	}
	
	@Override
	public boolean insert(int elt) {
		return false;
	}

	@Override
	public int remove() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getCurrentSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCapacity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMin() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IHeapNode getRoot() {
		// TODO Auto-generated method stub
		return null;
	}

}
