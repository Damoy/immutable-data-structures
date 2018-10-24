package com.dzoum.ids.core.avl.immutable;

import static com.dzoum.ids.utils.Utils.*;

import com.dzoum.ids.utils.Utils.Pair;

public class ImmutableAVL implements IImmutableAVL {
	
	private final int element;
	private final IImmutableAVL left, right;
	private final int height;
	private final int size;

	public ImmutableAVL(int element, IImmutableAVL left, IImmutableAVL right,
			int height, int size) {
		this.element = element;
		this.left = left;
		this.right = right;
		this.height = height;
		this.size = size;
	}
	
	public ImmutableAVL(int element, IImmutableAVL left, IImmutableAVL right) {
		this.element = element;
		this.left = left;
		this.right = right;
		
		if(left == null) {
			if(right == null) {
				this.height = 1;
				this.size = 1;
			} else {
				this.height = right.getHeight() + 1;
				this.size = right.getSize() + 1;
			}
		} else {
			if(right == null) {
				this.height = left.getHeight() + 1;
				this.size = left.getSize() + 1;
			} else {
				this.height = Math.max(left.getHeight(), right.getHeight()) + 1;
				this.size = left.getSize() + right.getSize() + 1;
			}
		}
	}
	
	public ImmutableAVL(int element) {
		this(element, null, null, 1, 1);
	}
	
	@Override
	public int getElement() { return element;}
	@Override
	public IImmutableAVL getLeftChild() {return left;}
	@Override
	public IImmutableAVL getRightChild() {return right;}
	@Override
	public int getHeight() {return height;}
	@Override
	public int getSize() {return size;}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public IImmutableAVL add(int element) {
		int c = compareInts(element, this.element);
		if (c < 0) {
			if(this.left == null) {
				return balanceLeft(this.element, new ImmutableAVL(element), this.right);
			} else {
				IImmutableAVL left = this.left.add(element);
				if (left != this.left) {
					return balanceLeft(this.element, left, this.right);
				}
			}
		} else if (c > 0) {
			if(this.right == null) {
				return new ImmutableAVL(this.element,
						this.left, new ImmutableAVL(element));
			} else {
				IImmutableAVL right = this.right.add(element);
				if (right != this.right) {
					return balanceRight(this.element, this.left, right);
				}
			}
		}
		return this;
	}
	
	private IImmutableAVL balanceLeft(int element, IImmutableAVL left, IImmutableAVL right) {
		if (left.getHeight() <= right.getHeight() + 1) {
			return new ImmutableAVL(element, left, right);
		} else if (left.getLeftChild().getHeight() > right.getHeight()) {
			return new ImmutableAVL(left.getElement(), left.getLeftChild(),
						new ImmutableAVL(element, left.getRightChild(), right));
		} else {
			return new ImmutableAVL(left.getRightChild().getElement(),
				new ImmutableAVL(left.getElement(), left.getLeftChild(), left.getRightChild().getLeftChild()),
				new ImmutableAVL(element, left.getRightChild().getRightChild(), right));
		}
	}

	private ImmutableAVL balanceRight(int element, IImmutableAVL left, IImmutableAVL right) {
		if (left.getHeight() + 1 >= right.getHeight()) {
			return new ImmutableAVL(element, left, right);
		} else if (left.getHeight() < right.getRightChild().getHeight()) {
			return new ImmutableAVL(right.getElement(),
				new ImmutableAVL(element, left, right.getLeftChild()), right.getRightChild());
		} else {
			return new ImmutableAVL(right.getLeftChild().getElement(),
					new ImmutableAVL(element, left, right.getLeftChild().getLeftChild()),
					new ImmutableAVL(right.getElement(), right.getLeftChild().getRightChild(),
						right.getRightChild()));
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Pair<Integer, IImmutableAVL> pollFirst(){
		if (this.getLeftChild() instanceof ImmutableNullAVL) {
			return new Pair<>(element, right);
		} else {
			Pair<Integer, IImmutableAVL> p = left.pollFirst();
			return new Pair<>(p.first, balanceRight(element, p.second, right));
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IImmutableAVL remove(int element) {
		int c = compareInts(element, this.element);
		if (c < 0) {
			IImmutableAVL left = this.remove(element);
			if (left != this.left) {
				return balanceRight(this.element, left, right);
			}
		} else if (c > 0) {
			IImmutableAVL right = this.right.remove(element);
			if (right != this.right) {
				return balanceLeft(this.element, this.left, right);
			}
		} else if (right instanceof ImmutableNullAVL) {
			return left;
		} else {
			Pair<Integer, IImmutableAVL> p = right.pollFirst();
			return balanceLeft(p.first, left, p.second);
		}
		
		return this;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int index(int element) {
		if (compareInts(element, this.element) <= 0) {
			return left.index(element);
		} else {
			return left.getSize() + 1 + right.index(element);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int subSize(int fromElement, int toElement) {
		if (compareInts(toElement, this.element) <= 0) {
			return left.subSize(fromElement, toElement);
		} else if (compareInts(fromElement, this.element) > 0) {
			return right.subSize(fromElement, toElement);
		} else {
			return index(toElement) - index(fromElement);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		toString(sb, this);
		return sb.toString();
	}
	
	private void toString(StringBuilder sb, IImmutableAVL iavl) {
		if(iavl == null) return;
		sb.append(iavl.getElement());
		sb.append(",");
		toString(sb, iavl.getLeftChild());
		toString(sb, iavl.getRightChild());
	}
	
}