package com.dzoum.ids.core.redblacktree.mutable;

import java.util.LinkedList;
import java.util.Queue;

import com.dzoum.ids.app.bench.IBenchable;
import com.dzoum.ids.model.IData;
import com.dzoum.ids.utils.Utils;

public class MutableRedBlackTree implements IMutableRedBlackTree, IBenchable {

	private IMutableRedBlackTreeNode root;
	
	public MutableRedBlackTree(int rootValue) {
		// root is always black
		root = new MutableRedBlackTreeNode(rootValue, MutableRedBlackTreeNode.BLACK);
	}
	
	public MutableRedBlackTree() {
		root = null;
	}

	@Override
	public void insert(int value) {
		IMutableRedBlackTreeNode newNode = new MutableRedBlackTreeNode(value, MutableRedBlackTreeNode.RED);
		
		if(root == null){
			// if root is null, just set root to new node
			newNode.setColor(MutableRedBlackTreeNode.BLACK);
			root = newNode;
		} else {
			IMutableRedBlackTreeNode searchNode = search(value);
			
			// if the value already exists, stop
			if(searchNode.getValue() == value)
				return;
			
			// lookFor method returns the node if the value is not found 
			// relate the new node with the correct one
			newNode.setParent(searchNode);
			
			if(value < searchNode.getValue()){
				searchNode.setLeftChild(newNode);
			} else {
				searchNode.setRightChild(newNode);
			}
			
			// check for red red breach
			checkRedBreach(newNode);
		}
	}
	
	@Override
	public IMutableRedBlackTreeNode search(int nodeValue){
		IMutableRedBlackTreeNode tmp = root;
		
		while(tmp != null){
			if(nodeValue < tmp.getValue()){
				if(tmp.getLeftChild() == null)
					break;
				else
					tmp = tmp.getLeftChild();
			} else if(nodeValue == tmp.getValue()){
				break;
			} else {
				if(tmp.getRightChild() == null)
					break;
				else 
					tmp = tmp.getRightChild();
			}
		}
		
		return tmp;
	}
	
	// Fix the double red violation
	private void checkRedBreach(IMutableRedBlackTreeNode node){
		// if node is the root, set color to black and return
		if(node == root){
			node.setColor(MutableRedBlackTreeNode.BLACK);
			return;
		}
		
		// create parent, uncle and grand-parent
		IMutableRedBlackTreeNode parent = node.getParent();
		IMutableRedBlackTreeNode uncle = node.getUncle();
		IMutableRedBlackTreeNode grandParent = parent.getParent();
		
		// if the parent is not black
		if(parent.isRed()){
			if(uncle != null && uncle.isRed()){
				// have to perform coloring and recurse
				parent.setColor(MutableRedBlackTreeNode.BLACK);
				uncle.setColor(MutableRedBlackTreeNode.BLACK);
				grandParent.setColor(MutableRedBlackTreeNode.RED);
				checkRedBreach(grandParent);
			} else {
				// else perform LR, LL, RL, RR
				if(parent.isLeftChild()){
					if(node.isLeftChild()){
						parent.swapColor(grandParent);
					} else {
						leftRotate(parent);
						node.swapColor(grandParent);
					}
					
					// for left left and left right 
					rightRotate(grandParent);
				} else {
					if(node.isLeftChild()){
						rightRotate(parent);
						node.swapColor(grandParent);
					} else {
						parent.swapColor(grandParent);
					}
					
					// for right right and right left
					leftRotate(grandParent);
				}
			}
		}
	}

	@Override
	public IMutableRedBlackTreeNode remove() {
		IMutableRedBlackTreeNode root = getRoot();
		remove(getRoot());
		return root;
	}
	
	@Override
	public void remove(IMutableRedBlackTreeNode node) {
		if(node == null) return;
		IMutableRedBlackTreeNode x = replace(node);
		
		// Are both node and x nodes black ?
		boolean nodesBlack = (x == null || x.isBlack()) && node.isBlack();
		IMutableRedBlackTreeNode parent = node.getParent();
		
		if(x == null) {
			// x is null so node is leaf
			if(node == root) {
				// node is root, set root to null
				root = null;
			} else {
				if(nodesBlack) {
					// if both nodes are black
					// node is leaf, check black breach on node
					checkBlackBreach(node);
				} else {
					if(node.getBrother() != null) {
						// brother is not null, set its color to red
						node.getBrother().setColor(MutableRedBlackTreeNode.RED);
					}
				}
					
				// delete node from red black tree
				if(node.isLeftChild()) {
					parent.setLeftChild(null);
				} else {
					parent.setRightChild(null);
				}
			}
			
			return;
		}
			
		if(node.getLeftChild() ==  null || node.getRightChild() == null) {
			// node has one child
			if(node == root) {
				// node is root, assign value of x to node and delete x
				node.setValue(x.getValue());
				node.setLeftChild(null);
				node.setRightChild(null);
			} else {
				// detach node from the tree and move x up
				if(node.isLeftChild()) {
					parent.setLeftChild(x);
				} else {
					parent.setRightChild(x);
				}
				
				x.setParent(parent);
				
				if(nodesBlack) {
					checkBlackBreach(x);
				} else {
					x.setColor(MutableRedBlackTreeNode.BLACK);
				}
			}
			
			return;
		}
		
		// node has 2 children, swap values with successor and recurse
		x.swapValue(node);
		remove(x);
	}
	
