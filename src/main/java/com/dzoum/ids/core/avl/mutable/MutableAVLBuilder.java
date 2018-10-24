package com.dzoum.ids.core.avl.mutable;

import com.dzoum.ids.model.IData;
import com.dzoum.ids.utils.Utils;

/**
 * {@link IMutableAVLBuilder} 
 */
public class MutableAVLBuilder implements IMutableAVLBuilder {

	private IMutableAVL mavl;
	private IMutableAVLNode root;
	
	public MutableAVLBuilder() {
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IMutableAVLBuilder insert(int key) {
		if(mavl == null) {
			root = new MutableAVLNode(key);
			mavl = new MutableAVL(root);
		} else if(mavl.getRoot() == null) {
			root = new MutableAVLNode(key);
			mavl.setRoot(root);
		}
		
		mavl.setRoot(mavl.insert(root, key));
		root = mavl.getRoot();
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
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
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IMutableAVL build() {
		if(mavl == null) mavl = new MutableAVL(null);
		return mavl;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IMutableAVLBuilder clear() {
		mavl = null;
		root = null;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IMutableAVLBuilder insert(int... keys) {
		for(Integer key : keys)
			insert(key);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IMutableAVLBuilder remove(int... keys) {
		for(Integer key : keys)
			remove(key);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IMutableAVL buildFrom(IData data) {
		insert(data.get());
		return mavl;
	}
	
	/**
	 * {@inheritDoc}
	 * @throws NotMutableAVLException 
	 */
	@Override
	public IMutableAVL safeBuildFrom(IData data, int dataMinValue, int dataMaxValue) throws NotMutableAVLException {
		for(int i = 0; i < data.getSize(); ++i) {
			insert(data.get(i)).build();
			
			if(!Utils.isValidMutableAVL(mavl, dataMinValue, dataMaxValue))
				throw new NotMutableAVLException();
		}
		
		return mavl;
	}

}
