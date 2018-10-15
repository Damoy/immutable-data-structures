package com.dzoum.ids.core.mutable;

public interface IMutableAVLBuilder {
	
	public IMutableAVLBuilder insert(int key);
	public IMutableAVLBuilder insert(int... keys);
	public IMutableAVLBuilder remove(int key);
	public IMutableAVLBuilder remove(int... keys);
	public IMutableAVLBuilder clear();
	public IMutableAVL build();
	
}
