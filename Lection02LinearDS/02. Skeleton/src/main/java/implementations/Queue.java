package implementations;

import java.util.Iterator;

import interfaces.AbstractQueue;

public class Queue<E> implements AbstractQueue<E> {

	private Node<E> head;
	private int size;

	private static class Node<E> {
		private E value;
		private Node<E> next;

		public Node(E element) {
			this.value = element;
		}
	}

	public Queue() {
		this.head = null;
		this.size = 0;
	}

	@Override
	public void offer(E element) {

		Node<E> toInsert = new Node<E>(element);
		Node<E> current = this.head;

		if (this.isEmpty()) {
			this.head = toInsert;
			this.size++;
			return;
		}

		while (current.next != null) {
			current = current.next;
		}
		current.next = toInsert;
		this.size++;
		;

	}

	@Override
	public E poll() {
		
		ensureNotEmpty();
		
		Node<E> first = this.head;
		this.head = first.next;
		
		this.size--;
		
		return first.value;
	}

	private void ensureNotEmpty() {
		if(this.isEmpty()) {
			throw new IllegalStateException();
		}
	}

	@Override
	public E peek() {
		
		ensureNotEmpty();
		
		
		return this.head.value;
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
				Node<E> tmp = current;
				
				current = tmp.next;
				
				return tmp.value;
			}
			
		};
	}
}
