//package solutions;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//import interfaces.Decrease;
//import interfaces.Heap;
//
//public class MinHeap<E extends Comparable<E> & Decrease<E>> implements Heap<E> {
//
//	List<E> elements;
//
//	public MinHeap() {
//		this.elements = new ArrayList<E>();
//	}
//
//	@Override
//	public int size() {
//		return this.elements.size();
//	}
//
//	@Override
//	public void add(E element) {
//		this.elements.add(element);
//		this.heapifyUp(this.elements.size() - 1);
//
//	}
//
//	private void heapifyUp(int index) {
//
//		int parentIndex = getParentIndex(index);
//
//		while (isLess(index, parentIndex)) {
//			Collections.swap(elements, index, parentIndex);
//			index = parentIndex;
//			parentIndex = getParentIndex(index);
//
//		}
//
//	}
//
//	@Override
//	public E peek() {
//
//		ensureNotEmpty();
//
//		return this.elements.get(0);
//	}
//
//	@Override
//	public E poll() {
//
//		ensureNotEmpty();
//
//		E result = this.elements.get(0);
//
//		Collections.swap(elements, 0, elements.size() - 1);
//		this.elements.remove(this.elements.size() - 1);
//		this.heapifyDown();
//
//		return result;
//	}
//
//	private void heapifyDown() {
//		int index = 0;
//		int leftChildIndex = getLeftChildIndex(index);
//		int rightChildIndex = getRightChildIndex(index);
//
//		while (isGreater(index, leftChildIndex) || isGreater(index, rightChildIndex)) {
//
//			if (isGreater(leftChildIndex, rightChildIndex)) {
//				Collections.swap(elements, index, leftChildIndex);
//				index = leftChildIndex;
//				leftChildIndex = getLeftChildIndex(index);
//				rightChildIndex = getRightChildIndex(index);
//			}
//
//			if (isGreater(rightChildIndex, leftChildIndex)) {
//				Collections.swap(elements, index, rightChildIndex);
//				index = rightChildIndex;
//				leftChildIndex = getLeftChildIndex(index);
//				rightChildIndex = getRightChildIndex(index);
//			}
////			if (index >= 0 && index < this.elements.size() && leftChildIndex >= 0
////					&& leftChildIndex < this.elements.size() && rightChildIndex >= 0
////					&& rightChildIndex < this.elements.size()) {
////			} else {
////				break;
////			}
//		}
//
//	}
//
//	@Override
//	public void decrease(E element) {
//
//		int index = this.elements.indexOf(element);
//
//		E foundElement = this.elements.get(index);
//
//		foundElement.decrease();
//
//		this.heapifyUp(index);
//
//	}
//
//	private boolean isLess(int index, int parentIndex) {
//
//		return (this.elements.get(index).compareTo(this.elements.get(parentIndex)) < 0);
//	}
//
//	private boolean isGreater(int index, int parentIndex) {
//
//		return (this.elements.get(index).compareTo(this.elements.get(parentIndex)) > 0);
//	}
//
//	private boolean areEqual(int index, int parentIndex) {
//
//		return (this.elements.get(index).compareTo(this.elements.get(parentIndex)) == 0);
//	}
//
//	private int getParentIndex(int index) {
//
//		return (index - 1) / 2;
//	}
//
//	private int getRightChildIndex(int index) {
//		// TODO Auto-generated method stub
//		return 2 * index + 2;
//	}
//
//	private int getLeftChildIndex(int index) {
//
//		return 2 * index + 1;
//	}
//
//	private void ensureNotEmpty() {
//		if (this.elements.size() == 0) {
//			throw new IllegalStateException();
//		}
//	}
//}








package solutions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import interfaces.Decrease;
import interfaces.Heap;

public class MinHeap<E extends Comparable<E> & Decrease<E>> implements Heap<E> {

	List<E> data;

	public MinHeap() {
		this.data = new ArrayList<>();
	}

	@Override
	public int size() {
		return this.data.size();
	}

	@Override
	public void add(E element) {
		this.data.add(element);
		this.heapifyUp(this.data.size() - 1);

	}

	private void heapifyUp(int index) {

		int parentIndex = this.getParentIndexFor(index);

		while (index > 0 && isLess(index, parentIndex)) {

			Collections.swap(data, index, parentIndex);
			index = parentIndex;
			parentIndex = this.getParentIndexFor(index);

		}

	}

	private boolean isLess(int first, int second) {

		return this.data.get(first).compareTo(this.data.get(second)) < 0;
	}

	@Override
	public E peek() {

		ensureNotEmpty();

		return this.data.get(0);
	}

	@Override
	public E poll() {

		ensureNotEmpty();

		Collections.swap(data, 0, this.data.size() - 1);
		E toReturn = data.remove(this.data.size() - 1);

		this.heapifyDown();

		return toReturn;
	}

	private void heapifyDown() {

		int index = 0;
		int swapIndex = this.getLeftChildIndex(index);

		while (swapIndex < this.data.size()) {



			int rightChildIndex = this.getRightChildIndex(index);

			if (rightChildIndex < this.data.size()) {
				swapIndex = isLess(swapIndex, rightChildIndex) ? swapIndex : rightChildIndex;
			}

			if (isLess(index, swapIndex)) {
				break;
			}

			Collections.swap(data, index, swapIndex);

			index = swapIndex;
			swapIndex = this.getLeftChildIndex(index);

		}

	}

	

	@Override
	public void decrease(E element) {
		
		int elementIndex = this.data.indexOf(element);
		
		E heapElement = this.data.get(elementIndex);
		
		heapElement.decrease();
		
		this.heapifyUp(elementIndex);
		
		

	}

	private void ensureNotEmpty() {
		if (this.data.isEmpty()) {
			throw new IllegalStateException();
		}
	}

	private int getParentIndexFor(int index) {

		return (index - 1) / 2;
	}

	private int getLeftChildIndex(int index) {

		return 2 * index + 1;
	}

	private int getRightChildIndex(int index) {

		return 2 * index + 2;
	}
	
	
}
