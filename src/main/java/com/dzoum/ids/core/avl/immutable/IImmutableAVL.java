package com.dzoum.ids.core.avl.immutable;

import com.dzoum.ids.utils.Utils.Pair;

public interface IImmutableAVL {

	public IImmutableAVL add(int element);
	public IImmutableAVL remove(int element);
	public Pair<Integer, IImmutableAVL> pollFirst();
	public int index(int element);
	public int subSize(int fromElement, int toElement);
	public String toString();
	
	public int getElement();
	public IImmutableAVL getLeftChild();
	public IImmutableAVL getRightChild();
	public int getHeight();
	public int getSize();
	
}
