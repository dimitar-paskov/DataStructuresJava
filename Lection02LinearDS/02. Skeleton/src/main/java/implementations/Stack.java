package implementations;

import java.util.Iterator;

import interfaces.AbstractStack;

public class Stack<E> implements AbstractStack<E> {

	private Node<E> top;
	private int size;

	private static class Node<E> {
		private E value;
		private Node<E> next;
		private Node<E> previous;

		Node(E element) {
			this.value = element;
		}
	}

	public Stack() {
		this.size = 0;
		this.top = null;

	}

	@Override
	public void push(E element) {
		
		Node<E> nodeToInsert = new Node(element);
		nodeToInsert.next = top;
		top = nodeToInsert;
		

		this.size++;
	}

	@Override
	public E pop() {
		
		if(this.isEmpty()) {
			throw new IllegalStateException();
		}
		
		Node<E> tmp = top;
		top = tmp.next;
		this.size--;
		
		
		return tmp.value;
	}

	@Override
	public E peek() {
		
		if(this.isEmpty()) {
			throw new IllegalStateException();
		}
		
		return top.value;
		
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
			
			private Node<E> current = top;

			@Override
			public boolean hasNext() {

				// return this.current.next != null;  both work 
				return this.current != null;
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
