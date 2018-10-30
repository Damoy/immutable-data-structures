package com.lama.ids.core.mutable;

import com.lama.ids.model.Data;
import com.lama.ids.model.IData;
import com.lama.ids.utils.Utils;

/**
 * Max mutable classic heap.
 *
 * https://cs.nyu.edu/courses/spring12/CSCI-GA.3033-014/Assignment3/heap.html
 */
public class MaxMutableHeap implements IMutableHeap {

	private int currentSize;
	private int capacity;
	private IData content;

	public MaxMutableHeap(int capacity) {
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

		int pos = currentSize++;
		content.set(pos, elt);

		while (pos != 0 && content.get(pos) > content.get(pos >> 1)) {
			swap(pos, pos >> 1);
			pos >>= 1;
		}

		return true;
	}

	@Override
	public int remove() {
		if (isEmpty())
			throw new IllegalStateException("Cannot remove from empty heap !");

		int returnVal = content.get(0);
		content.set(0, content.get(--currentSize));
		adjust(0);
		return returnVal;
	}

	private void adjust(int i) {
		if (isLeaf(content.get(i)))
			return;

		if (content.get(i) < content.get((i << 1) + 1) || content.get(i) < content.get((i << 1) + 2)) {
			int max = (content.get((i << 1) + 1) > (content.get((i << 1) + 2))) ? ((i << 1) + 1) : ((i << 1) + 2);
			swap(i, max);
			adjust(max);
		}
	}

	private void swap(int i, int j) {
		int tmp = content.get(i);
		content.set(i, content.get(j));
		content.set(j, tmp);
	}

	private boolean isLeaf(int i) {
		return (i << 1) + 1 > currentSize;
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
