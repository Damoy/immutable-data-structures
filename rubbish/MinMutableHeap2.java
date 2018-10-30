package com.lama.ids.core.mutable;

import com.lama.ids.model.Data;
import com.lama.ids.model.IData;
import com.lama.ids.utils.Utils;

/**
 * Min mutable classic heap.
 */
public class MinMutableHeap2 implements IMutableHeap {

	private int currentSize;
	private int capacity;
	private IData content;

	public MinMutableHeap2(int capacity) {
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

		content.set(0, elt);
		int pos = ++currentSize;
		
		// heapify
//		for(; (!(elt < content.get(pos >> 1)); pos >>= 1) {
//			content.set(pos, content.get(pos >> 1));
//		}
		
		content.set(pos, elt);
		return true;
	}

	@Override
	public int remove() {
		if (isEmpty())
			throw new IllegalStateException("Cannot remove from empty heap !");

		int pos = 1;
		int min = content.get(pos);
		content.set(1, content.get(currentSize--));
		
		// heapify
		int child;
		int tmp = content.get(pos);
		
		while((pos << 1) <= currentSize) {
			child = pos << 1;
			
			if(child != currentSize && content.get(child + 1) < content.get(child))
				++child;
			
			if(content.get(child) < tmp) {
				content.set(pos, content.get(child));
			} else {
				break;
			}
			
			content.set(pos, tmp);
			pos = child;
		}
		
		return min;
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
		StringBuilder sb = new StringBuilder();

		sb.append("[MMH:");

		for (int i = 0; i < currentSize - 1; i++) {
			sb.append(content.get(i));
			sb.append(",");
		}

		sb.append(content.get(currentSize - 1));
		sb.append("]");

		Utils.println(sb.toString());
	}

}
