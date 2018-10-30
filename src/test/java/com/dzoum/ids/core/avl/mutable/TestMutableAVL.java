package com.dzoum.ids.core.avl.mutable;

import static com.dzoum.ids.utils.Utils.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.dzoum.ids.core.avl.mutable.IMutableAVL;
import com.dzoum.ids.core.avl.mutable.IMutableAVLBuilder;
import com.dzoum.ids.core.avl.mutable.MutableAVLBuilder;
import com.dzoum.ids.core.avl.mutable.MutableAVLNode;
import com.dzoum.ids.core.avl.mutable.NotMutableAVLException;
import com.dzoum.ids.model.generation.Generator;
import com.dzoum.ids.utils.Utils;

/**
 * Testing the mutable AVL
 */
@RunWith(JUnit4.class)
public class TestMutableAVL {

	private IMutableAVLBuilder builder;
	private IMutableAVL mavl;
	
	@Before
	public void setup() {
		builder = new MutableAVLBuilder();
	}
	
	/**
	 * Testing the insertions.
	 * 
	 * If this test does not throw exception,
	 * mutable AVL built is valid.<br>
	 * 
	 * @throws NotMutableAVLException 
	 */
	@Test
	public void testSafeBuildMutableAvlAuto() throws NotMutableAVLException {
		long start = System.nanoTime();
		StringBuilder sb = new StringBuilder();
		// number of launches
		int nb = 50;
		
		for(int i = 0; i < nb; ++i) {
			long innerStart = System.nanoTime();
			sb.setLength(0);
			int min = -Utils.irand((int) Math.pow(2, 4), (int) Math.pow(2, 12));
			int max = Utils.irand((int) Math.pow(2, 4), (int) Math.pow(2, 12));
			int size = Utils.irand((int) Math.pow(2, 4), (int) Math.pow(2, 13));
			
			/* Create random shuffle data.
			   If no NotMutableAVLException is raised then, each insertion step
			   produced a valid mutable AVL. */
			mavl = builder.safeBuildFrom(Generator.getInstance().randomGeneration(size, min, max).shuffle(), min, max);
			
			assertTrue(Utils.isMutableAVL(mavl, min, max));
			
			sb.append("testSafeBuildMutableAvlAuto | min:");
			sb.append(min);
			sb.append(", max:");
			sb.append(max);
			sb.append(", size:");
			sb.append(size);
			sb.append(" | took ");
			sb.append((System.nanoTime() - innerStart) / 1000000);
			sb.append(" ms.");
			
			Utils.println(sb.toString());
			
			// reset builder
			builder.clear();
		}
		
		Utils.println(nb + " testSafeBuildMutableAvlAuto took " + (System.nanoTime() - start) / 1000000 + " ms.");
	}
	
	/**
	 * Testing the removals.
	 * 
	 * 
	 * If this test does not throw exception,
	 * mutable AVL built is valid.<br>
	 * @throws NotMutableAVLException 
	 */
	@Test
	public void testRemovalMutableAvlAuto() throws NotMutableAVLException {
		long start = System.nanoTime();
		StringBuilder sb = new StringBuilder();
		// number of launches
		int nb = 50;
		
		for(int i = 0; i < nb; ++i) {
			long innerStart = System.nanoTime();
			sb.setLength(0);
			int min = -Utils.irand((int) Math.pow(2, 4), (int) Math.pow(2, 12));
			int max = Utils.irand((int) Math.pow(2, 4), (int) Math.pow(2, 12));
			int size = Utils.irand((int) Math.pow(2, 4), (int) Math.pow(2, 13));
			
			/* Create random shuffle data.
			   If no NotMutableAVLException is raised then, each insertion step
			   produced a valid mutable AVL. */
			mavl = builder.buildFrom(Generator.getInstance().randomGeneration(size, min, max).shuffle());
			
			for(int removeCount = 0; removeCount < size - 2; ++removeCount) {
				assertTrue(Utils.isMutableAVL(mavl, min, max));
				mavl.remove(mavl.getRoot(), Utils.getMutableAVLMinNodeValue(mavl));
				Utils.isMutableAVL(mavl, min, max);
			}
			
			sb.append("testRemovalMutableAvlAuto | min:");
			sb.append(min);
			sb.append(", max:");
			sb.append(max);
			sb.append(", size:");
			sb.append(size);
			sb.append(" | took ");
			sb.append((System.nanoTime() - innerStart) / 1000000);
			sb.append(" ms.");
			
			Utils.println(sb.toString());
			
			// reset builder
			builder.clear();
		}
		
		Utils.println(nb + " testRemovalMutableAvlAuto took " + (System.nanoTime() - start) / 1000000 + " ms.");
	}
	
