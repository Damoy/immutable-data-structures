package com.lama.ids.core.mutable;

import com.lama.ids.model.Data;
import com.lama.ids.model.IData;
import com.lama.ids.utils.Utils;

/**
 * Min mutable classic heap.
 */
@Deprecated // MAX
public class MinMutableHeap implements IMutableHeap {

	private int currentSize;
	private int capacity;
	private IData content;

	public MinMutableHeap(int capacity) {
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

		content.set(++currentSize, elt);
		int pos = currentSize;

		while (pos != 1 && elt > content.get(pos >> 1)) {
			content.set(pos, content.get(pos >> 1));
			pos >>= 1;
		}

		content.set(pos, elt);
		return true;
	}

	@Override
	public int remove() {
		if (isEmpty())
			throw new IllegalStateException("Cannot remove from empty heap !");


		int item = content.get(0);
		int tmp = content.get(currentSize--);
		int parent = 1;
		int ichild = 2;
		
		while (ichild <= currentSize) {
			if (ichild < currentSize && content.get(ichild) < content.get(ichild + 1))
				ichild++;
				
			if (tmp >= content.get(ichild))
				break;

			content.set(parent, content.get(ichild));
			parent = ichild;
			ichild *= 2;
		}
		
		content.set(parent, tmp);
		return item;
	}

	private void adjust(int i) {
		if (isLeaf(content.get(i)))
			return;

		int lchildIndex = (i << 1) + 1;
		int rchildIndex = (i << 1) + 2;
		int minChildIndex;
		
		if(rchildIndex > currentSize - 1) {
			minChildIndex = lchildIndex;
		} else {
			minChildIndex = (content.get(lchildIndex) < content.get(rchildIndex)) ? lchildIndex : rchildIndex;
		}
		
		if(content.get(minChildIndex) > content.get(i)) return;
		
		swap(minChildIndex, i);
		adjust(minChildIndex);
	}

	private void swap(int i, int j) {
		int tmp = content.get(i);
		content.set(i, content.get(j));
		content.set(j, tmp);
	}

	private boolean isLeaf(int i) {
		return ((i << 1) + 1) > currentSize;
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
