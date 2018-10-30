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

	@Override
	public IData randomSetGeneration(int size, int min, int max) {
		IData data = Data.of(size);
		
		for(int i = 0; i < size; ++i) {
			int value = Utils.irand(min, max);
			
			for(int j = 0; j < i; ++j) {
				if(data.get(j) == value) {
					int value2 = Utils.irand(min, max);
					while(value2 == value) {
						value2 = Utils.irand(min, max);
					}
					
					data.set(i, value2);
					break;
				}
			}
			
			data.set(i, value);
		}
		
		return data;
	}

}