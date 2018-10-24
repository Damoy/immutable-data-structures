package com.dzoum.ids.core.avl.immutable;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


/**
 * Testing the immutable AVL
 */
@RunWith(JUnit4.class)
public class TestImmutableAVL {

	@Test
	public void testInsertion() {
		ImmutableAVL root = new ImmutableAVL(5);
		System.out.println(root.add(56));
	}
}
