package com.dzoum.ids.core.commons;

public interface INode {
	
	/**
	 * Get the node value
	 */
	public int getValue();
	
	/**
	 * Get the node height.
	 */
	public int getHeight();

	/**
	 * Set the node value.
	 */
	public void setValue(int value);
	
	/**
	 * Set the node height.
	 */
	public void setHeight(int height);
	
}
