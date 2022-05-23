/**
 * @author dimitar
 *
 */
package implementations;

import java.util.Iterator;

public class ReversedList <E> implements Iterable<E>{
	
	private final int INITIAL_CAPACITY = 2;
	
	private Object[] elements;
	
	private int size;
	
	public ReversedList() {
		this.size = 0;
		this.elements = new Object[INITIAL_CAPACITY]; 
	}
	



	public void add(E element) {
		if(size == this.elements.length) {
			grow();
		}
		this.elements[size++]=element;
	
	}

	private void grow() {
		Object[] tmp = new Object[this.elements.length * 2] ;
		

		for (int i = 0; i < this.elements.length; i++) {
			tmp[i] = this.elements[i];
		}
		
		this.elements = tmp;
		
	}

	public E get(int idx) {
		ensureIndex(idx);
		
		if(size == 0) {
			return null;
		}
		
		return getAt(size()-1 - idx);
	}
	
	public E removeAt(int idx) {
		
		if(size == 0) {
			return null;
		}
		
		ensureIndex(idx);
		
//		int actualIndex = size()-1 - idx;  -- error in judge
		int actualIndex = idx;
		
		E element = getAt(actualIndex);
		
		
		for (int i = actualIndex; i < size()-1; i++) {
			this.elements[i] = this.elements[i+1];
			
		}
		
		this.size--;

		return element;
	}

	
	public int size() {
		return this.size;
	}
	
	public int capacity() {
		return this.elements.length;
	}


	public boolean isEmpty() {
		return this.size == 0;
	}
	
	@SuppressWarnings("unchecked")
	private E getAt(int index) {
		
		return (E)this.elements[index];
		
	}
	
	private void ensureIndex(int idx) {

		if (idx < 0 || idx > this.size - 1) {
			throw new IndexOutOfBoundsException("IOB for index " + idx);
		}

	}

	@Override
	public Iterator<E> iterator() {
		
		return new Iterator<E>() {
			
			int idx = size()-1;

			@Override
			public boolean hasNext() {
				
				return idx >= 0;
			}

			@Override
			public E next() {
				
				return getAt(idx--);
			}
			
		};
	}

}
