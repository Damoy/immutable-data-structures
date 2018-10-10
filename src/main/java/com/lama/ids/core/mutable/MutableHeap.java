package com.lama.ids.core.mutable;

import com.lama.ids.model.Data;
import com.lama.ids.model.IData;

public class MutableHeap implements IMutableHeap{

	private int currentSize;
	private int capacity;
	private IData content;
	
	public MutableHeap(int capacity){
		this.currentSize = 0;
		this.capacity = capacity;
		this.content = Data.of(capacity);
	}
	
	public int getCurrentSize(){
		return currentSize;
	}
	
	public int getCapacity(){
		return capacity;
	}
	
}
