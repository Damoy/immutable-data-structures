package com.lama.ids.app;

import com.lama.ids.core.mutable.IMutableHeap;
import com.lama.ids.core.mutable.MaxMutableHeap;
import com.lama.ids.core.mutable.MinMutableHeap;
import com.lama.ids.core.mutable.MinMutableHeap2;
import com.lama.ids.core.mutable.OHeap;
import com.lama.ids.utils.Utils;

public class Application {

	public static void main(String[] args) {
		oHeap();
	}
	
	private static void trialMaxMutableHeap() {
		int capacity = Utils.irand(5, 15);
		Utils.println("Capacity: " + capacity);
		
		IMutableHeap mh = new MaxMutableHeap(capacity);
		int maxValue = 50;
		
		for(int i = 0; i < capacity; ++i)
			mh.insert(Utils.irand(1, maxValue));
			
		mh.display();
		
		mh.remove();
		mh.remove();
		
		mh.display();
	}
	
	private static void oHeap() {
		OHeap heap = new OHeap();
		
		heap.insert(5);
		heap.insert(2);
		heap.insert(11);
		heap.insert(13);
		heap.insert(65);
		heap.insert(1);
		heap.insert(3);
		
		heap.printHeapList();
		heap.remove();
		heap.printHeapList();
		heap.remove();
		heap.printHeapList();
	}
	
	private static void _trialMinMutableHeap2() {
		IMutableHeap heap = new MinMutableHeap2(10);
		
		heap.insert(5);
		heap.insert(2);
		heap.insert(11);
		heap.insert(13);
		heap.insert(65);
		heap.insert(1);
		heap.insert(3);
		
		heap.display();
		heap.remove();
		heap.display();
		heap.remove();
		heap.display();
	}
	
	private static void _trialMinMutableHeap() {
		IMutableHeap heap = new MinMutableHeap(10);
		
		heap.insert(5);
		heap.insert(2);
		heap.insert(11);
		heap.insert(13);
		heap.insert(65);
		heap.insert(1);
		heap.insert(3);
		
		heap.display();
		heap.remove();
		heap.display();
		heap.remove();
		heap.display();
	}
	
	private static void trialMinMutableHeap() {
		int capacity = Utils.irand(5, 15);
		Utils.println("Capacity: " + capacity);
		
		IMutableHeap mh = new MinMutableHeap(capacity);
		int maxValue = 50;
		
		for(int i = 0; i < capacity; ++i)
			mh.insert(Utils.irand(1, maxValue));
			
		mh.display();
		
		mh.remove();
		mh.remove();
		
		mh.display();
	}

}
