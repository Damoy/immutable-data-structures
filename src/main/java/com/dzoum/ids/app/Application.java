package com.dzoum.ids.app;

import com.dzoum.ids.core.mutable.AVLNode;
import com.dzoum.ids.core.mutable.IAVLNode;
import com.dzoum.ids.core.mutable.IMutableAVL;
import com.dzoum.ids.core.mutable.IMutableHeap;
import com.dzoum.ids.core.mutable.MinMutableHeap;
import com.dzoum.ids.core.mutable.MutableAVL;
import com.dzoum.ids.utils.Utils;

/**
 * Entry point
 */
public class Application {

	public static void main(String[] args) {
		// trialMMH();
		// trialMinMutableHeap();
		trialMAVL();
	}
	
	private static void trialMAVL(){
		IMutableAVL mavl = MutableAVL.build(10, 20, 30, 40, 50, 25);
		mavl.preOrderPrint();
	}
	
	private static void trialMMH() {
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