	/**
	 * Testing insertions and removals.
	 * 
	 * 
	 * If this test does not throw exception,
	 * mutable AVL built is valid.<br>
	 * @throws NotMutableAVLException 
	 */
	@Test
	public void testInsertionsRemovalsMutableAvlAuto() throws NotMutableAVLException {
		long start = System.nanoTime();
		StringBuilder sb = new StringBuilder();
		// number of launches
		int nb = 50;
		
		for(int i = 0; i < nb; ++i) {
			long innerStart = System.nanoTime();
			sb.setLength(0);
			int min = -Utils.irand((int) Math.pow(2, 4), (int) Math.pow(2, 12));
			int max = Utils.irand((int) Math.pow(2, 4), (int) Math.pow(2, 12));
			int size = Utils.irand((int) Math.pow(2, 4), (int) Math.pow(2, 13));
			
			/* Create random shuffle data.
			   If no NotMutableAVLException is raised then, each insertion step
			   produced a valid mutable AVL. */
			mavl = builder.insert(Utils.irand(min, max)).build();
			
			// actual check
			for(int time = 0; time < size; ++time){
				// insertion
				mavl = builder.insert(Utils.irand(min, max)).build();
				// check
				assertTrue(Utils.isMutableAVL(mavl, min, max));
				// removal
				mavl.remove(mavl.getRoot(), Utils.getMutableAVLMinNodeValue(mavl));
				// check
				assertTrue(Utils.isMutableAVL(mavl, min, max));
				// insertion
				mavl = builder.insert(Utils.irand(min, max)).build();
			}
			
			// final check
			assertTrue(Utils.isMutableAVL(mavl, min, max));
			
			sb.append("testInsertionsRemovalsMutableAvlAuto | min:");
			sb.append(min);
			sb.append(", max:");
			sb.append(max);
			sb.append(", size:");
			sb.append(size);
			sb.append(" | took ");
			sb.append((System.nanoTime() - innerStart) / 1000000);
			sb.append(" ms.");
			
			Utils.println(sb.toString());
			
			// reset builder
			builder.clear();
		}
		
		Utils.println(nb + " testInsertionsRemovalsMutableAvlAuto took " + (System.nanoTime() - start) / 1000000 + " ms.");
	}
	
	@Test(expected = IllegalStateException.class)
	public void testInit() {
		mavl = builder.build();
		assertNull(Utils.getMutableAVLMinNode(mavl));
		Utils.getMutableAVLMinNodeValue(mavl); // throw IllegalStateException
		assertNull(mavl.getRoot());
		assertTrue(mavl.isEmpty());
		assertEquals(mavl.toStringPreOrder(), "");
		assertEquals(mavl.toStringWidthOrder(), "");
	}
	
	@Test
	public void testSomeInsertions() {
		mavl = builder.insert(10, 25, 2).build();
		assertEquals(Utils.getMutableAVLMinNode(mavl), new MutableAVLNode(2));
		assertEquals(Utils.getMutableAVLMinNodeValue(mavl), 2);
		assertNotNull(mavl.getRoot());
		assertFalse(mavl.isEmpty());
		assertTrue(isMutableAVLBalanced(mavl));
		assertEquals(mavl.toStringWidthOrder(), "10 2 25");
		assertEquals(mavl.toStringPreOrder(), "10 2 25");
		
		builder.insert(1, 33);
		
		assertEquals(Utils.getMutableAVLMinNode(mavl), new MutableAVLNode(1));
		assertEquals(Utils.getMutableAVLMinNodeValue(mavl), 1);
		assertNotNull(mavl.getRoot());
		assertFalse(mavl.isEmpty());
		assertTrue(isMutableAVLBalanced(mavl));
		
		assertEquals(mavl.toStringWidthOrder(), "10 2 25 1 33");
		assertEquals(mavl.toStringPreOrder(), "10 2 1 25 33");
		
		builder.insert(40, 67, 98);

		assertTrue(isMutableAVLBalanced(mavl));
		assertEquals(mavl.toStringWidthOrder(), "10 2 33 1 25 67 40 98");
		assertEquals(mavl.toStringPreOrder(), "10 2 1 33 25 67 40 98");
	}
	
	@Test
	public void testSomeRemoval(){
		mavl = builder.insert(2, 56, 4, 78).build();
		assertEquals(Utils.getMutableAVLMinNodeValue(mavl), 2);
		assertEquals(mavl.toStringWidthOrder(), "4 2 56 78");
		assertEquals(mavl.toStringPreOrder(), "4 2 56 78");
		
		builder.remove(2);
		assertEquals(Utils.getMutableAVLMinNodeValue(mavl), 4);
		assertEquals(mavl.toStringWidthOrder(), "56 4 78");
		assertEquals(mavl.toStringPreOrder(), "56 4 78");
		
		builder.insert(1, 99);
		assertEquals(mavl.toStringWidthOrder(), "56 4 78 1 99");
		assertEquals(mavl.toStringPreOrder(), "56 4 1 78 99");
		
		builder.remove(56);
		assertEquals(mavl.toStringWidthOrder(), "78 4 99 1");
		assertEquals(mavl.toStringPreOrder(), "78 4 1 99");
	}
	
	@Test
	public void testSearch() {
		mavl = builder.insert(2, 8, 65, 4, 876).build();
		assertEquals(mavl.search(4).getValue(), 4);
		assertEquals(mavl.search(500).getValue(), 876);
	}
	
}
