package com.dzoum.ids.core.mutable;

import com.dzoum.ids.utils.Utils;

public class MutableAVLBuilder implements IMutableAVLBuilder {

	private IMutableAVL mavl;
	private IAVLNode root;
	
	public MutableAVLBuilder() {
		
	}
	
	@Override
	public IMutableAVLBuilder insert(int key) {
		if(root == null) {
			root = new AVLNode(key);
			mavl = new MutableAVL(root);
		}
		
		mavl.setRoot(mavl.insert(root, key));
		root = mavl.getRoot();
		return this;
	}

	@Override
	public IMutableAVLBuilder remove(int key) {
		if(mavl == null || mavl.isEmpty()) {
			Utils.println("AVL builder > cannot remove from empty AVL");
			return this;
		}
		
		mavl.setRoot(mavl.remove(mavl.getRoot(), key));
		root = mavl.getRoot();
		return this;
	}
	
	@Override
	public IMutableAVL build() {
		return mavl;
	}
	
	@Override
	public IMutableAVLBuilder clear() {
		mavl = null;
		root = null;
		return this;
	}

	@Override
	public IMutableAVLBuilder insert(int... keys) {
		for(Integer key : keys)
			insert(key);
		return this;
	}

	@Override
	public IMutableAVLBuilder remove(int... keys) {
		for(Integer key : keys)
			remove(key);
		return this;
	}

}
