package com.dzoum.ids.core.avl.immutable;

import com.dzoum.ids.model.IData;
import com.dzoum.ids.utils.Utils.Pair;

public class ImmutableNullAVL implements IImmutableAVL {

	public ImmutableNullAVL() {}

	@Override
	public IImmutableAVL add(int element) {
		return this;
	}

	@Override
	public Pair<Integer, IImmutableAVL> pollFirst() {
		return new Pair<>(0, this);
	}

	@Override
	public IImmutableAVL remove(int element) {
		return this;
	}

	@Override
	public int index(int element) {
		return 0;
	}

	@Override
	public int subSize(int fromElement, int toElement) {
		return 0;
	}

	@Override
	public String toString() {
		return "$\n";
	}

	@Override
	public int getElement() {
		return 0;
	}

	@Override
	public IImmutableAVL getLeftChild() {
		return null;
	}

	@Override
	public IImmutableAVL getRightChild() {
		return null;
	}

	@Override
	public int getHeight() {
		return 0;
	}

	@Override
	public int getSize() {
		return 0;
	}

	@Override
	public void benchCreate(IData dataset, int creationSize) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void benchInsertMin(int times) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void benchRemoveMin(int times) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void benchInsertions(IData dataset, int times) {
		return;
	}

	@Override
	public void benchRemovals(IData dataset, int times) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setupForBench(IData dataset, int creationSize) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void benchSearch(IData dataset, int times) {
		// TODO Auto-generated method stub
		
	}

}
