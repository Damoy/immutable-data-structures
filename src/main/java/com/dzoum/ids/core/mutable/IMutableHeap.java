package com.dzoum.ids.core.mutable;

/**
 * The mutable heaps interface.
 */
public interface IMutableHeap {

	/**
	 * Inserts an element into the heap.
	 * 
	 * @param elt to insert
	 * @return insertion success
	 */
	public boolean insert(int elt);
	
	/**
	 * Removes the heap lowest element.
	 * 
	 * @return The removed element if the heap current size
	 * 			is greater than zero, Integer.MIN_VALUE otherwise.
	 */
	public int remove();
	
	/**
	 * Is the heap empty ?
	 */
	public boolean isEmpty();
	
	/**
	 * Get the current size
	 */
	public int getCurrentSize();
	
	/**
	 * Get the heap capacity.
	 */
	public int getCapacity();
	
	/**
	 * Get the heap min value.
	 */
	public int getMin();
	
	/**
	 * Outputs to console heap String representation.
	 */
	public void display();
	
	/**
	 * Gets the heap current representation. 
	 */
	public String toString();
	
}
