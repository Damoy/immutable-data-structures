package com.dzoum.ids.app;

import com.dzoum.ids.core.mutable.IMutableAVL;
import com.dzoum.ids.core.mutable.IMutableAVLBuilder;
import com.dzoum.ids.core.mutable.IMutableHeap;
import com.dzoum.ids.core.mutable.MinMutableHeap;
import com.dzoum.ids.core.mutable.MutableAVLBuilder;
import com.dzoum.ids.utils.Utils;

/**
 * Entry point
 */
public class Application {

	public static void main(String[] args) {
		// trialMAVL();
		trialMAVL2();
	}
	
	private static void trialMAVL(){
		IMutableAVLBuilder builder = new MutableAVLBuilder();
		builder.insert(10);
		builder.insert(20);
		builder.insert(30);
		builder.insert(40);
		builder.insert(50);
		builder.insert(25);
		IMutableAVL mavl = builder.build();
		mavl.preOrderPrint();
	}
	
	private static void trialMAVL2(){
		IMutableAVLBuilder builder = new MutableAVLBuilder();
		builder.insert(9, 5, 10, 0, 6, 11, -1, 1, 2);
		
		builder.build().preOrderPrint();
		builder.remove(10);
		builder.build().preOrderPrint();
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
