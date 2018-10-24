package com.dzoum.ids.core.heap.mutable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.dzoum.ids.core.heap.mutable.array.IMutableArrayHeap;
import com.dzoum.ids.core.heap.mutable.array.MinMutableArrayHeap;
import com.dzoum.ids.utils.Utils;

/**
 * Testing the min mutable heap.
 */
@RunWith(JUnit4.class)
public class TestMinMutableArrayHeap {

	private IMutableArrayHeap mmh;
	
	@Test
	public void testInit() {
		mmh = new MinMutableArrayHeap(5);
		assertEquals(mmh.getCapacity(), 5);
		assertEquals(mmh.getCurrentSize(), 0);
		assertTrue(mmh.isEmpty());
		assertTrue(mmh.isValid());
	}
	
	/**
	 * Testing the insertions.
	 */
	@Test
	public void testSafeInsertionsAuto() {
		long start = System.nanoTime();
		StringBuilder sb = new StringBuilder();
		// number of launches
		int nb = 50;
		
		for(int i = 0; i < nb; ++i) {
			long innerStart = System.nanoTime();
			sb.setLength(0);
			int min = -Utils.irand((int) Math.pow(2, 8), (int) Math.pow(2, 16));
			int max = Utils.irand((int) Math.pow(2, 8), (int) Math.pow(2, 16));
			int size = Utils.irand((int) Math.pow(2, 8), (int) Math.pow(2, 16));
			
			IMutableArrayHeap heap = new MinMutableArrayHeap(size);
			
			// actual check
			for(int iv = 0; iv < size; ++iv){
				heap.insert(Utils.irand(min, max));
				assertTrue(heap.isValid());
			}

			sb.append("testSafeInsertionsAuto | min:");
			sb.append(min);
			sb.append(", max:");
			sb.append(max);
			sb.append(", size:");
			sb.append(size);
			sb.append(" | took ");
			sb.append((System.nanoTime() - innerStart) / 1000000);
			sb.append(" ms.");
			
			Utils.println(sb.toString());
		}
		
		Utils.println(nb + " testSafeInsertionsAuto took " + (System.nanoTime() - start) / 1000000 + " ms.");
	}
	
	/**
	 * Testing the removals.
	 */
	@Test
	public void testSafeRemovalsAuto() {
		long start = System.nanoTime();
		StringBuilder sb = new StringBuilder();
		// number of launches
		int nb = 50;
		
		for(int i = 0; i < nb; ++i) {
			long innerStart = System.nanoTime();
			sb.setLength(0);
			int min = -Utils.irand((int) Math.pow(2, 8), (int) Math.pow(2, 16));
			int max = Utils.irand((int) Math.pow(2, 8), (int) Math.pow(2, 16));
			int size = Utils.irand((int) Math.pow(2, 8), (int) Math.pow(2, 16));
			
			IMutableArrayHeap heap = new MinMutableArrayHeap(size);
			
			// first perform insertions
			for(int insertionCount = 0; insertionCount < size; ++insertionCount)
				heap.insert(Utils.irand(min, max));
			
			// actual check
			for(int removeCount = 0; removeCount < size; ++removeCount) {
				assertTrue(heap.isValid());
				heap.remove();
			}

			sb.append("testSafeRemovalsAuto | min:");
			sb.append(min);
			sb.append(", max:");
			sb.append(max);
			sb.append(", size:");
			sb.append(size);
			sb.append(" | took ");
			sb.append((System.nanoTime() - innerStart) / 1000000);
			sb.append(" ms.");
			
			Utils.println(sb.toString());
		}
		
		Utils.println(nb + " testSafeRemovalsAuto took " + (System.nanoTime() - start) / 1000000 + " ms.");
	}
	
