package com.dzoum.ids.core.mutable.heap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dzoum.ids.core.mutable.heap.IMutableHeap;
import com.dzoum.ids.core.mutable.heap.MinMutableHeap;

/**
 * Testing the min mutable heap.
 */
public class TestMinMutableHeap {

	private IMutableHeap mmh;
	
	@Test
	public void testInit() {
		mmh = new MinMutableHeap(5);
		assertEquals(mmh.getCapacity(), 5);
		assertEquals(mmh.getCurrentSize(), 0);
		assertTrue(mmh.isEmpty());
	}
	
	@Test
	public void testEmptyInsertion() {
		mmh = new MinMutableHeap(5);
		
		mmh.insert(25);
		assertEquals(mmh.getMin(), 25);
		
		mmh.insert(24);
		assertEquals(mmh.getMin(), 24);
		
		mmh.insert(1);
		assertEquals(mmh.getMin(), 1);
		assertEquals(mmh.getCurrentSize(), 3);
		assertEquals("[MMH|1,25,24]", mmh.toString());
	}
	
	@Test
	public void testFilledInsertion() {
		mmh = new MinMutableHeap(3);
		assertTrue(mmh.insert(1));
		assertTrue(mmh.insert(2));
		assertTrue(mmh.insert(3));
		assertFalse(mmh.insert(4));
	}
	
	@Test
	public void testEmptyRemove() {
		mmh = new MinMutableHeap(5);
		assertEquals(mmh.remove(), Integer.MIN_VALUE);
		assertEquals(mmh.remove(), Integer.MIN_VALUE);
	}
	
	@Test
	public void testFilledRemove() {
		mmh = new MinMutableHeap(5);
		
		mmh.insert(5);
		mmh.insert(2);
		mmh.insert(254);
		
		assertEquals(mmh.remove(), 2);
		assertEquals(mmh.remove(), 5);
		assertEquals(mmh.remove(), 254);
		assertEquals(mmh.remove(), Integer.MIN_VALUE);
	}
	
	@Test
	public void testRepresentation() {
		mmh = new MinMutableHeap(5);
		assertEquals("[MMH|]", mmh.toString());
		
		mmh.insert(2);
		mmh.insert(25);
		mmh.insert(64);
		
		assertEquals("[MMH|2,25,64]", mmh.toString());
		
		mmh.insert(1);
		mmh.insert(1000);
		
		assertEquals("[MMH|1,2,64,25,1000]", mmh.toString());
		
		mmh.insert(2452);
		
		assertEquals("[MMH|1,2,64,25,1000]", mmh.toString());
		
		mmh.remove();
		
		assertEquals("[MMH|2,25,64,1000]", mmh.toString());
	}
}
