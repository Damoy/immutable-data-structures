package com.dzoum.ids.app;

import com.dzoum.ids.core.heap.mutable.IMutableHeap;
import com.dzoum.ids.core.heap.mutable.MinMutableHeap;
import com.dzoum.ids.utils.Utils;

public class AppMinMutableHeap {

	public static void main(String[] args) {
		trialSimple();
		Utils.println("----------");
		trialMinMutableHeap();
	}
	
	private static void trialSimple() {
		IMutableHeap heap = new MinMutableHeap(10);
		
		Utils.println("Inserting...");
		heap.insert(5);
		heap.insert(2);
		heap.insert(11);
		heap.insert(13);
		heap.insert(65);
		heap.insert(1);
		heap.insert(3);
		
		heap.display();
		Utils.println("Removing...");
		heap.remove();
		heap.display();
		Utils.println("Removing...");
		heap.remove();
		heap.display();
	}
	
	private static void trialMinMutableHeap() {
		int capacity = Utils.irand(5, 15);
		Utils.println("Capacity: " + capacity);
		int insertionTimes = Utils.irand(capacity >> 1, capacity);
		Utils.println("Insertion times: " + insertionTimes);
		
		IMutableHeap mh = new MinMutableHeap(capacity);
		int maxValue = 50;
		int removeTimes = Utils.irand(1, insertionTimes >> 1);
		Utils.println("Remove times: " + removeTimes);
		
		for(int i = 0; i < capacity; ++i)
			mh.insert(Utils.irand(1, maxValue));
		
		Utils.println("Insertions done");
		mh.display();
		
		for(int i = 0; i < removeTimes; ++i)
			mh.remove();
		
		Utils.println("Deletions done");
		mh.display();
	}
	
}
