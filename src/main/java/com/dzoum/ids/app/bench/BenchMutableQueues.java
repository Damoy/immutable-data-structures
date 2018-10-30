package com.dzoum.ids.app.bench;

import com.dzoum.ids.core.avl.mutable.MutableAVL;
import com.dzoum.ids.core.avl.mutable.MutableAVLBuilder;
import com.dzoum.ids.core.heap.mutable.array.MinMutableArrayHeap;
import com.dzoum.ids.core.redblacktree.mutable.MutableRedBlackTree;
import com.dzoum.ids.model.IData;
import com.dzoum.ids.model.generation.Generator;
import com.dzoum.ids.utils.Utils;

/**
 * Comparaisons entre tas et arbres de recherches pour
 * utilisation en tant que file de priorité
 * (création à partir d'un tableau de valeur, insertion et supression du minimum)
 */
public class BenchMutableQueues {

	public static void main(String[] args) {
		// launchArrayHeapVSAVLBench1();
		// launchAVLvsRedBench();
		launchAVLvsRedSearchsBench();
	}
	
	public static void launchArrayHeapVSAVLBench1() {
		// creation bench
		String bench = versusBench(BENCH_HINT.CREATION, BENCHABLE_STRUCTURE.HEAP,
				BENCHABLE_STRUCTURE.AVL, BENCHABLE_STRUCTURE.RED_BLACK_TREE, ",");
		Utils.println(bench);
		Utils.writeToFile("arrayHeapVSAVLvsRedCreationBench1.csv", ".csv", bench);
		
		// min insertion bench
		bench = versusBench(BENCH_HINT.INSERTION_MIN, BENCHABLE_STRUCTURE.HEAP,
				BENCHABLE_STRUCTURE.AVL, BENCHABLE_STRUCTURE.RED_BLACK_TREE, ",");
		Utils.println(bench);
		Utils.writeToFile("arrayHeapVSAVLvsRedMinInsertionBench1.csv", ".csv", bench);
		
		// min deletion bench
		bench = versusBench(BENCH_HINT.DELETION_MIN, BENCHABLE_STRUCTURE.HEAP,
				BENCHABLE_STRUCTURE.AVL, BENCHABLE_STRUCTURE.RED_BLACK_TREE, ",");
		Utils.println(bench);
		Utils.writeToFile("arrayHeapVSAVLvsRedMinDeletionBench1.csv", ".csv", bench);
	}
	
	public static void launchAVLvsRedBench() {
		// creation bench
		String bench = versusBench(BENCH_HINT.INSERTIONS, BENCHABLE_STRUCTURE.AVL,
				BENCHABLE_STRUCTURE.RED_BLACK_TREE, ",");
		Utils.println(bench);
		Utils.writeToFile("AVLvsRedInsertionsBench1.csv", ".csv", bench);
		
		// min insertion bench
		bench = versusBench(BENCH_HINT.DELETIONS, BENCHABLE_STRUCTURE.AVL,
				BENCHABLE_STRUCTURE.RED_BLACK_TREE, ",");
		Utils.println(bench);
		Utils.writeToFile("AVLvsRedDeletionsBench3.csv", ".csv", bench);
	}
	
	public static void launchAVLvsRedSearchsBench() {
		String bench = versusBench(BENCH_HINT.SEARCHS, BENCHABLE_STRUCTURE.AVL,
				BENCHABLE_STRUCTURE.RED_BLACK_TREE, ",");
		Utils.println(bench);
		Utils.writeToFile("AVLvsRedSearchsBench2.csv", ".csv", bench);
	}
	
