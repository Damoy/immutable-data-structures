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
	
	@Test
	public void testBuiltMutableAVL() {
		long start = System.nanoTime();
		StringBuilder sb = new StringBuilder();
		int nb = 50;
		
		for(int i = 0; i < nb; ++i) {
			long innerStart = System.nanoTime();
			sb.setLength(0);
			int min = -Utils.irand((int) Math.pow(2, 8), (int) Math.pow(2, 16));
			int max = Utils.irand((int) Math.pow(2, 8), (int) Math.pow(2, 16));
			int size = Utils.irand((int) Math.pow(2, 8), (int) Math.pow(2, 20));
			
			Utils.println("s: " + size + ", min: " + min + ", max: " + max);
			
			mavl = builder.buildFrom(Generator.getInstance().randomGeneration(size, min, max));
			builder.clear();
			
			assertTrue(Utils.isValidMutableAVL(mavl, min, max));

			sb.append("testBuildMutableAVL | min:");
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
		
		Utils.println(nb + " testBuiltMutableAVL took " + (System.nanoTime() - start) / 1000000 + " ms.");
	}
	
	/**
	 * If this test does not throw exception,
	 * mutable AVL built is valid.<br>
	 * 
	 * @throws NotMutableAVLException 
	 */
	@Test
	public void testSafeBuildMutableAvl() throws NotMutableAVLException {
		long start = System.nanoTime();
		int nb = 50;
		
		for(int i = 0; i < nb; ++i) {
			int min = -Utils.irand((int) Math.pow(2, 4), (int) Math.pow(2, 12));
			int max = Utils.irand((int) Math.pow(2, 4), (int) Math.pow(2, 12));
			int size = Utils.irand((int) Math.pow(2, 4), (int) Math.pow(2, 13));
			
			/* Create random shuffle data.
			   If no NotMutableAVLException is raised then, each insertion step
			   produced a valid mutable AVL. */
			mavl = builder.safeBuildFrom(Generator.getInstance().randomGeneration(size, min, max).shuffle(), min, max);
			
			assertTrue(Utils.isValidMutableAVL(mavl, min, max));
			// reset builder
			builder.clear();
		}
		
		Utils.println(nb + " testSafeBuildMutableAvl took " + (System.nanoTime() - start) / 1000000 + " ms.");
	}
	
	@Test(expected = IllegalStateException.class)
	public void testInit() {
		mavl = builder.build();
		assertNull(Utils.getMAVLMinNode(mavl));
		Utils.getMAVLMinNodeValue(mavl); // throw IllegalStateException
		assertNull(mavl.getRoot());
		assertTrue(mavl.isEmpty());
		assertEquals(mavl.toStringPreOrder(), "");
		assertEquals(mavl.toStringWidthOrder(), "");
	}
	
	@Test
	public void testInsertion() {
		mavl = builder.insert(10, 25, 2).build();
		assertEquals(Utils.getMAVLMinNode(mavl), new MutableAVLNode(2));
		assertEquals(Utils.getMAVLMinNodeValue(mavl), 2);
		assertNotNull(mavl.getRoot());
		assertFalse(mavl.isEmpty());
		assertTrue(isMAVLBalanced(mavl));
		assertEquals(mavl.toStringWidthOrder(), "10 2 25");
		assertEquals(mavl.toStringPreOrder(), "10 2 25");
		
		builder.insert(1, 33);
		
		assertEquals(Utils.getMAVLMinNode(mavl), new MutableAVLNode(1));
		assertEquals(Utils.getMAVLMinNodeValue(mavl), 1);
		assertNotNull(mavl.getRoot());
		assertFalse(mavl.isEmpty());
		assertTrue(isMAVLBalanced(mavl));
		
		assertEquals(mavl.toStringWidthOrder(), "10 2 25 1 33");
		assertEquals(mavl.toStringPreOrder(), "10 2 1 25 33");
		
		builder.insert(40, 67, 98);

		assertTrue(isMAVLBalanced(mavl));
		assertEquals(mavl.toStringWidthOrder(), "10 2 33 1 25 67 40 98");
		assertEquals(mavl.toStringPreOrder(), "10 2 1 33 25 67 40 98");
	}
	
	@Test
	public void testInsertion2(){
		mavl = builder.insert(10, 5).build();
		assertEquals(mavl.toStringWidthOrder(), "10 5");
		assertEquals(mavl.toStringPreOrder(), "10 5");
		
		builder.insert(6);
		assertEquals(mavl.toStringWidthOrder(), "6 5 10");
		assertEquals(mavl.toStringPreOrder(), "6 5 10");
	}
	
	@Test
	public void testRemove(){
		mavl = builder.insert(2, 56, 4, 78).build();
		assertEquals(Utils.getMAVLMinNodeValue(mavl), 2);
		assertEquals(mavl.toStringWidthOrder(), "4 2 56 78");
		assertEquals(mavl.toStringPreOrder(), "4 2 56 78");
		
		builder.remove(2);
		assertEquals(Utils.getMAVLMinNodeValue(mavl), 4);
		assertEquals(mavl.toStringWidthOrder(), "56 4 78");
		assertEquals(mavl.toStringPreOrder(), "56 4 78");
		
		builder.insert(1, 99);
		assertEquals(mavl.toStringWidthOrder(), "56 4 78 1 99");
		assertEquals(mavl.toStringPreOrder(), "56 4 1 78 99");
		
		builder.remove(56);
		assertEquals(mavl.toStringWidthOrder(), "78 4 99 1");
		assertEquals(mavl.toStringPreOrder(), "78 4 1 99");
	}
	
}
