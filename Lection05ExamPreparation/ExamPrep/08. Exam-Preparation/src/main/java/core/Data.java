package core;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

import interfaces.Entity;
import interfaces.Repository;

public class Data implements Repository {

	private final Queue<Entity> data;

	public Data() {
		this.data = new PriorityQueue<>();
	}

	public Data(Data other) {
		this.data = new PriorityQueue<>(other.data);
	}

	@Override
	public void add(Entity entity) {

//		if (entity.getId() > 0) {
//
//			Entity parent = this.getById(entity.getParentId());
//
//			parent.addChild(entity);
//		}

		this.data.offer(entity);

	}

	@Override
	public Entity getById(int id) {

		for (Entity entity : data) {
			if (entity.getId() == id) {
				return entity;
			}
		}

		return null;
	}

	@Override
	public List<Entity> getByParentId(int id) {
		
		List<Entity> result = new ArrayList<>();

		for (Entity entity : data) {
			
			if(entity.getParentId() == id) {
				result.add(entity);
			}
		}
		
		return result;
		
		

//		List<Entity> result = new ArrayList<>();
//
//		Entity parent = this.getById(id);
//
//		if (parent != null) {
//			result.addAll(parent.getChildren());
//		}
//
//		return result;
	}

	@Override
	public List<Entity> getAll() {

		return new ArrayList<>(this.data);
	}

	@Override
	public Repository copy() {
		
		return new Data(this);
	}

	@Override
	public List<Entity> getAllByType(String type) {
		
		boolean isLocalModel = type.equals("Invoice") || 
				type.equals("StoreClient") ||
				type.equals("User");
		
		if(!isLocalModel) {
			
			throw new  IllegalArgumentException ("Illegal type: " + type);
			
		}
		
		return this.data.stream()
		.filter(e -> e.getClass().getSimpleName().equals(type))
		.collect(Collectors.toList());

	}

	@Override
	public Entity peekMostRecent() {
		
		if(this.data.isEmpty()) {
			throw new IllegalStateException("Operation on empty Data");
		}
		
		return this.data.peek();
	}

	@Override
	public Entity pollMostRecent() {
		
		Entity result = this.data.poll();
		
		if(result == null) {
			throw new IllegalStateException("Operation on empty Data");
		}
		
		return result;

//		if(this.data.isEmpty()) {
//			throw new IllegalStateException("Operation on empty Data");
//		}
//		
//		return this.data.poll();
	}

	@Override
	public int size() {

		return this.data.size();
	}

}






// //my solution   200ms vs 20ms for the solution with PriorityQueue
//import java.util.ArrayList;
//import java.util.List;
//
//import interfaces.Entity;
//import interfaces.Repository;
//
//public class Data implements Repository {
//
//	private final List<Entity> elements;
//	private final List<String> availableTypes;
//
//	public Data() {
//		this.elements = new ArrayList<>();
//		this.availableTypes = new ArrayList<>();
//	}
//
//	@Override
//	public void add(Entity entity) {
//		
//		String availableType = entity.getClass().getSimpleName();
//		if(!this.availableTypes.contains(availableType)) {
//			availableTypes.add(availableType);
//		}
//		
//		this.elements.add(entity);
//	}
//
//	@Override
//	public Entity getById(int id) {
//
//		for (int i = 0; i < this.elements.size(); i++) {
//			if (this.elements.get(i).getId() == id) {
//				return this.elements.get(i);
//			}
//		}
//
//		return null;
//	}
//
//	@Override
//	public List<Entity> getByParentId(int id) {
//
//		List<Entity> result = new ArrayList<>();
//
//		for (int i = 0; i < this.elements.size(); i++) {
//			if (this.elements.get(i).getParentId() == id) {
//				result.add(this.elements.get(i));
//			}
//		}
//		
//		return result;
//	}
//
//	@Override
//	public List<Entity> getAll() {
//		return this.elements;
//	}
//
//	@Override
//	public Repository copy() {
//				
//		return this;
//	}
//
//	@Override
//	public List<Entity> getAllByType(String type) {
//		
//		
//		if(!this.availableTypes.contains(type)) {
//			throw new IllegalArgumentException ("Illegal type: " + type);
//		}
//		
//		
//		List<Entity> result = new ArrayList<>();
//		
//		for (int i = 0; i < this.elements.size(); i++) {
//			if(this.elements.get(i).getClass().getSimpleName().equals(type)) {
//				result.add(this.elements.get(i));
//				
//			}
//		}
//		
//		return result;
//	}
//
//	@Override
//	public Entity peekMostRecent() {
//		
//
//		int maxIndex = findMaxIndex();
//
//		Entity result = this.elements.get(maxIndex);
//
//		return result;
//	}
//
//	@Override
//	public Entity pollMostRecent() {
//
//		int maxIndex = findMaxIndex();
//
//		Entity result = this.elements.get(maxIndex);
//
//		this.elements.remove(maxIndex);
//
//		return result;
//	}
//
//	private int findMaxIndex() {
//		
//		if(this.elements.isEmpty()) {
//			throw new IllegalStateException("Operation on empty Data");
//		}
//		
//		int maxIndex = -1;
//		int maxId = Integer.MIN_VALUE;
//		for (int i = 0; i < this.elements.size(); i++) {
//			if (this.elements.get(i).getId() > maxId) {
//				maxId = this.elements.get(i).getId();
//				maxIndex = i;
//			}
//		}
//		return maxIndex;
//	}
//
//	@Override
//	public int size() {
//		return this.elements.size();
//	}
//}
