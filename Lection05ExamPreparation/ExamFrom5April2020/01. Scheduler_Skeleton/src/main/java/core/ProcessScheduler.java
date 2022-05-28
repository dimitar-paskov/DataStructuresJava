package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Task;
import shared.Scheduler;

public class ProcessScheduler implements Scheduler {

	List<Task> scheduleQueue = null;

	public ProcessScheduler() {
		this.scheduleQueue = new ArrayList<>();
	}

	@Override
	public void add(Task task) {
		this.scheduleQueue.add(task);

	}

	@Override
	public Task process() {

		if (!this.scheduleQueue.isEmpty()) {
			return this.scheduleQueue.remove(0);
		} else {
			return null;
		}

	}

	@Override
	public Task peek() {

		if (!this.scheduleQueue.isEmpty()) {
			return this.scheduleQueue.get(0);
		} else {
			return null;
		}
	}

	@Override
	public Boolean contains(Task task) {

		return this.scheduleQueue.contains(task);
	}

	@Override
	public int size() {
		
		return this.scheduleQueue.size();
	}

	@Override
	public Boolean remove(Task task) {

		int index = -1;
		boolean result = false;
		for (int i = 0; i < this.scheduleQueue.size(); i++) {
			if (this.scheduleQueue.get(i).equals(task)) {
//				if (this.scheduleQueue.get(i).getId() == task.getId()) {
				index = i;
				break;
			}
		}

		if (index != -1) {
			this.scheduleQueue.remove(index);
			result = true;
		} else {
			throw new IllegalArgumentException();
		}

		return result;
	}

	@Override
	public Boolean remove(int id) {

		int index = -1;
		boolean result = false;
		for (int i = 0; i < this.scheduleQueue.size(); i++) {
			if (this.scheduleQueue.get(i).getId() == id) {
				index = i;
				break;
			}
		}

		if (index != -1) {
			this.scheduleQueue.remove(index);
			result = true;
		} else {
			throw new IllegalArgumentException();
		}

		return result;
	}

	@Override
	public void insertBefore(int id, Task task) {

		int index = -1;

		for (int i = 0; i < this.scheduleQueue.size(); i++) {
			if (this.scheduleQueue.get(i).getId() == id) {
				index = i;
				break;
			}
		}

		if (index != -1) {

			this.scheduleQueue.add(index, task);

		} else {
			throw new IllegalArgumentException();
		}

	}

	@Override
	public void insertAfter(int id, Task task) {
		int index = -1;

		for (int i = 0; i < this.scheduleQueue.size(); i++) {
			if (this.scheduleQueue.get(i).getId() == id) {
				index = i;
				break;
			}
		}

		if (index != -1) {
			if (index + 1 >= this.scheduleQueue.size()) {
				this.scheduleQueue.add(task);
			} else {
				this.scheduleQueue.add(index + 1, task);
			}

		} else {
			throw new IllegalArgumentException();
		}

	}

	@Override
	public void clear() {
		this.scheduleQueue.clear();

	}

	@Override
	public Task[] toArray() {
		
		return this.scheduleQueue.toArray(new Task[0]);
		
	}

	@Override
	public void reschedule(Task first, Task second) {
		
		
		int firstFoundIndex = -1;
		int secondFoundIndex = -1;
		for (int i = 0; i < this.scheduleQueue.size(); i++) {
			if (this.scheduleQueue.get(i).equals(first)) {
				firstFoundIndex = i;
			}
			
			if (this.scheduleQueue.get(i).equals(second)) {
				secondFoundIndex = i;
			}
			
			if(firstFoundIndex != -1 && secondFoundIndex != -1) {
				break;
			}
		}
		
		if(firstFoundIndex == -1 || secondFoundIndex == -1) {
			throw new IllegalArgumentException();
		}

		Collections.swap(scheduleQueue, firstFoundIndex, secondFoundIndex); 


	}

	@Override
	public List<Task> toList() {
		
		return this.scheduleQueue;
	}

	@Override
	public void reverse() {
		Collections.reverse(scheduleQueue); 

	}

	@Override
	public Task find(int id) {
		
		
		Task result = null;
		for (int i = 0; i < this.scheduleQueue.size(); i++) {
			if (this.scheduleQueue.get(i).getId() == id) {
				result = this.scheduleQueue.get(i);
				break;
			}
		}

		if (result!= null) {
			return result;
		} else {
			throw new IllegalArgumentException();
		}

	}

	@Override
	public Task find(Task task) {
		

		Task result = null;
		for (int i = 0; i < this.scheduleQueue.size(); i++) {
			if (this.scheduleQueue.get(i).equals(task)) {
				result = this.scheduleQueue.get(i); 
				break;
			}
		}

		if (result != null) {
			return result;
		} else {
			throw new IllegalArgumentException();
		}

	}

}

