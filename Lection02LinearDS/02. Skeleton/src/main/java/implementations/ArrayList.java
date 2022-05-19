package implementations;

import java.util.Arrays;
import java.util.Iterator;

import interfaces.List;

public class ArrayList<E> implements List<E> {

	private static final int INITIAL_SIZE = 4;
	private Object[] elements;
	private int size;

	public ArrayList() {
		this.elements = new Object[INITIAL_SIZE];
		this.size = 0;
	}

	@Override
	public boolean add(E element) {

		if (this.size == this.elements.length  ) {
			this.elements = grow();
		}

		this.elements[this.size++] = element;
		
		return true;
	}

	private Object[] grow() {

		return Arrays.copyOf(this.elements, this.elements.length*2);
		
		
	}

	@Override
	public boolean add(int index, E element) {
		
		checkIndex(index);
		
		if (this.size == this.elements.length  ) {
			this.elements = grow();
		}
		
		
		for (int i = this.size; i > index; i--) {
			this.elements[i] = this.elements[i-1];			
		}
		
		this.elements[index] = element;
		this.size++;
		
		return true;
	}

	private void checkIndex(int index) {

		if(index < 0   || index >= this.size) {
			throw new IndexOutOfBoundsException("Can not get index " + index + " on ArrayList with size " + this.size);
		}
	}



	@Override
	public E get(int index) {
		
		checkIndex(index);
		return (E) this.elements[index];
		
		
		 
	}

	@Override
	public E set(int index, E element) {
		checkIndex(index);
		E oldElement = (E)this.elements[index];
		this.elements[index] = element;
		return oldElement;
		
	}

	@Override
	public E remove(int index) {
		checkIndex(index);
		E element = (E)this.elements[index];
		
		for (int i = index; i < this.size-1  ; i++) {
			this.elements[i] = this.elements[i+1]; 
		}
		
		this.size--;
		ensureCapacity();
		return element;
	}

	private void ensureCapacity() {
		
		if(this.size < this.elements.length / 3) {
			this.elements = shrink();
		}
		
	}

	private Object[] shrink() {
		
		return Arrays.copyOf(this.elements, this.elements.length/2); 
	}

	@Override
	public int size() {
		return this.size; 
	}

	@Override
	public int indexOf(E element) {
		for (int i = 0; i < this.size; i++) {
			if(this.elements[i].equals(element)) {
				return i;
			}
		}
		
		return -1;
	}

	@Override
	public boolean contains(E element) {
		
		
		for (int i = 0; i < this.size; i++) {
			if(this.elements[i].equals(element)) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public boolean isEmpty() {
		return this.size <= 0; 
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {

			private int index = 0;
			@Override
			public boolean hasNext() {
				return this.index < size();
			}

			@Override
			public E next() {
				return get(index++);
			}
			
		};
	}
}
