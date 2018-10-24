package com.dzoum.ids.core.avl.immutable;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


/**
 * Testing the immutable AVL
 */
@RunWith(JUnit4.class)
public class TestImmutableAVL {

	@Test
	@Ignore
	public void testInsertion() {
		IImmutableAVL left = new ImmutableAVL(6);
		IImmutableAVL right = new ImmutableAVL(56);
		IImmutableAVL root = new ImmutableAVL(5, left, right);
		root = root.add(43);
		System.out.println(root.toString());
		System.out.println(left.toString());
		System.out.println(right.toString());
	}
}
