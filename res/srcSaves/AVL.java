package com.dzoum.ids.core.avl.immutable;

public class AVL {

	public static abstract class AVLTree<E extends Comparable<E>> {
		protected final E element;
		protected final AVLTree<E> left, right;
		protected final int height;
		public final int size;

		protected AVLTree(E element, AVLTree<E> left, AVLTree<E> right, int height, int size) {
			this.element = element;
			this.left = left;
			this.right = right;
			this.height = height;
			this.size = size;
		}

		public abstract AVLTree<E> add(E element);

		public abstract Pair<E, AVLTree<E>> pollFirst();

		public abstract AVLTree<E> remove(E element);

		public abstract int index(E element);

		public abstract int subSize(E fromElement, E toElement);

		protected abstract String toString(String indent);

		public String toString() {
			return this.toString("");
		}

	}

	public static final class Pair<A, B> {
		public final A first;
		public final B second;

		public Pair(A first, B second) {
			this.first = first;
			this.second = second;
		}

	}

	public static final class AVLNil<E extends Comparable<E>> extends AVLTree<E> {

		public AVLNil() {
			super(null, null, null, 0, 0);
		}

		public AVLTree<E> add(E element) {
			return new AVLNode<E>(element, this, this);
		}

		public Pair<E, AVLTree<E>> pollFirst() {
			return new Pair<E, AVLTree<E>>(null, this);
		}

		public AVLTree<E> remove(E element) {
			return this;
		}

		public int index(E element) {
			return 0;
		}

		public int subSize(E fromElement, E toElement) {
			return 0;
		}

		protected String toString(String indent) {
			return indent + "$\n";
		}

	}

	public static final class AVLNode<E extends Comparable<E>> extends AVLTree<E> {

		public AVLNode(E element, AVLTree<E> left, AVLTree<E> right) {
			super(element, left, right,
					(left == null && right == null) ? 1 : 
					(left != null && right != null) ? (1 + Math.max(left.height, right.height)) :
						(left == null) ? right.height + 1 : left.height + 1,
								(left == null && right == null) ? 1 : 
								(left != null && right != null) ? (1 + left.size + right.size) :
									(left == null) ? right.size + 1 : left.size + 1);
		}

		private AVLTree<E> balanceLeft(E element, AVLTree<E> left, AVLTree<E> right) {
			if (left.height <= right.height + 1) {
				return new AVLNode<E>(element, left, right);
			} else if (left.left.height > right.height) {
				return new AVLNode<E>(left.element, left.left, new AVLNode<E>(element, left.right, right));
			} else {
				return new AVLNode<E>(left.right.element, new AVLNode<E>(left.element, left.left, left.right.left),
						new AVLNode<E>(element, left.right.right, right));
			}
		}

		private AVLTree<E> balanceRight(E element, AVLTree<E> left, AVLTree<E> right) {
			if (left.height + 1 >= right.height) {
				return new AVLNode<E>(element, left, right);
			} else if (left.height < right.right.height) {
				return new AVLNode<E>(right.element, new AVLNode<E>(element, left, right.left), right.right);
			} else {
				return new AVLNode<E>(right.left.element, new AVLNode<E>(element, left, right.left.left),
						new AVLNode<E>(right.element, right.left.right, right.right));
			}
		}

		public AVLTree<E> add(E element) {
			int c = element.compareTo(this.element);
			if (c < 0) {
				AVLTree<E> left = this.left.add(element);
				if (left != this.left) {
					return balanceLeft(this.element, left, this.right);
				}
			} else if (c > 0) {
				AVLTree<E> right = this.right.add(element);
				if (right != this.right) {
					return balanceRight(this.element, this.left, right);
				}
			}
			return this;
		}

		public Pair<E, AVLTree<E>> pollFirst() {
			if (this.left instanceof AVLNil) {
				return new Pair<E, AVLTree<E>>(this.element, this.right);
			} else {
				Pair<E, AVLTree<E>> p = this.left.pollFirst();
				return new Pair<E, AVLTree<E>>(p.first, balanceRight(this.element, p.second, this.right));
			}
		}

		public AVLTree<E> remove(E element) {
			int c = element.compareTo(this.element);
			if (c < 0) {
				AVLTree<E> left = this.left.remove(element);
				if (left != this.left) {
					return balanceRight(this.element, left, this.right);
				}
			} else if (c > 0) {
				AVLTree<E> right = this.right.remove(element);
				if (right != this.right) {
					return balanceLeft(this.element, this.left, right);
				}
			} else if (this.right instanceof AVLNil) {
				return this.left;
			} else {
				Pair<E, AVLTree<E>> p = this.right.pollFirst();
				return balanceLeft(p.first, this.left, p.second);
			}
			return this;
		}

		public int index(E element) {
			if (element.compareTo(this.element) <= 0) {
				return this.left.index(element);
			} else {
				return this.left.size + 1 + this.right.index(element);
			}
		}

		public int subSize(E fromElement, E toElement) {
			if (toElement.compareTo(this.element) <= 0) {
				return this.left.subSize(fromElement, toElement);
			} else if (fromElement.compareTo(this.element) > 0) {
				return this.right.subSize(fromElement, toElement);
			} else {
				return index(toElement) - index(fromElement);
			}
		}

		protected String toString(String indent) {
			String indent1 = indent + "  ";
			return this.left.toString(indent1) + indent + this.element + "\n" + this.right.toString(indent1);
		}

	}

}