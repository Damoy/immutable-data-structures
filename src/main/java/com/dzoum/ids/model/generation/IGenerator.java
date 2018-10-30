package com.dzoum.ids.model.generation;

import com.dzoum.ids.model.IData;

/**
 * Data generation tool.
 */
public interface IGenerator {
	
	/**
	 * Generates random data.
	 * @param size of data
	 * @param min value
	 * @param max value
	 */
	public IData randomGeneration(int size, int min, int max);
	
	public IData randomSetGeneration(int size, int min, int max);
}