	private static String versusBench(BENCH_HINT hint, BENCHABLE_STRUCTURE bs1, BENCHABLE_STRUCTURE bs2, String separator) {
		StringBuilder avlCSVBuilder = new StringBuilder();
		StringBuilder redCSVBuilder = new StringBuilder();
		
		benchStructureOn(0, avlCSVBuilder, separator, bs1, hint, 4, 20, -1000, 1000, 50);
		benchStructureOn(1, redCSVBuilder, separator, bs2, hint, 4, 20, -1000, 1000, 50);
		
		String[] splitHeapContent = avlCSVBuilder.toString().split("\n");
		String[] splitAvlContent = redCSVBuilder.toString().split("\n");
		
		if(splitHeapContent.length != splitAvlContent.length)
			throw new IllegalStateException("Internal error while processing benchmark occured.");
		
		avlCSVBuilder.setLength(0);
		
		for(int i = 0; i < splitAvlContent.length; ++i) {
			avlCSVBuilder.append(splitHeapContent[i]);
			avlCSVBuilder.append(separator);
			avlCSVBuilder.append(splitAvlContent[i]);
			avlCSVBuilder.append("\n");
		}
		
		return avlCSVBuilder.toString();
	}
	
	private static String versusBench(BENCH_HINT hint, BENCHABLE_STRUCTURE bs1, BENCHABLE_STRUCTURE bs2, BENCHABLE_STRUCTURE bs3, String separator) {
		StringBuilder heapCSVBuilder = new StringBuilder();
		StringBuilder avlCSVBuilder = new StringBuilder();
		StringBuilder redCSVBuilder = new StringBuilder();
		
		benchStructureOn(0, heapCSVBuilder, separator, bs1, hint, 4, 20, -1000, 1000, 50);
		benchStructureOn(1, avlCSVBuilder, separator, bs2, hint, 4, 20, -1000, 1000, 50);
		benchStructureOn(2, redCSVBuilder, separator, bs3, hint, 4, 20, -1000, 1000, 50);
		
		String[] splitHeapContent = heapCSVBuilder.toString().split("\n");
		String[] splitAvlContent = avlCSVBuilder.toString().split("\n");
		String[] splitRedContent = redCSVBuilder.toString().split("\n");
		
		if(splitHeapContent.length != splitAvlContent.length || splitAvlContent.length != splitRedContent.length
				|| splitRedContent.length != splitHeapContent.length)
			throw new IllegalStateException("Internal error while processing benchmark occured.");
		
		heapCSVBuilder.setLength(0);
		
		for(int i = 0; i < splitAvlContent.length; ++i) {
			heapCSVBuilder.append(splitHeapContent[i]);
			heapCSVBuilder.append(separator);
			heapCSVBuilder.append(splitAvlContent[i]);
			heapCSVBuilder.append(separator);
			heapCSVBuilder.append(splitRedContent[i]);
			heapCSVBuilder.append("\n");
		}
		
		return heapCSVBuilder.toString();
	}
	
	private static void benchStructureOn(int index, StringBuilder csvBuilder, String separator,
			BENCHABLE_STRUCTURE bs, BENCH_HINT hint, int minSize, int maxSize,
			int min, int max, int times) {
		for(int sizePow = minSize; sizePow <= maxSize; ++sizePow) {
			startBench(csvBuilder, separator, hint, bs, sizePow, min, max, times, index != 0);
		}
	}
	
