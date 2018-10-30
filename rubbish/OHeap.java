package com.lama.ids.core.mutable;

// This is a min heap
public class OHeap {
	protected int[] data = new int[1000];

	public OHeap() {
		this.data[0] = 0;
	}

	public OHeap insert(int value) {
		++this.data[0];
		this.data[this.data[0]] = value;
		this.bubbleUp(this.data[0]);
		return this;
	}

	protected void bubbleUp(int index) {
		// TASK: Find the parent index
		int parentIndex = index / 2;

		// TASK: check halt condition, if parent is less than children
		// or current is the root, we need to do nothing
		if (index == 1 || this.data[parentIndex] < this.data[index])
			return;

		// TASK: Swap children and parent
		int parentValue = this.data[parentIndex];
		this.data[parentIndex] = this.data[index];
		this.data[index] = parentValue;

		// TASK: Recursively do the same thing with new parent
		bubbleUp(parentIndex);
	}

	public int remove() {
		int minValue = this.data[1];
		int lastValue = this.data[this.data[0]--];
		this.data[1] = lastValue;
		this.sinkDown(1);
		return minValue;
	}

	protected boolean isLeaf(int index) {
		return (index * 2) > this.data[0];
	}

	protected void sinkDown(int index) {
		// TASK: Check if leaf, if that's the case do nothing
		if (this.isLeaf(index))
			return;

		// TASK: Initialize children variables
		int leftChildIndex = index * 2;
		int rightChildIndex = (index * 2) + 1;
		int minChildIndex;

		// TASK: Check if rightChildren doesn't exist, in that case
		// the minChildIndex is the left, otherwise, find the min children
		if (rightChildIndex > this.length()) {
			minChildIndex = leftChildIndex;
		} else {

			minChildIndex = (this.data[leftChildIndex] < this.data[rightChildIndex]) ? leftChildIndex : rightChildIndex;
		}

		// TASK: stop in case the parent is already smaller than the children
		if (this.data[index] < this.data[minChildIndex])
			return;

		int childValue = this.data[minChildIndex];
		this.data[minChildIndex] = this.data[index];
		this.data[index] = childValue;

		// TASK: apply the same algorithm recursively
		sinkDown(minChildIndex);
	}

	public int minValue() {
		return this.data[1];
	}

	public int length() {
		return this.data[0];
	}

	public void printHeapList() {
		for (int i = 0; i < data[0]; i++) {
			System.out.printf("%d, ", this.data[i]);
		}
		System.out.printf("%d\n", this.data[this.data[0]]);
	}

	public static void main(String[] args) {
		OHeap h = new OHeap();
		h.insert(2);
		h.insert(4);
		h.insert(1);
		h.insert(-10);
		h.printHeapList();
		h.remove();
		h.printHeapList();
	}
}