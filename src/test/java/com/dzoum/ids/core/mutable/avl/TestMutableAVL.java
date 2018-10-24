package com.dzoum.ids.core.mutable.avl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.dzoum.ids.model.generation.Generator;

import static com.dzoum.ids.core.mutable.avl.AVLUtils.*;

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
	
	@Test(expected = IllegalStateException.class)
	public void testInit() {
		mavl = builder.build();
		assertNull(AVLUtils.getMAVLMinNode(mavl));
		AVLUtils.getMAVLMinNodeValue(mavl); // throw IllegalStateException
		assertNull(mavl.getRoot());
		assertTrue(mavl.isEmpty());
		assertEquals(mavl.toStringPreOrder(), "");
		assertEquals(mavl.toStringWidthOrder(), "");
	}
	
	@Test
	public void testInsertion() {
		mavl = builder.insert(10, 25, 2).build();
		assertEquals(AVLUtils.getMAVLMinNode(mavl), new MutableAVLNode(2));
		assertEquals(AVLUtils.getMAVLMinNodeValue(mavl), 2);
		assertNotNull(mavl.getRoot());
		assertFalse(mavl.isEmpty());
		assertTrue(isBalanced(mavl));
		assertEquals(mavl.toStringWidthOrder(), "10 2 25");
		assertEquals(mavl.toStringPreOrder(), "10 2 25");
		
		builder.insert(1, 33);
		
		assertEquals(AVLUtils.getMAVLMinNode(mavl), new MutableAVLNode(1));
		assertEquals(AVLUtils.getMAVLMinNodeValue(mavl), 1);
		assertNotNull(mavl.getRoot());
		assertFalse(mavl.isEmpty());
		assertTrue(isBalanced(mavl));
		
		assertEquals(mavl.toStringWidthOrder(), "10 2 25 1 33");
		assertEquals(mavl.toStringPreOrder(), "10 2 1 25 33");
		
		builder.insert(40, 67, 98);

		assertTrue(isBalanced(mavl));
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
		assertEquals(AVLUtils.getMAVLMinNodeValue(mavl), 2);
		assertEquals(mavl.toStringWidthOrder(), "4 2 56 78");
		assertEquals(mavl.toStringPreOrder(), "4 2 56 78");
		
		builder.remove(2);
		assertEquals(AVLUtils.getMAVLMinNodeValue(mavl), 4);
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
	public void testInsertionsAuto1() {
		long start = System.nanoTime();
		int min = -100;
		int max = 100;
		int size = 1000;
		mavl = builder.buildFrom(Generator.getInstance().randomGeneration(size, min, max));
		assertTrue(AVLUtils.isValidMutableAVL(mavl, min, max));
		System.out.println("Automatic insertions test 1 took " + (System.nanoTime() - start) / 1000000 + " ms.");
	}
	
	@Test
	public void testInsertionsAuto2() {
		long start = System.nanoTime();
		int min = -100000;
		int max = 100000;
		int size = 1000000;
		mavl = builder.buildFrom(Generator.getInstance().randomGeneration(size, min, max));
		assertTrue(AVLUtils.isValidMutableAVL(mavl, min, max));
		System.out.println("Automatic insertions test 2 took " + (System.nanoTime() - start) / 1000000 + " ms.");
	}
	
}
