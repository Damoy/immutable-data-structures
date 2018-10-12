package com.lama.ids.core.mutable;

import com.lama.ids.model.Data;
import com.lama.ids.model.IData;

/**
 * Mutable min heap.
 */
public class MutableHeap implements IMutableHeap{

	private int currentSize;
	private int capacity;
	private IData content;
	
	public MutableHeap(int capacity){
		this.currentSize = 0;
		this.capacity = capacity;
		this.content = Data.of(capacity);
	}
	
	public void insert(int elt) {
		if(currentSize >= capacity) {
			content.increase(capacity << 1);
			capacity = capacity << 1;
		}
		
		// increase size and set element
		++currentSize;
		int i = capacity - 1;
		content.set(i, elt);
		
		// fix min heap property
		while(i != 0 && content.get(getParent(i))) {
			
		}
	}
	
	private int getParent(int i) {
		return ((i - 1) >> 1);
	}
	
	public int getCurrentSize(){
		return currentSize;
	}
	
	public int getCapacity(){
		return capacity;
	}
	
}
