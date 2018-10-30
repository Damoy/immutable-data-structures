package com.dzoum.ids.core.heap.mutable.array;


import com.dzoum.ids.app.bench.IBenchable;
import com.dzoum.ids.core.avl.mutable.MutableAVLNode;
import com.dzoum.ids.model.Data;
import com.dzoum.ids.model.IData;
import com.dzoum.ids.utils.Utils;

/**
 * Min mutable classic heap.
 */
public class MinMutableArrayHeap implements IMutableArrayHeap, IBenchable {
	
	private IData data;

	public MinMutableArrayHeap(int capacity) {
		// one more that contain size
		this.data = Data.of(capacity + 1);
		// data.get(0) is the current size
		this.data.set(0, 0);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean insert(int elt) {
		if(data.get(0) + 1 == data.getSize()) {
			return false;
		}
		
		// increase current size
		data.set(0, data.get(0) + 1);
		// increase element
		data.set(data.get(0), elt);
		// percolate up
		percolateUp(data.get(0));
		
		return true;
	}

	private void percolateUp(int index) {
		// find the parent index
		int iparent = index >> 1;

		// check stop condition, if parent is less than children
		// or current is the root, we need to do nothing
		if (index == 1 || data.get(iparent) < data.get(index))
			return;

		// swap children and parent
		int parentVal = data.get(iparent);
		data.set(iparent, data.get(index));
		data.set(index, parentVal);

		// do the same with the new parent
		percolateUp(iparent);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int remove() {
		if(data.get(0) == 0)
			throw new IllegalStateException("Cannot remove from empty heap !");
		
		// get min
		int minVal = data.get(1);
		// get last
		int lastVal = data.get(data.get(0));
		
		// update current size
		data.set(0, data.get(0) - 1);
		// update min value
		data.set(1, lastVal);
		
		// percolate down
		percolateDown(1);
		
		return minVal;
	}

	private void percolateDown(int index) {
		// check if leaf, if that's the case do nothing
		// checks that parent is greater than current size
		if ((index << 1) > data.get(0))
			return;

		// initialize children variables
		int ileftchild = index << 1;
		int irightChild = (index << 1) + 1;
		int iminChild;

		// check if right child does not exist
		// in that case iminChild is the left otherwise find the min child
		if (irightChild > data.get(0)) {
			iminChild = ileftchild;
		} else {
			iminChild = (data.get(ileftchild) < data.get(irightChild)) ? ileftchild : irightChild;
		}

		// stops if the parent is smaller than the child
		if (data.get(index) < data.get(iminChild))
			return;

		int childVal = data.get(iminChild);
		data.set(iminChild, data.get(index));
		data.set(index, childVal);
		
		// recursive algorithm
		percolateDown(iminChild);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isValid() {
		for (int i = 1; i < (getCurrentSize() - 1) >> 1; ++i) {
			if (data.get((i << 1) + 1) < data.get(i))
				return false;

			if (((i << 1) + 2) >= getCurrentSize() && data.get((i << 1) + 2) < data.get(i))
				return false;
		}
		
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		return data.get(0) == 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCurrentSize() {
		return data.get(0);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCapacity() {
		return data.getSize() - 1;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getMin() {
		return data.get(1);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[MMH|");
		
		// i starts from one because element 0 is 
		for (int i = 1; i < data.get(0); ++i) {
			sb.append(data.get(i));
			sb.append(",");
		}
		
		if(data.get(data.get(0)) > 0)
			sb.append(data.get(data.get(0)));
		
		sb.append("]");
		
		return sb.toString();
	}
	
//	/**
//	 * {@inheritDoc}
//	 * @return
//	 */
//	@Override
//    public String toPreOrderString() { 
//        StringBuilder sb = new StringBuilder();
//        sb.append("[");
//        toPreOrderString(sb, 0);
//        sb.append("]");
//        return sb.toString(); 
//    }
//	
//	private void toPreOrderString(StringBuilder sb, int index) {
//		if(index >= data.getSize()) return;
//		sb.append(data.get(index));
//		toPreOrderString(sb, (index << 1) + 1);
//		toPreOrderString(sb, (index << 1) + 2);
//	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void display() {
		Utils.println(toString());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void benchCreate(IData dataset, int creationSize) {
		data = Data.of(dataset.getSize() + 1);
		data.set(0, dataset.getSize());
		
		for(int i = 1; i < creationSize; ++i)
			data.set(i, dataset.get(i - 1));
			
		for (int i = creationSize >> 1; i >= 0; i--)
			minHeapify(i + 1, creationSize);
		
		// Utils.println("heap ok: " + isValid());
	}
	
	private void minHeapify(int index, int size) {
		int left = (index << 1) + 1;
		int right = (index << 1) + 2;
		int smallest = -1;

		// find the smallest key between current node and its children.
		if (left <= size - 1 && data.get(left) < data.get(index)) {
			smallest = left;
		} else {
			smallest = index;
		}

		if (right <= size - 1 && data.get(right) < data.get(smallest)) {
			smallest = right;
		}

		// if the smallest key is not the current key then bubble-down it.
		if (smallest != index) {
			int tmp = data.get(index);
			data.set(index, data.get(smallest));
			data.set(index, tmp);
			minHeapify(smallest, size);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void benchInsertMin(int times) {
		for(int j = 0; j < times; ++j) {
			insert(getMin() - 1);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void benchRemoveMin(int times) {
		for(int j = 0; j < times; ++j) {
			remove();
		}
	}

	@Override
	public void benchInsertions(int times) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void benchRemovals(int times) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setupForBench(IData dataset, int creationSize) {
		benchCreate(dataset, creationSize);
	}
}