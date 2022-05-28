package core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import shared.AbstractBinarySearchTree;

public class BinarySearchTree<E extends Comparable<E>> implements AbstractBinarySearchTree<E> {

	private Node<E> root;
	private Node<E> leftChild;
	private Node<E> rightChild;
	private int size;

	public BinarySearchTree() {
		this.size = 0;

	}

	public BinarySearchTree(Node<E> root) {

		this.copy(root);

	}

	private void copy(Node<E> node) {

		if (node != null) {
			this.insert(node.value);
			this.copy(node.leftChild);
			this.copy(node.rightChild);
		}

	}

	@Override
	public void insert(E element) {

		this.size++;

		Node<E> newNode = new Node<E>(element);

		if (this.getRoot() == null) {
			this.root = newNode;
		} else {
			Node<E> current = this.root;
			Node<E> prev = current;

			while (current != null) {
				prev = current;
				if (isLess(element, current.value)) {
					current = current.leftChild;
				} else if (isGreater(element, current.value)) {
					current = current.rightChild;
				} else if (areEqual(element, current.value)) {

					this.size--;

					throw new IllegalArgumentException();
				}

			}

			if (isLess(element, prev.value)) {
				prev.leftChild = newNode;
			} else if (isGreater(element, prev.value)) {
				prev.rightChild = newNode;
			}

		}

	}

	private boolean isLess(E first, E second) {

		return first.compareTo(second) < 0;
	}

	private boolean isGreater(E first, E second) {

		return first.compareTo(second) > 0;
	}

	private boolean areEqual(E first, E second) {

		return first.compareTo(second) == 0;
	}

	@Override
	public boolean contains(E element) {

		Node<E> current = this.root;

		while (current != null) {
			if (isLess(element, current.value)) {
				current = current.leftChild;

			} else if (isGreater(element, current.value)) {
				current = current.rightChild;
			} else if (areEqual(element, current.value)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public E search(E element) {

		Node<E> current = this.root;

		while (current != null) {
			if (isLess(element, current.value)) {
				current = current.leftChild;

			} else if (isGreater(element, current.value)) {
				current = current.rightChild;
			} else if (areEqual(element, current.value)) {
				return current.value;
			}
		}

		throw new IllegalArgumentException();

	}

	@Override
	public Node<E> getRoot() {
		return this.root;
	}

	@Override
	public Node<E> getLeft() {
		return this.leftChild;
	}

	@Override
	public Node<E> getRight() {
		return this.rightChild;
	}

	@Override
	public E getValue() {
		return this.root.value;
	}

	public E getLightest() {

		Node<E> current = this.root;

		if (current != null) {

			while (current.leftChild != null) {

				current = current.leftChild;

			}
		} else {
			throw new IllegalStateException();
		}

		return current.value;
	}

	public int getSize() {
		return size;
	}

	public E getHeaviest() {
		Node<E> current = this.root;

		if (current != null) {

			while (current.rightChild != null) {

				current = current.rightChild;

			}
		} else {
			throw new IllegalStateException();
		}

		return current.value;
	}

	public E deleteLightest() {

		Node<E> current = this.root;
		Node<E> next = this.root.leftChild == null ? null : this.root.leftChild;

		if (current != null) {
			if (next == null) {

				if (current.rightChild == null) {
					this.size = 0;
					this.root = null;
					return current.value;
				} else {
					this.root = this.root.rightChild;
					this.size--;
					return current.value;
				}

			} else {
				while (next.leftChild != null) {
					current = next;
					next = next.leftChild;

				}

				current.leftChild = null;
				this.size--;
				return next.value;
			}

		} else {
			throw new IllegalStateException();
		}

	}

	public E deleteHeaviest() {
		Node<E> current = this.root;
		Node<E> next = this.root.rightChild == null ? null : this.root.rightChild;

		if (current != null) {
			if (next == null) {

				if (current.leftChild == null) {
					this.size = 0;
					this.root = null;
					return current.value;
				} else {
					this.root = this.root.leftChild;
					this.size--;
					return current.value;
				}

			} else {
				while (next.rightChild != null) {
					current = next;
					next = next.rightChild;

				}

				current.rightChild = null;
				this.size--;
				return next.value;
			}

		} else {
			throw new IllegalStateException();
		}
	}

	public Collection<E> getOrderedByWeight() {

		List<E> result = new ArrayList<>();

		if (this.root != null) {

			getOrderedByWeightByNode(this.root, result);

		}

		return result;
	}

	private void getOrderedByWeightByNode(Node<E> node, List<E> result) {

		if (node != null) {
			getOrderedByWeightByNode(node.leftChild, result);
			result.add(node.value);
			getOrderedByWeightByNode(node.rightChild, result);
		}

	}

	public Collection<E> getPostOrder() {
		List<E> result = new ArrayList<>();

		if (this.root != null) {

			getPostOrderedByNode(this.root, result);

		}

		return result;
	}

	private void getPostOrderedByNode(Node<E> node, List<E> result) {

		if (node != null) {
			getPostOrderedByNode(node.leftChild, result);
			getPostOrderedByNode(node.rightChild, result);
			result.add(node.value);
		}

	}

	public Collection<E> getPreOrder() {

		List<E> result = new ArrayList<>();

		if (this.root != null) {

			getPreOrderedByNode(this.root, result);

		}

		return result;

	}

	private void getPreOrderedByNode(Node<E> node, List<E> result) {

		

		if (node != null) {
			result.add(node.value);
			getPreOrderedByNode(node.leftChild, result);
			getPreOrderedByNode(node.rightChild, result);
		}

	}

	public Collection<E> getInOrder() {
		List<E> result = new ArrayList<>();

		if (this.root != null) {

			getInOrderByNode(this.root, result);

		}

		return result;
	}

	private void getInOrderByNode(Node<E> node, List<E> result) {
		

		if (node != null) {
			getInOrderByNode(node.leftChild, result);
			result.add(node.value);
			getInOrderByNode(node.rightChild, result);
		}

	}

}