package com.dzoum.ids.model.generation;

import com.dzoum.ids.model.Data;
import com.dzoum.ids.model.IData;
import com.dzoum.ids.utils.Utils;

/**
 * {@link IGenerator}
 */
public class Generator implements IGenerator {

	private final static IGenerator INSTANCE = new Generator();

	private Generator() {
	}

	public static IGenerator getInstance() {
		return INSTANCE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IData randomGeneration(int size, int min, int max) {
		IData data = Data.of(size);

		for (int i = 0; i < size; ++i)
			data.set(i, Utils.irand(min, max));

		return data;
	}

}