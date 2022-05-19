package implementations;

import java.util.Arrays;
import java.util.Iterator;

import interfaces.Deque;

public class ArrayDeque<E> implements Deque<E> {

	private final int INITIAL_CAPACITY = 7;

	private int size;
	private int head;
	private int tail;

	private Object[] elements;

	public ArrayDeque() {
		this.elements = new Object[INITIAL_CAPACITY];
		this.head = this.tail = INITIAL_CAPACITY / 2;
	}

	@Override
	public void add(E element) {
		addLast(element);
	}

	@Override
	public void offer(E element) {
		addLast(element);

	}

	private Object[] grow() {

		int newCapacity = this.elements.length * 2 + 1;

		Object[] newElements = new Object[newCapacity];

		int middle = newCapacity / 2;

		int begin = middle - this.size / 2;

		int index = this.head;

		for (int i = begin; index <= this.tail; i++) {
			newElements[i] = this.elements[index++];
		}

		this.head = begin;
		this.tail = this.head + this.size - 1;

		return newElements;
	}

	@Override
	public void addFirst(E element) {
		if (this.size == 0) {
			this.addLast(element);
		} else {
			if (this.head == 0) {
				this.elements = grow();
			}

			this.elements[--this.head] = element;
			this.size++;
		}

	}

	@Override
	public void addLast(E element) {
		if (this.size == 0) {
			this.elements[this.tail] = element;
		} else {
			if (this.tail == this.elements.length - 1) {
				this.elements = grow();
			}
			this.elements[++this.tail] = element;
		}

		this.size++;

	}

	@Override
	public void push(E element) {
		addFirst(element);
	}

	@Override
	public void insert(int index, E element) {
		int realIndex = this.head + index;
		ensureIndex(realIndex);

		if (index < this.tail - realIndex) {
			insertAndShiftLeft(realIndex, element);
		} else {
			insertAndShiftRight(realIndex, element);
		}

	}

	private void insertAndShiftRight(int index, E element) {
		
		E lastElement = this.getAt(this.tail);
		for (int i = this.tail; i > index; i--) {
			this.elements[i] = this.elements[i-1];
			
		}
		
		this.elements[index] = element;
		this.addLast(lastElement);

	}

	private void insertAndShiftLeft(int index, E element) {
		
		E firstElement = this.getAt(this.head);
		
		if(this.head != index) {
			for (int i = this.head; i < index; i++) {
				this.elements[i] = this.elements[i+1];
				
			}
			
			this.elements[index-1] = element;
			this.addFirst(firstElement);
		}else {
			this.addFirst(element);
		}
		

		
		System.out.println();
		
		

	}

	@Override
	public void set(int index, E element) {
		int realIndex = this.head + index;
		ensureIndex(realIndex);
		this.elements[realIndex] = element;
		
	}

	@Override
	public E peek() {

		if (this.size != 0) {
			return getAt(this.head);
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	private E getAt(int index) {
		return (E) this.elements[index];
	}

	@Override
	public E poll() {

		return removeFirst();
	}

	@Override
	public E pop() {

		if (this.size != 0) {

		}

		return null;
	}

	@Override
	public E get(int index) {
		int realIndex = this.head + index;
		ensureIndex(realIndex);

		return getAt(realIndex);
	}

	@Override
	public E get(Object object) {
		
		if(isEmpty()) {
			return null;
		}

		for (int i = this.head; i <= this.tail; i++) {
			if (this.elements[i].equals(object)) {
				return getAt(i);
			}
		}

		return null;
	}

	@Override
	public E remove(int index) {

		int realIndex = this.head + index;
		ensureIndex(realIndex);
		
		E element = this.getAt(realIndex);
		
		if (index < size/2) {
			removeAndShiftRight(realIndex);			
		} else {
			removeAndShiftLeft(realIndex);
		}
		
		
		return element;

	}

	private void removeAndShiftLeft(int realIndex) {
		
		if(realIndex != this.tail) {
			for (int i = realIndex; i < this.tail; i++) {
				this.elements[i] = this.elements[i+1];
			} 
			removeLast();
		}else {
			removeLast();
		}
		
	}

	private void removeAndShiftRight(int realIndex) {
		
		if(realIndex != this.head) {
			for (int i = realIndex; i > this.head; i--) {
				this.elements[i] = this.elements[i-1];
			} 
			removeFirst();
		}else {
			removeFirst();
		}
		
		
	}

	private void ensureIndex(int realIndex) {

		if (realIndex < this.head || realIndex > this.tail) {
			throw new IndexOutOfBoundsException("IOB for index " + (realIndex - this.head));
		}

	}

	@Override
	public E remove(Object object) {
		
		if(isEmpty()) {
			return null;
		}
		
		for (int i = this.head; i <= this.tail; i++) {
			if(this.elements[i].equals(object)) {
				
				E element = getAt(i);
				this.remove(i-this.head);
				return element;
			}
		}

		return null;
	}

	@Override
	public E removeFirst() {

		if (!isEmpty()) {
			E element = this.getAt(this.head);
			this.elements[this.head] = null;
			this.head++;
			this.size--;
			return element;

		}

		return null;
	}

	@Override
	public E removeLast() {

		if (!isEmpty()) {
			E element = this.getAt(this.tail);
			this.elements[this.tail] = null;
			this.tail--;
			this.size--;
			return element;

		}

		return null;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public int capacity() {
		return this.elements.length;
	}

	@Override
	public void trimToSize() {
		
		this.elements = Arrays.copyOfRange(this.elements, this.head, this.size);

	}

	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {

			private int index = head;

			@Override
			public boolean hasNext() {

				return size > 0 && index <= tail;
			}

			@Override
			public E next() {

				return getAt(index++);
			}

		};
	}
}
