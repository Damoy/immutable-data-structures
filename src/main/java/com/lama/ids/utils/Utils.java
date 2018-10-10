package com.lama.ids.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public final class Utils {

	private final static Random SEED = new Random();
	private final static Map<String, Integer> MEMORY = new HashMap<>();
	
	private Utils(){}
	
	public static <T> void println(T o){
		System.out.println(o.toString());
	}
	
	public static <T> void print(T o){
		System.out.print(o.toString());
	}
	
	public static int irand(int min, int max){
		return SEED.nextInt((max - min) + 1) + min;
	}
	
	public static long log2(long value){
		return (int) (Math.log10(value) / Math.log10(2));
	}
	
	public static void toFile(String title, String content){
		title = filterTitle(title);
		String ftitle = getOutputFilePath();
		
		if(MEMORY.containsKey(title)){
			int lastId = MEMORY.get(title);
			ftitle += title + (++lastId);
			MEMORY.put(ftitle, lastId);
		} else {
			MEMORY.put(title, 0);
			ftitle += title;
		}
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(ftitle)));
			bw.write(content);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static String filterTitle(String title){
		return title.replaceAll("[ *$^!;:,=)('\"&]", "_");
	}
	
	private static String getOutputFilePath(){
		return "./src/main/resources/output/";
	}
	
}
