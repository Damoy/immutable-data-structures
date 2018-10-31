package com.dzoum.ids.app;

import com.dzoum.ids.core.avl.immutable.ImmutableAVL;
import com.dzoum.ids.utils.Utils;
import com.dzoum.ids.core.avl.immutable.IImmutableAVL;

public class AppImmutable {

	public static void main(String[] args) {
		IImmutableAVL avl = new ImmutableAVL(9, null, null)
				.add(5)
				.add(10)
				.add(0)
				.add(6)
				.add(11)
				.add(-1)
				.add(1)
				.add(2);
		
		avl = avl.remove(2);
		Utils.println(avl);
	}
}