	// Fix the double black violation
	private void checkBlackBreach(IMutableRedBlackTreeNode node) {
		if(node == root) return;
		
		IMutableRedBlackTreeNode brother = node.getBrother();
		IMutableRedBlackTreeNode parent = node.getParent();
		
		if(brother == null) {
			checkBlackBreach(parent);
		} else {
			// brother red
			if(brother.isRed()) {
				// update colors
				parent.setColor(MutableRedBlackTreeNode.RED);
				brother.setColor(MutableRedBlackTreeNode.BLACK);
				
				if(brother.isLeftChild()) {
					// left case
					rightRotate(parent);
				} else {
					// right case
					leftRotate(parent);
				}
				
				checkBlackBreach(node);
			} else {
				// brother black
				if(brother.hasRedChild()) {
					// at least one red child
					if(brother.getLeftChild() != null && brother.getLeftChild().isRed()) {
						if(brother.isLeftChild()) {
							// left left case
							brother.getLeftChild().setColor(brother.getColor());
							brother.setColor(parent.getColor());
							rightRotate(parent);
						} else {
							// right left
							brother.getLeftChild().setColor(parent.getColor());
							rightRotate(brother);
							leftRotate(parent);
						}
					} else {
						if(brother.isLeftChild()) {
							// left right
							brother.getRightChild().setColor(parent.getColor());
							leftRotate(brother);
							rightRotate(parent);
						} else {
							// right right
							brother.getRightChild().setColor(brother.getColor());
							brother.setColor(parent.getColor());
							leftRotate(parent);
						}
					}
					
					parent.setColor(MutableRedBlackTreeNode.BLACK);
				} else {
					// 2 black children
					brother.setColor(MutableRedBlackTreeNode.RED);
					if(parent.isBlack()) {
						checkBlackBreach(parent);
					} else {
						parent.setColor(MutableRedBlackTreeNode.BLACK);
					}
				}
			}
		}
	}
	
	
	private IMutableRedBlackTreeNode replace(IMutableRedBlackTreeNode node) { 
		// when node have 2 children 
		if (node.getLeftChild() != null && node.getRightChild() != null) {
			return findNodeSuccessor(node.getRightChild()); 
		}

		// leaf
		if(node.getLeftChild() == null && node.getRightChild() == null)
			return null;

		// one child
		if(node.getLeftChild() != null) {
			return node.getLeftChild();
		} else {
			return node.getRightChild();
		}
	}

	@Override
	public IMutableRedBlackTreeNode removeGivenValue(int value) {
		if(root == null) return null;
		
		IMutableRedBlackTreeNode node = search(value);
		
		if(node.getValue() != value) {
			return null;
		}
		
		IMutableRedBlackTreeNode ptr = node;
		remove(node);
		return ptr;
	}

	// left rotation given the node
	private void leftRotate(IMutableRedBlackTreeNode node) {
		// node's right child is the new parent
		IMutableRedBlackTreeNode newParent = node.getRightChild();
		
		// update the root if current node is root 
		if(node == root){
			root = newParent;
		}
		
		// move down node with the new parent
		node.moveDown(newParent);
		
		// relate node with the new parent's left child
		node.setRightChild(newParent.getLeftChild());
		
		// relate new parent's left child with node
		if(newParent.getLeftChild() != null)
			newParent.getLeftChild().setParent(node);

		// relate new parent with node
		newParent.setLeftChild(node);
	}

	private void rightRotate(IMutableRedBlackTreeNode node) {
		// new parent is node's left child
		IMutableRedBlackTreeNode newParent = node.getLeftChild();
		
		// if current node is the root; update the root
		if(node == root)
			root = newParent;
		
		// move down node with the new parent
		node.moveDown(newParent);
		
		// relate node with the new parent's right child
		node.setLeftChild(newParent.getRightChild());

		// relate node with the new parent's right child
		if(newParent.getRightChild() != null)
			newParent.getRightChild().setParent(node);

		// relate new parent with node
		newParent.setRightChild(node);
	}

	// Finds node that don't have a left child in the given node's subtree
	private IMutableRedBlackTreeNode findNodeSuccessor(IMutableRedBlackTreeNode node) {
		IMutableRedBlackTreeNode successor = node;
		
		while(successor.getLeftChild() != null)
			successor = successor.getLeftChild();
		
		return successor;
	}