//import java.util.ArrayDeque;
//import java.util.ArrayList;
//import java.util.Deque;
//import java.util.Iterator;
//import java.util.List;
//
//import model.Task;
//import shared.Scheduler;
//
//public class ProcessScheduler implements Scheduler {
//
//	private Deque<Task> scheduler;
//
//	public ProcessScheduler() {
//		this.scheduler = new ArrayDeque<>();
//	}
//
//	@Override
//	public void add(Task task) {
//
//		this.scheduler.offer(task);
//
//	}
//
//	@Override
//	public Task process() {
//		return this.scheduler.poll();
//	}
//
//	@Override
//	public Task peek() {
//		return this.scheduler.peek();
//	}
//
//	@Override
//	public Boolean contains(Task task) {
//		return this.scheduler.contains(task);
//	}
//
//	@Override
//	public int size() {
//		return this.scheduler.size();
//	}
//
//	@Override
//	public Boolean remove(Task task) {
//
//		return this.scheduler.remove(task);
//
//	}
//
//	@Override
//	public Boolean remove(int id) {
//
//		boolean result = false;
//		Task task = null;
//
//		for (Iterator<Task> iterator = this.scheduler.iterator(); iterator.hasNext();) {
//			task = (Task) iterator.next();
//			if (task.getId() == id) {
//				result = true;
//				break;
//			}
//
//		}
//
//		this.remove(task);
//		return result;
//
//	}
//
//	@Override
//	public void insertBefore(int id, Task task) {
//
//		Task taskToInsertBefore = this.find(id);
//
//		Deque<Task> newScheduler = new ArrayDeque<>();
//
//		while (!this.scheduler.isEmpty()) {
//
//			Task current = this.scheduler.poll();
//
//			if (current.equals(taskToInsertBefore)) {
//
//				newScheduler.offer(task);
//				newScheduler.offer(current);
//
//			} else {
//				newScheduler.offer(current);
//			}
//
//		}
//
//		this.scheduler = newScheduler;
//
//	}
//
//	@Override
//	public void insertAfter(int id, Task task) {
//
//		Task taskToInsertAfter = this.find(id);
//
//		Deque<Task> newScheduler = new ArrayDeque<>();
//
//		while (!this.scheduler.isEmpty()) {
//
//			Task current = this.scheduler.poll();
//
//			if (current.equals(taskToInsertAfter)) {
//
//				newScheduler.offer(current);
//				newScheduler.offer(task);
//
//			} else {
//				newScheduler.offer(current);
//			}
//
//		}
//
//		this.scheduler = newScheduler;
//	}
//
//	@Override
//	public void clear() {
//		this.scheduler.clear();
//	}
//
//	@Override
//	public Task[] toArray() {
//		return this.scheduler.toArray(Task[]::new);
//	}
//
//	@Override
//	public void reschedule(Task first, Task second) {
//		
//		
//		Task current = null;
//		Task foundFirst = null;
//		Task foundSecond = null;
//
//		for (Iterator<Task> iterator = this.scheduler.iterator(); iterator.hasNext();) {
//			current = (Task) iterator.next();
//
//			if (current.equals(first)) {
//
//				foundFirst = current;
//
//			}
//			
//			if (current.equals(second)) {
//
//				foundSecond = current;
//
//			}
//			
//			if(foundFirst != null && foundSecond != null) {
//				break;
//			}
//
//		}
//
//		if (foundFirst == null || foundSecond == null) {
//			throw new IllegalArgumentException();
//		}
//		
//		Deque<Task> newScheduler = new ArrayDeque<>();
//		
//
//		while(!this.scheduler.isEmpty()) {
//			
//			current = this.scheduler.poll();
//			
//			if(current.equals(foundFirst)) {
//				
//				newScheduler.offer(foundSecond);
//			}else if(current.equals(foundSecond)) {
//				newScheduler.offer(foundFirst);
//			}else {
//				newScheduler.offer(current);
//			}
//
//		}
//
//		
//		this.scheduler = newScheduler;
//		
//
//	}
//
//	@Override
//	public List<Task> toList() {
//
//		List<Task> result = new ArrayList<>();
//
//		for (Iterator<Task> iterator = this.scheduler.iterator(); iterator.hasNext();) {
//			Task task = (Task) iterator.next();
//			result.add(task);
//
//		}
//
//		return result;
//	}
//
//	@Override
//	public void reverse() {
//		Deque<Task> newScheduler = new ArrayDeque<>();
//
//		while (!this.scheduler.isEmpty()) {
//			newScheduler.offer(this.scheduler.pop());
//		}
//
//		this.scheduler = newScheduler;
//
//	}
//
//	@Override
//	public Task find(int id) {
//
//		Task task = null;
//
//		for (Iterator<Task> iterator = this.scheduler.iterator(); iterator.hasNext();) {
//			task = (Task) iterator.next();
//			if (task.getId() == id) {
//				break;
//			}
//
//		}
//
//		if (task == null) {
//			throw new IllegalArgumentException();
//		}
//
//		return task;
//	}
//
//	@Override
//	public Task find(Task task) {
//
//		Task current = null;
//		Task result = null;
//
//		for (Iterator<Task> iterator = this.scheduler.iterator(); iterator.hasNext();) {
//			current = (Task) iterator.next();
//
//			if (current.equals(task)) {
//
//				result = current;
//
//				break;
//			}
//
//		}
//
//		if (result == null) {
//			throw new IllegalArgumentException();
//		}
//
//		return result;
//	}
//}