	private static void startBench(StringBuilder csvBuilder, String separator,
			BENCH_HINT hint, BENCHABLE_STRUCTURE bs,
			int size, int min, int max, int times, boolean follower) {
		int realSize = (int) Math.pow(2, size);
		IBenchable benchable = BENCHABLE_STRUCTURE.get(bs, realSize);
		
		long processTime = 0L;
		for(int time = 0; time < times; ++time) {
			IData dataset = Generator.getInstance().randomGeneration(realSize, min, max);
			long innerStart = 0L;
			
			if(hint == BENCH_HINT.CREATION) {
				innerStart = System.nanoTime();
				// creation
				benchable.benchCreate(dataset, dataset.getSize());
			}
		
			if(hint == BENCH_HINT.INSERTION_MIN || hint == BENCH_HINT.DELETION_MIN) {
				if(hint == BENCH_HINT.INSERTION_MIN) {
					// min insertion
					if(bs == BENCHABLE_STRUCTURE.AVL) {
						MutableAVL avl = (MutableAVL) benchable;
						avl.setRoot(avl.insert(avl.getRoot(), dataset.get(dataset.getSize() - 1)));
						innerStart = System.nanoTime();
						// benchable.benchInsertMin(dataset.getSize() - 1);
						avl.benchInsertMin(dataset.getSize());
					} else if(bs == BENCHABLE_STRUCTURE.RED_BLACK_TREE){
						MutableRedBlackTree tree = (MutableRedBlackTree) benchable;
						tree.insert(dataset.get(dataset.getSize() - 1));
						innerStart = System.nanoTime();
						tree.benchInsertMin(dataset.getSize() - 1);
					} else {
						innerStart = System.nanoTime();
						benchable.benchInsertMin(dataset.getSize());
					}
				} else {
					// min deletion
					// setup state for insertion or deletion
					benchable.setupForBench(dataset, dataset.getSize());
					innerStart = System.nanoTime();
					benchable.benchRemoveMin(dataset.getSize());
				}
			}
			
			if(hint == BENCH_HINT.INSERTIONS) {
				dataset = Generator.getInstance().randomSetGeneration(realSize, min, max);
				if(bs == BENCHABLE_STRUCTURE.AVL) {
					MutableAVL avl = (MutableAVL) benchable;
					avl.setRoot(avl.insert(avl.getRoot(), dataset.get(dataset.getSize() - 1)));
					innerStart = System.nanoTime();
					avl.benchInsertions(dataset, dataset.getSize() - 1);
				} else if(bs == BENCHABLE_STRUCTURE.RED_BLACK_TREE) {
					MutableRedBlackTree tree = (MutableRedBlackTree) benchable;
					tree.insert(dataset.get(dataset.getSize() - 1));
					innerStart = System.nanoTime();
					tree.benchInsertions(dataset, dataset.getSize() - 1);
				}
			}
			
			if(hint == BENCH_HINT.DELETIONS){
				if(bs == BENCHABLE_STRUCTURE.AVL) {
					MutableAVL avl = (MutableAVL) benchable;
					avl.setupForBench(dataset, dataset.getSize());
					innerStart = System.nanoTime();
					avl.benchRemovals(dataset, dataset.getSize());
				} else if(bs == BENCHABLE_STRUCTURE.RED_BLACK_TREE) {
					MutableRedBlackTree tree = (MutableRedBlackTree) benchable;
					tree.setupForBench(dataset, dataset.getSize());
					innerStart = System.nanoTime();
					tree.benchRemovals(dataset, dataset.getSize());
				}
			}
			
			if(hint == BENCH_HINT.SEARCHS) {
				dataset = Generator.getInstance().randomSetGeneration(realSize, min, max);
				if(bs == BENCHABLE_STRUCTURE.AVL) {
					MutableAVL avl = (MutableAVL) benchable;
					avl.setupForBench(dataset, dataset.getSize());
					innerStart = System.nanoTime();
					avl.benchSearch(dataset, dataset.getSize());
				} else if(bs == BENCHABLE_STRUCTURE.RED_BLACK_TREE) {
					MutableRedBlackTree tree = (MutableRedBlackTree) benchable;
					tree.setupForBench(dataset, dataset.getSize());
					innerStart = System.nanoTime();
					tree.benchSearch(dataset, dataset.getSize());
				}
			}
			
			processTime += System.nanoTime() - innerStart;
			if(innerStart == 0L) throw new IllegalStateException();
		}
		
		if(!follower) {
			csvBuilder.append(size);
			csvBuilder.append(separator);
		}
		
		csvBuilder.append(Utils.log2(processTime / times));
		csvBuilder.append("\n");
	}
	
	private static enum BENCH_HINT {
		CREATION, INSERTION_MIN, DELETION_MIN, INSERTIONS, DELETIONS, SEARCHS;
	}
	
	private static enum BENCHABLE_STRUCTURE { 
		HEAP, AVL, RED_BLACK_TREE;
		
		public static IBenchable get(BENCHABLE_STRUCTURE bs, int capacity) {
			switch (bs) {
			case HEAP:
				return new MinMutableArrayHeap(capacity);
			case AVL:
				return (IBenchable) new MutableAVLBuilder().build();
			case RED_BLACK_TREE:
				return new MutableRedBlackTree();
			default:
				throw new IllegalStateException("Unknown structure.");
			}
		}
	}
	
}