	@Override
	public String toStringWidthOrder() {
		if(root == null) return "";
		StringBuilder sb = new StringBuilder();

		Queue<IMutableRedBlackTreeNode> queue = new LinkedList<>();
		IMutableRedBlackTreeNode current = null;
		queue.add(root);
		
		while(!queue.isEmpty()){
			current = queue.poll();
			sb.append(current.toString());
			sb.append(" ");
			
			if(current.getLeftChild() != null)
				queue.add(current.getLeftChild());
			
			if(current.getRightChild() != null)
				queue.add(current.getRightChild());
		}

		return sb.toString();
	}

	@Override
	public String toStringPreOrder() {
		if(root == null) return "";
		StringBuilder sb = new StringBuilder();
		toStringPreOrder(sb, root);
		return sb.toString();
	}
	
	private void toStringPreOrder(StringBuilder sb, IMutableRedBlackTreeNode node){
		if(node == null) return;
		sb.append(node.toString());
		sb.append(" ");
		toStringPreOrder(sb, node.getLeftChild());
		toStringPreOrder(sb, node.getRightChild());
	}

	@Override
	public void printColoredWidthOrder() {
		Utils.println(toStringWidthOrder());
	}
	
	@Override
	public void printWidthOrder() {
		StringBuilder sb = new StringBuilder();
		if(root == null) {
			sb.append("Red black tree is empty.\n");
		} else {
			printWidthOrder(sb, root);
			sb.append("\n");
		}
		
		Utils.print(sb.toString());
	}
	
	private void printWidthOrder(StringBuilder sb, IMutableRedBlackTreeNode node) {
		if(node == null) return;
		Queue<IMutableRedBlackTreeNode> queue = new LinkedList<>();
		IMutableRedBlackTreeNode current = null;
		queue.add(node);
		
		while(!queue.isEmpty()) {
			current = queue.poll();
			if(current != null) {
				Utils.print(current.getValue() + " ");
				
				if(current.getLeftChild() != null)
					queue.add(current.getLeftChild());
				if(current.getRightChild() != null)
					queue.add(current.getRightChild());
			}
		}
	}

	@Override
	public void printPreOrder() {
		Utils.println(toStringPreOrder());
	}

	@Override
	public IMutableRedBlackTreeNode getMinNode(IMutableRedBlackTreeNode from) {
		if(from == null) return from;
		
		IMutableRedBlackTreeNode min = from;

		while (min.getLeftChild() != null)
			min = min.getLeftChild();

		return min;
	}

	@Override
	public IMutableRedBlackTreeNode getRoot() {
		return root;
	}

	@Override
	public int getMinValue(IMutableRedBlackTreeNode from) {
		IMutableRedBlackTreeNode node = getMinNode(from);
		if(node == null) throw new IllegalStateException("No minimum node found !");
		return node.getValue();
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public void setRoot(IMutableRedBlackTreeNode root) {
		this.root = root;
	}
	
	@Override
	public void benchCreate(IData dataset, int creationSize) {
		IData sorted = dataset.clone().sort();
		root = create(sorted, 0, creationSize, MutableRedBlackTreeNode.BLACK);
	}
	
	private IMutableRedBlackTreeNode create(IData dataset, int start, int end, byte color) {
		if(start >= end) return null;
		int mid = (end + start) >> 1;
		IMutableRedBlackTreeNode node = new MutableRedBlackTreeNode(dataset.get(mid), color);
		
		color = (color == MutableRedBlackTreeNode.BLACK) ?
				MutableRedBlackTreeNode.RED : MutableRedBlackTreeNode.BLACK;
		
		node.setLeftChild(create(dataset, start, mid - 1, color));
		node.setRightChild(create(dataset, mid + 1, end, color));
		return node;
	}

	@Override
	public void benchInsertMin(int times) {
		for(int i = 0; i < times; ++i) {
			insert(getMinValue(root) - 1);
		}
	}

	@Override
	public void benchRemoveMin(int times) {
		for(int i = 0; i < times; ++i) {
			remove();
		}
	}

	@Override
	public void benchInsertions(IData data, int times) {
		for(int i = 0; i < times; ++i) {
			insert(data.getRandomValue());
		}
	}

	@Override
	public void benchRemovals(IData data, int times) {
		for(int i = 0; i < times; ++i) {
			removeGivenValue(data.getRandomValue());
		}
	}

	@Override
	public void setupForBench(IData dataset, int creationSize) {
		root = new MutableRedBlackTreeNode(dataset.get(0), MutableRedBlackTreeNode.BLACK);
		for(int i = 1; i < creationSize; ++i) {
			insert(dataset.get(i));
		}
	}

	@Override
	public void benchSearch(IData dataset, int times) {
		for(int i = 0; i < times; ++i) {
			search(dataset.getRandomValue());
		}
	}

}
