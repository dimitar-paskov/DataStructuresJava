

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class BinarySearchTreeBackup<E extends Comparable<E>> {
	private Node<E> root;

	public static class Node<E> {
		private E value;
		private Node<E> leftChild;
		private Node<E> rightChild;

		public Node(E value) {
			this.value = value;
		}

		public Node<E> getLeft() {
			return this.leftChild;
		}

		public Node<E> getRight() {
			return this.rightChild;
		}

		public E getValue() {
			return this.value;
		}
	}

	public BinarySearchTreeBackup() {
		// TODO Auto-generated constructor stub
	}

	public BinarySearchTreeBackup(Node<E> node) {

		this.copy(node);

	}

	private void copy(Node<E> node) {
		this.insert(node.value);
		this.copy(node.leftChild);
		this.copy(node.rightChild);

	}

	public void eachInOrder(Consumer<E> consumer) {

		if (this.root.leftChild != null) {
			BinarySearchTreeBackup<E> leftCopy = new BinarySearchTreeBackup<E>(this.root.leftChild);
			leftCopy.eachInOrder(consumer);
		}

		consumer.accept(this.root.value);

		if (this.root.rightChild != null) {
			BinarySearchTreeBackup<E> rightCopy = new BinarySearchTreeBackup<E>(this.root.rightChild);
			rightCopy.eachInOrder(consumer);
		}

	}

	public Node<E> getRoot() {
		return this.root;
	}

	public void insert(E element) {

		Node<E> newNode = new Node<E>(element);
		Node<E> current;

		if (this.root == null) {
			this.root = newNode;
		} else {
			current = this.root;
			Node<E> prev = null;

			while (current != null) {
				prev = current;

				if (isLess(element, current.getValue())) {
					current = current.getLeft();
				} else if (isGreater(element, current.getValue())) {
					current = current.getRight();
				} else {
					return;
				}

			}

			if (isLess(element, prev.getValue())) {
				prev.leftChild = newNode;
			} else if (isGreater(element, prev.getValue())) {
				prev.rightChild = newNode;
			}

		}

	}

	private boolean isLess(E firstElement, E secondElement) {
		return firstElement.compareTo(secondElement) < 0;
	}

	private boolean isGreater(E firstElement, E secondElement) {
		return firstElement.compareTo(secondElement) > 0;
	}

	private boolean areEqual(E firstElement, E secondElement) {
		return firstElement.compareTo(secondElement) == 0;
	}

	public boolean contains(E element) {

		Node<E> current = this.root;

		while (current != null) {

			if (isLess(element, current.value)) {
				current = current.leftChild;
			} else if (isGreater(element, current.value)) {
				current = current.rightChild;
			} else {
				return true;
			}

		}

		return false;

	}

	public BinarySearchTreeBackup<E> search(E element) {

		BinarySearchTreeBackup<E> result = new BinarySearchTreeBackup<E>();

		Node<E> current = this.root;

		while (current != null) {

			if (isLess(element, current.value)) {
				current = current.leftChild;
			} else if (isGreater(element, current.value)) {
				current = current.rightChild;
			} else {
				return result = new BinarySearchTreeBackup<E>(current);
			}

		}

		return result;

	}

	public List<E> range(E first, E second) {

		List<E> result = new ArrayList<E>();

		Node<E> current = this.root;

		while (current != null) {

			if (isLess(first, current.value)) {
				current = current.leftChild;
			} else if (isGreater(first, current.value)) {
				current = current.rightChild;
			} else {

				while (current != null) {

					result.add(current.value);

					if (isLess(second, current.value)) {
						current = current.leftChild;
						result.add(current.value);
					} else if (isGreater(second, current.value)) {
						current = current.rightChild;
						result.add(current.value);
					} else {
						result.add(current.value);
						return result;
					}

				}
			}

		}

		return new ArrayList<E>();
	}

	public void deleteMin() {

		if (this.root == null) {
			throw new IllegalArgumentException();
		}

		Node<E> current = this.root;
		Node<E> prev = current;

		if (this.root.leftChild == null) {
			 this.root = this.root.rightChild; // delete root as it is the smallest

		}

		while (current.leftChild != null) {
			
			prev = current;
			current = current.leftChild;

		}
		
		prev.leftChild = null;
		

	}

	public void deleteMax() {
		
		if (this.root == null) {
			throw new IllegalArgumentException();
		}

		Node<E> current = this.root;
		Node<E> prev = current;

		if (this.root.rightChild == null) {
			 this.root = this.root.leftChild; // delete root as it is the largest

		}

		while (current.rightChild != null) {
			
			prev = current;
			current = current.rightChild;

		}
		
		prev.rightChild = null;

	}

	public int count() {
		
		if(this.root == null) {
			return 0;
		}
		return 0;
	}

	public int rank(E element) {
		return 0;
	}

	public E ceil(E element) {
		return null;
	}

	public E floor(E element) {
		return null;
	}
}