	/**
	 * Testing insertions and removals.
	 */
	@Test
	public void testSafeInsertionsRemovalsAuto() {
		long start = System.nanoTime();
		StringBuilder sb = new StringBuilder();
		// number of launches
		int nb = 50;
		
		for(int i = 0; i < nb; ++i) {
			long innerStart = System.nanoTime();
			sb.setLength(0);
			int min = -Utils.irand((int) Math.pow(2, 8), (int) Math.pow(2, 16));
			int max = Utils.irand((int) Math.pow(2, 8), (int) Math.pow(2, 16));
			int size = Utils.irand((int) Math.pow(2, 8), (int) Math.pow(2, 16));
			
			IMutableArrayHeap heap = new MinMutableArrayHeap(size);
			
			// actual check
			while(heap.getCurrentSize() < heap.getCapacity()) {
				// insert
				heap.insert(Utils.irand(min, max));
				// check
				assertTrue(heap.isValid());
				// remove
				heap.remove();
				// insert
				heap.insert(Utils.irand(min, max));
			}
			
			// final check
			assertTrue(heap.isValid());
			
			sb.append("testSafeInsertionsRemovalsAuto | min:");
			sb.append(min);
			sb.append(", max:");
			sb.append(max);
			sb.append(", size:");
			sb.append(size);
			sb.append(" | took ");
			sb.append((System.nanoTime() - innerStart) / 1000000);
			sb.append(" ms.");
			
			Utils.println(sb.toString());
		}
		
		Utils.println(nb + " testSafeInsertionsRemovalsAuto took " + (System.nanoTime() - start) / 1000000 + " ms.");
	}
	
	@Test
	public void testEmptyInsertion() {
		mmh = new MinMutableArrayHeap(5);
		
		mmh.insert(25);
		assertEquals(mmh.getMin(), 25);
		assertTrue(mmh.isValid());
		
		mmh.insert(24);
		assertEquals(mmh.getMin(), 24);
		assertTrue(mmh.isValid());
		
		mmh.insert(1);
		assertEquals(mmh.getMin(), 1);
		assertEquals(mmh.getCurrentSize(), 3);
		assertTrue(mmh.isValid());
		assertEquals("[MMH|1,25,24]", mmh.toString());
	}
	
	@Test
	public void testFilledInsertion() {
		mmh = new MinMutableArrayHeap(3);
		assertTrue(mmh.insert(1));
		assertTrue(mmh.isValid());
		assertTrue(mmh.insert(2));
		assertTrue(mmh.isValid());
		assertTrue(mmh.insert(3));
		assertTrue(mmh.isValid());
		assertFalse(mmh.insert(4));
		assertTrue(mmh.isValid());
	}
	
	@Test(expected = IllegalStateException.class)
	public void testEmptyRemove() {
		mmh = new MinMutableArrayHeap(5);
		assertTrue(mmh.isValid());
		mmh.remove();
		assertTrue(mmh.isValid());
		mmh.remove();
		assertTrue(mmh.isValid());
	}
	
	@Test(expected = IllegalStateException.class)
	public void testFilledRemove() {
		mmh = new MinMutableArrayHeap(5);
		
		mmh.insert(5);
		assertTrue(mmh.isValid());
		mmh.insert(2);
		assertTrue(mmh.isValid());
		mmh.insert(254);
		assertTrue(mmh.isValid());
		
		assertEquals(mmh.remove(), 2);
		assertTrue(mmh.isValid());
		assertEquals(mmh.remove(), 5);
		assertTrue(mmh.isValid());
		assertEquals(mmh.remove(), 254);
		assertTrue(mmh.isValid());
		mmh.remove();
		assertTrue(mmh.isValid());
	}
	
	@Test
	public void testRepresentation() {
		mmh = new MinMutableArrayHeap(5);
		assertEquals("[MMH|]", mmh.toString());
		
		mmh.insert(2);
		mmh.insert(25);
		mmh.insert(64);
		
		assertTrue(mmh.isValid());
		assertEquals("[MMH|2,25,64]", mmh.toString());
		
		mmh.insert(1);
		mmh.insert(1000);
		
		assertTrue(mmh.isValid());
		assertEquals("[MMH|1,2,64,25,1000]", mmh.toString());
		
		mmh.insert(2452);
		
		assertTrue(mmh.isValid());
		assertEquals("[MMH|1,2,64,25,1000]", mmh.toString());
		
		mmh.remove();
		assertTrue(mmh.isValid());
		assertEquals("[MMH|2,25,64,1000]", mmh.toString());
	}
}
