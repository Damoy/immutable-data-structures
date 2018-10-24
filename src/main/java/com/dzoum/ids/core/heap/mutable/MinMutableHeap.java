package com.dzoum.ids.core.heap.mutable;

import com.dzoum.ids.model.Data;
import com.dzoum.ids.model.IData;
import com.dzoum.ids.utils.Utils;

/**
 * Min mutable classic heap.
 */
public class MinMutableHeap implements IMutableHeap {
	
	private IData data;

	public MinMutableHeap(int capacity) {
		// one more that contain size
		this.data = Data.of(capacity + 1);
		// data.get(0) is the current size
		this.data.set(0, 0);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean insert(int elt) {
		if(data.get(0) + 1 == data.getSize()) {
			Utils.println("Could not insert element into heap.");
			return false;
		}
		
		// increase current size
		data.set(0, data.get(0) + 1);
		// increase element
		data.set(data.get(0), elt);
		// heapify up
		heapifyUp(data.get(0));
		
		return true;
	}

	private void heapifyUp(int index) {
		// find the parent index
		int iparent = index >> 1;

		// check stop condition, if parent is less than children
		// or current is the root, we need to do nothing
		if (index == 1 || data.get(iparent) < data.get(index))
			return;

		// swap children and parent
		int parentVal = data.get(iparent);
		data.set(iparent, data.get(index));
		data.set(index, parentVal);

		// do the same with the new parent
		heapifyUp(iparent);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int remove() {
		if(data.get(0) == 0)
			throw new IllegalStateException("Cannot remove from empty heap !");
		
		// get min
		int minVal = data.get(1);
		// get last
		int lastVal = data.get(data.get(0));
		
		// update current size
		data.set(0, data.get(0) - 1);
		// update min value
		data.set(1, lastVal);
		
		// heapify down
		heapifyDown(1);
		
		return minVal;
	}

	private void heapifyDown(int index) {
		// check if leaf, if that's the case do nothing
		// checks that parent is greater than current size
		if ((index << 1) > data.get(0))
			return;

		// initialize children variables
		int ileftchild = index << 1;
		int irightChild = (index << 1) + 1;
		int iminChild;

		// check if right child does not exist
		// in that case iminChild is the left otherwise find the min child
		if (irightChild > data.get(0)) {
			iminChild = ileftchild;
		} else {
			iminChild = (data.get(ileftchild) < data.get(irightChild)) ? ileftchild : irightChild;
		}

		// stops if the parent is smaller than the child
		if (data.get(index) < data.get(iminChild))
			return;

		int childVal = data.get(iminChild);
		data.set(iminChild, data.get(index));
		data.set(index, childVal);
		
		// recursive algorithm
		heapifyDown(iminChild);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		return data.get(0) == 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCurrentSize() {
		return data.get(0);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCapacity() {
		return data.getSize() - 1;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getMin() {
		return data.get(1);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[MMH|");
		
		// i starts from one because element 0 is 
		for (int i = 1; i < data.get(0); ++i) {
			sb.append(data.get(i));
			sb.append(",");
		}
		
		if(data.get(data.get(0)) > 0)
			sb.append(data.get(data.get(0)));
		
		sb.append("]");
		
		return sb.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void display() {
		Utils.println(toString());
	}
}