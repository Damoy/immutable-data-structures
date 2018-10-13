package com.dzoum.ids.model;

import com.dzoum.ids.model.IData;

/**
 * Our data model.
 * Algorithms should only use this model when processing data.
 */
public interface IData {

	/**
	 * Get element given an index.
	 */
	public int get(int index);
	
	/**
	 * Get the data length.
	 */
	public int getSize();
	
	/**
	 * Set element given the index.
	 */
	public IData set(int index, int value);
	
	/**
	 * Clone of the object.
	 * Attributes references can change.
	 */
	public IData clone();
	
	/**
	 * Reverse the data content.
	 */
	public IData reverse();
	
	/**
	 * Increased the data length.
	 */
	public IData grow(int of);
	
}