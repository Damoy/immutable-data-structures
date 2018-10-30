package com.lama.ids.core.mutable;

import com.lama.ids.model.Data;
import com.lama.ids.model.IData;
import com.lama.ids.utils.Utils;

/**
 * Mutable min heap.
 * 
 * Capacity increases if needed.
 */
public class MutableHeap implements IMutableHeap {

	private int currentSize;
	private int capacity;
	private IData content;

	public MutableHeap(int capacity) {
		this.currentSize = 0;
		this.capacity = capacity;
		this.content = Data.of(capacity);
	}

	@Override
	public boolean insert(int elt) {
		if (currentSize + 1 == capacity) {
			Utils.println("Could not insert element into heap.");
			return false;
		}
		// increase size and set element
		content.set(++currentSize, elt);
		int pos = currentSize;

		// fix min heap property
		// pos >> 1 == pos / 2 ; to get parent
		while (pos != 1 && content.get(pos >> 1)  < elt) {
			content.set(pos, content.get(pos >> 1));
			pos >>= 1;
		}
		
		content.set(pos, elt);
		return true;
	}
	
	@Override
	public int remove() {
		if(isEmpty())
			throw new IllegalStateException("Cannot remove from empty heap !");
		
		int item = content.get(1);
		int tmp = content.get(currentSize--);
		int parent = 1;
		int child = 2;
		
		// heapify
		while(child <= currentSize) {
			if(child < currentSize && content.get(child + 1) > content.get(child + 1))
				++child;
			
			if(tmp >= content.get(child))
				break;
			
			content.set(parent, content.get(child));
			parent = child;
			child <<= 1; // child = child * 2
		}
		
		content.set(parent, tmp);
		return item;
	}

	@Override
	public boolean isEmpty() {
		return currentSize == 0;
	}

	@Override
	public int getCurrentSize() {
		return currentSize;
	}

	@Override
	public int getCapacity() {
		return capacity;
	}

	@Override
	public void display() {
		for (int i = 0; i < currentSize - 1; i++)
			Utils.print(content.get(i) + ",");
		
		Utils.println(content.get(content.getLength() - 1));
	}

}
