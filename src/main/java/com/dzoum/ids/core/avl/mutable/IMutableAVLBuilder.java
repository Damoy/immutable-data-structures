package com.dzoum.ids.core.avl.mutable;

import com.dzoum.ids.model.IData;

/**
 * Mutable AVL structure builder.
 * 
 * Ease Mavl construction.
 */
public interface IMutableAVLBuilder {
	
	/**
	 * Insert a new element.
	 * 
	 * @param key the value of the element to insert
	 */
	public IMutableAVLBuilder insert(int key);
	
	/**
	 * Insert new elements.
	 * 
	 * @param keys the values of the elements to insert
	 */
	public IMutableAVLBuilder insert(int... keys);
	
	/**
	 * Remove an element.
	 * 
	 * @param key the value of the element to remove
	 */
	public IMutableAVLBuilder remove(int key);
	
	/**
	 * Remove elements.
	 * 
	 * @param keys the values of the elements to remove
	 * @return
	 */
	public IMutableAVLBuilder remove(int... keys);
	
	/**
	 * Clear the builder.
	 */
	public IMutableAVLBuilder clear();
	
	/**
	 * Get a mutable AVL.
	 */
	public IMutableAVL build();
	
	/**
	 * Build a mutable AVL given data.
	 */
	public IMutableAVL buildFrom(IData data);
	
	/**
	 * Build a mutable AVL given data.<br>
	 * Checks that each step production is still a mutable AVL.<br>
	 * If not, throws a NotMutableAVLException.<br>
	 */
	public IMutableAVL safeBuildFrom(IData data, int dataMinValue, int dataMaxValue) throws NotMutableAVLException;
}
