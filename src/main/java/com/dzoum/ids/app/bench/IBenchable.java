package com.dzoum.ids.app.bench;

import com.dzoum.ids.model.IData;

public interface IBenchable {

	/**
	 * Alias for benchmark structure creation.
	 */
	public void benchCreate(IData dataset, int creationSize);
	
	/**
	 * Alias for benchmark structure minimum insertions.
	 */
	public void benchInsertMin(int times);
	
	/**
	 * Alias for benchmark structure minimum removals.
	 */
	public void benchRemoveMin(int times);
	
	/**
	 * Alias for benchmark structure insertions.
	 */
	public void benchInsertions(int times);
	
	/**
	 * Alias for benchmark structure removals.
	 */
	public void benchRemovals(int times);
	
	/**
	 * Tool to prepare insertions or removals.
	 */
	public void setupForBench(IData dataset, int creationSize);
	
}
