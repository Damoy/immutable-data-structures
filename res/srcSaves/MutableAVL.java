package com.dzoum.ids.core.avl.mutable;

import static com.dzoum.ids.utils.Utils.*;

import java.util.LinkedList;
import java.util.Queue;

import com.dzoum.ids.app.bench.IBenchable;
import com.dzoum.ids.model.IData;
import com.dzoum.ids.utils.Utils;

/**
 * {@link IMutableAVL}
 */
public class MutableAVL implements IMutableAVL, IBenchable {

	private IMutableAVLNode root;

	public MutableAVL(IMutableAVLNode root) {
		this.root = root;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IMutableAVLNode insert(IMutableAVLNode node, int value) {
		if (node == null)
			return (new MutableAVLNode(value));

		if (value < node.getValue())
			node.setLeftChild(insert(node.getLeftChild(), value));
		else if (value > node.getValue())
			node.setRightChild(insert(node.getRightChild(), value));
		else
			return node; // Duplicate values not allowed

		// Update height of this ancestor node
		node.setHeight(1 + max(getNodeHeight(node.getLeftChild()), getNodeHeight(node.getRightChild())));

		int balance = getBalance(node);

		// If this node becomes unbalanced, then there
		// are 4 cases Left Left Case
		if (balance > 1 && value < node.getLeftChild().getValue())
			return rightRotate(node);

		// Right Right Case
		if (balance < -1 && value > node.getRightChild().getValue())
			return leftRotate(node);

		// Left Right Case
		if (balance > 1 && value > node.getLeftChild().getValue()) {
			node.setLeftChild(leftRotate(node.getLeftChild()));
			return rightRotate(node);
		}

		// Right Left Case
		if (balance < -1 && value < node.getRightChild().getValue()) {
			node.setRightChild(rightRotate(node.getRightChild()));
			return leftRotate(node);
		}

		return node;
	}

	private int getBalance(IMutableAVLNode node) {
		if (node == null)
			return 0;

		return getNodeHeight(node.getLeftChild()) - getNodeHeight(node.getRightChild());
	}

	private IMutableAVLNode rightRotate(IMutableAVLNode node) {
		if(node.getLeftChild() == null) return null;
		
		IMutableAVLNode left = node.getLeftChild();
		IMutableAVLNode lRightChild = left.getRightChild();

		// Perform rotation
		left.setRightChild(node);
		node.setLeftChild(lRightChild);

		// Update heights
		node.setHeight(max(getNodeHeight(node.getLeftChild()), getNodeHeight(node.getRightChild())) + 1);
		left.setHeight(max(getNodeHeight(left.getLeftChild()), getNodeHeight(left.getRightChild())) + 1);

		// Return new root
		return left;
	}

	private IMutableAVLNode leftRotate(IMutableAVLNode node) {
		if(node.getRightChild() == null) return null;
		IMutableAVLNode right = node.getRightChild();
		IMutableAVLNode rightLeftChild = right.getLeftChild();

		// Perform rotation
		right.setLeftChild(node);
		node.setRightChild(rightLeftChild);

		// Update heights
		node.setHeight(max(getNodeHeight(node.getLeftChild()), getNodeHeight(node.getRightChild())) + 1);
		right.setHeight(max(getNodeHeight(right.getLeftChild()), getNodeHeight(right.getRightChild())) + 1);

		// Return new root
		return right;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setRoot(IMutableAVLNode root) {
		this.root = root;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IMutableAVLNode remove(IMutableAVLNode node, int value) {
		if (node == null)
			return node;

		// If the value to be deleted is smaller than
		// the root's value then it is located in the left subtree
		if (value < node.getValue())
			node.setLeftChild(remove(node.getLeftChild(), value));

		// If the value to be deleted is greater than the
		// root's value, then it lies in right subtree
		else if (value > node.getValue())
			node.setRightChild(remove(node.getRightChild(), value));

		// if value is same as root's value, then this is the node
		// to be deleted
		else {
			// node with only one child or no child
			if ((node.getLeftChild() == null) || (node.getRightChild() == null)) {
				IMutableAVLNode tmp = null;
				if (tmp == node.getLeftChild()) tmp = node.getRightChild();
				else tmp = node.getLeftChild();

				// No child case
				if (tmp == null) {
					tmp = node;
					node = null;
				} else {
					node = tmp;
				}
			} else {
				// node with two children: Get the in-order
				// successor (smallest in the right subtree)
				IMutableAVLNode tmp = getMinNode(node.getRightChild());

				// Copy the inorder successor's data to this node
				node.setValue(tmp.getValue());

				// Delete the inorder successor
				node.setRightChild(remove(node.getRightChild(), tmp.getValue()));
			}
		}

		// If the tree had only one node then return
		if (node == null)
			return node;

		// update height of the current node
		node.setHeight(max(getNodeHeight(node.getLeftChild()), getNodeHeight(node.getRightChild())) + 1);

		// get the balance factor of this node (to check whether
		// this node became unbalanced)
		int balance = getBalance(node);

		// If this node becomes unbalanced, then there are 4 cases
		// Left Left Case
		if (balance > 1 && getBalance(node.getLeftChild()) >= 0)
			return rightRotate(node);

		// Left Right Case
		if (balance > 1 && getBalance(node.getLeftChild()) < 0) {
			node.setLeftChild(leftRotate(node.getLeftChild()));
			return rightRotate(node);
		}

		// Right Right Case
		if (balance < -1 && getBalance(node.getRightChild()) <= 0)
			return leftRotate(node);

		// Right Left Case
		if (balance < -1 && getBalance(node.getRightChild()) > 0) {
			node.setRightChild(rightRotate(node.getRightChild()));
			return leftRotate(node);
		}

		return node;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IMutableAVLNode getMinNode(IMutableAVLNode from) {
		if(from == null) return from;
		
		IMutableAVLNode min = from;

		while (min.getLeftChild() != null)
			min = min.getLeftChild();

		return min;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getMinValue(IMutableAVLNode from) {
		IMutableAVLNode node = getMinNode(from);
		if(node == null) throw new IllegalStateException("No minimum node found !");
		return node.getValue();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IMutableAVLNode getRoot() {
		return root;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toStringWidthOrder() {
		if(root == null) return "";
		
		StringBuilder sb = new StringBuilder();
		Queue<IMutableAVLNode> nodes = new LinkedList<>();
		nodes.add(root);
		
		while(!nodes.isEmpty()){
			IMutableAVLNode node = nodes.poll();
			sb.append(node.getValue());
			sb.append(" ");
			
			if(node.getLeftChild() != null) nodes.add(node.getLeftChild());
			if(node.getRightChild() != null) nodes.add(node.getRightChild());
		}
		
		return sb.toString().trim();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toStringPreOrder() {
		if(root == null) return "";
		
		StringBuilder sb = new StringBuilder();

		if (root != null) {
			sb.append(root.getValue());
			sb.append(" ");
			toStringPreOrder(sb, root.getLeftChild());
			toStringPreOrder(sb, root.getRightChild());
		}
		
		return sb.toString().trim();
	}
	
	private void toStringPreOrder(StringBuilder sb, IMutableAVLNode node) {
		if (node != null) {
			sb.append(node.getValue());
			sb.append(" ");
			toStringPreOrder(sb, node.getLeftChild());
			toStringPreOrder(sb, node.getRightChild());
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void printPreOrder() {
		Utils.println(toStringPreOrder());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void printWidthOrder() {
		Utils.println(toStringWidthOrder());
	}
	
	@Override
	public void benchCreate(IData dataset, int creationSize) {
		IData sorted = dataset.clone().sort();
		root = create(sorted, 0, creationSize);
	}
	
	private IMutableAVLNode create(IData dataset, int start, int end) {
		if(start >= end) return null;
		int mid = (end + start) >> 1;
		IMutableAVLNode node = new MutableAVLNode(dataset.get(mid));
		node.setLeftChild(create(dataset, start, mid - 1));
		node.setRightChild(create(dataset, mid + 1, end));
		return node;
	}

	@Override
	public void benchInsertMin(int times) {
		for(int i = 0; i < times; ++i) {
			setRoot(insert(getRoot(), getMinValue(getRoot()) - 1));
		}
	}

	@Override
	public void benchRemoveMin(int times) {
		IMutableAVLNode node = null;
		for(int i = 0; i < times; ++i) {
			node = remove(getRoot(), getMinValue(getRoot()));
			if(node != null)
				setRoot(node);
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
		root = new MutableAVLNode(dataset.get(0));
		for(int i = 1; i < creationSize; ++i) {
			setRoot(insert(getRoot(), dataset.get(i)));
		}
	}

}
