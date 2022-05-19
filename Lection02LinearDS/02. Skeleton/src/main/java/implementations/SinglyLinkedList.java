package implementations;

import java.util.Iterator;

import interfaces.LinkedList;

public class SinglyLinkedList<E> implements LinkedList<E> {

	private Node<E> head;
	private int size;

	private static class Node<E> {
		private E value;
		private Node<E> next;

		public Node(E element) {
			this.value = element;
			this.next = null;
		}
	}

	public SinglyLinkedList() {
		this.head = null;
		this.size = 0;
	}

	@Override
	public void addFirst(E element) {
		Node<E> toInsert = new Node<E>(element);
		if (!isEmpty()) {
			toInsert.next = head;

		}
		head = toInsert;
		this.size++;

	}

	@Override
	public void addLast(E element) {
		Node<E> toInsert = new Node<E>(element);
		Node<E> current = this.head;

		if (isEmpty()) {
			this.head = toInsert;
		} else {

			while (current.next != null) {
				current = current.next;
			}

			current.next = toInsert;
		}
		this.size++;

	}

	@Override
	public E removeFirst() {
		ensureNotEmpty();
		E value = this.head.value;
		head = this.head.next;
		this.size--;
		return value;
	}

	private void ensureNotEmpty() {
		if (this.isEmpty()) {
			throw new IllegalStateException();
		}

	}

	@Override
	public E removeLast() {
		ensureNotEmpty();
		Node<E> current = this.head;
		E valueToReturn = null; 

		if (current.next == null) {
			valueToReturn = this.head.value;
			this.head = null;
			
		} else {

			Node<E> afterCurrent = current.next;

			while (afterCurrent.next != null) {
				
				current = afterCurrent;
				afterCurrent = current.next;
				
				

			}
			valueToReturn = afterCurrent.value;
			current.next = null;
			
		}

		this.size--;

		return valueToReturn;
	}

	@Override
	public E getFirst() {
		ensureNotEmpty();
		return this.head.value;
	}

	@Override
	public E getLast() {
		ensureNotEmpty();
		Node<E> current = this.head;
		while (current.next != null) {
			current = current.next;
		}

		return current.value;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {

			private Node<E> current = head;

			@Override
			public boolean hasNext() {

				return current != null;
			}

			@Override
			public E next() {

				Node<E> tmp = this.current;
				current = this.current.next;
				return tmp.value;
			}

		};
	}
}
