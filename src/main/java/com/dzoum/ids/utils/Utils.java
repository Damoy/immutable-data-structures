package com.dzoum.ids.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public final class Utils {

	private final static Random SEED = new Random();
	private final static Map<String, Integer> MEMORY = new HashMap<>();
	
	private Utils(){}
	
	/**
	 * Assert a condition.
	 * 
	 * @param cond the condition to check
	 * @param err message to throw
	 * @param eclazz exception class to use
	 */
	public static void assertTrue(boolean cond, String err, Class<? extends Exception> eclazz) {
		if(!cond) {
			try {
				Constructor<? extends Exception> ce = eclazz.getConstructor(String.class);
				ce.newInstance(err);
			} catch (NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} 
		}
	}
	
	public static <T> void println(T o){
		System.out.println(o.toString());
	}
	
	public static <T> void print(T o){
		System.out.print(o.toString());
	}
	
	/**
	 * Get random integer in range [min,max].
	 */
	public static int irand(int min, int max){
		return SEED.nextInt((max - min) + 1) + min;
	}
	
	/**
	 * Get log2 of inquired value.
	 */
	public static double log2(double value){
		return Math.log10(value) / Math.log10(2);
	}
	
	/**
	 * Write some content to a file.
	 * Overrides the file if it exists.
	 * 
	 * @param title the file title 
	 * @param content the text to write
	 */
	public static void writeToFile(String title, String content){
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
