package core;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import interfaces.Buffer;
import interfaces.Entity;
import model.BaseEntity.Status;

public class Loader implements Buffer {

	private final List<Entity> data;

	public Loader() {
		this.data = new ArrayList<>();
	}

	

	@Override
	public void add(Entity entity) {
		this.data.add(entity);

	}

	@Override
	public Entity extract(int id) {

		Entity result = null;

		for (Entity entity : this.data) {
			if (entity.getId() == id) {
				result = entity;
				break;
			}
		}

		if (result != null) {
			this.data.remove(result);
		}

		return result;
	}

	@Override
	public Entity find(Entity entity) {

		int index = this.data.indexOf(entity);

		if (index < 0) {
			return null;
		}

		return this.data.get(index);
	}

	@Override
	public boolean contains(Entity entity) {

		return this.data.contains(entity);
	}

	@Override
	public int entitiesCount() {
		return this.data.size();
	}

	@Override
	public void replace(Entity oldEntity, Entity newEntity) {

		int oldIndex = this.data.indexOf(oldEntity);

		if (oldIndex < 0) {
			throw new IllegalStateException("Entity not found");
		}

		this.data.set(oldIndex, newEntity);

	}

	@Override
	public void swap(Entity first, Entity second) {

		int firstIndex = this.data.indexOf(first);
		if (firstIndex < 0) {
			throw new IllegalStateException("Entities not found");
		}

		int secondIndex = this.data.indexOf(second);
		if (secondIndex < 0) {
			throw new IllegalStateException("Entities not found");
		}

		Collections.swap(this.data, firstIndex, secondIndex);

	}

	@Override
	public void clear() {
		this.data.clear();

	}

	@Override
	public Entity[] toArray() {

		Entity[] result = new Entity[this.data.size()];

		this.data.toArray(result);

		return result;
	}

	@Override
	public List<Entity> retainAllFromTo(Status lowerBound, Status upperBound) {

		return this.data.stream()
		.filter(e -> e.getStatus().ordinal() >= lowerBound.ordinal()) 
		.filter(e -> e.getStatus().ordinal() <= upperBound.ordinal()) 
		.collect(Collectors.toList());
		
	}

	@Override
	public List<Entity> getAll() {
		
		return new ArrayList<>(this.data);
	}
	
	@Override
	public void updateAll(Status oldStatus, Status newStatus) {
		
		for (Entity entity : data) {
			if(entity.getStatus() == oldStatus) {
				 
				entity.setStatus(newStatus);
			}
		}

	}

	@Override
	public void removeSold() {
		
		this.data.removeIf(e -> e.getStatus() == Status.SOLD);
		

	}


	
	@Override
	public Iterator<Entity> iterator() {
		
		return this.data.iterator();
	}


//////HashMap solution - Performance not OK (for swap method) - 8897 ms and it should be less than 450 ms
////
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//import interfaces.Buffer;
//import interfaces.Entity;
//import model.BaseEntity;
//
//
//public class Loader implements Buffer {
//
//	private Map<Integer, Entity> elements;
//
//	public Loader() {
//		this.elements = new HashMap<>();
//	}
//
//	@Override
//	public void add(Entity entity) {
//
//		this.elements.put(entity.getId(), entity);
//	}
//
//	@Override
//	public Entity extract(int id) {
//
//		return this.elements.remove(id);
//	}
//
//	@Override
//	public Entity find(Entity entity) {
//
//		return this.elements.get(entity.getId());
//
//	}
//
//	@Override
//	public boolean contains(Entity entity) {
//		return this.elements.containsKey(entity.getId());
//	}
//
//	@Override
//	public int entitiesCount() {
//		return this.elements.keySet().size();
//	}
//
//	@Override
//	public void replace(Entity oldEntity, Entity newEntity) {
//
//		if (!this.elements.containsKey(oldEntity.getId())) {
//			throw new IllegalStateException("Entity not found");
//		} else {
//			this.elements.remove(oldEntity.getId());
//			this.elements.put(newEntity.getId(), newEntity);
//		}
//
//	}
//
//	@Override
//	public void swap(Entity first, Entity second) {
//		if (!this.elements.containsKey(first.getId())) {
//			throw new IllegalStateException("Entities not found");
//		}
//
//		if (!this.elements.containsKey(second.getId())) {
//			throw new IllegalStateException("Entities not found");
//		}
//		
//
//
//		Map<Integer, Entity> result = new HashMap<>();
//
//		for (Integer key : this.elements.keySet()) {
//
//			if (key == first.getId()) {
//				result.put(second.getId(), second);
//			}
//
//			if (key == second.getId()) {
//				result.put(first.getId(), first);
//			}
//
//			result.put(key, this.elements.get(key));
//
//		}
//
//		this.elements = result;
//
//	}
//
//	@Override
//	public void clear() {
//		this.elements = new HashMap<>();
//
//	}
//
//	@Override
//	public Entity[] toArray() {
//
//		return this.elements.values().toArray(Entity[]::new);
//	}
//
//	@Override
//	public List<Entity> retainAllFromTo(BaseEntity.Status lowerBound, BaseEntity.Status upperBound) {
//
//		int lowerLimit = lowerBound.ordinal();
//		int upperLimit = upperBound.ordinal();
//
//		List<Entity> result = new ArrayList<>();
//
//		this.elements.values().stream()
//				.filter(e -> e.getStatus().ordinal() >= lowerLimit && e.getStatus().ordinal() <= upperLimit)
//				.forEach(e -> result.add(e));
//
//		return result;
//	}
//
//	@Override
//	public List<Entity> getAll() {
//		return this.elements.values().stream().collect(Collectors.toList());
//	}
//
//	@Override
//	public void updateAll(BaseEntity.Status oldStatus, BaseEntity.Status newStatus) {
//		this.elements.values().stream().filter(e -> e.getStatus().equals(oldStatus))
//				.forEach(e -> e.setStatus(newStatus));
//	}
//
//	@Override
//	public void removeSold() {
//		this.elements.values().removeIf(e -> e.getStatus().equals(BaseEntity.Status.SOLD));
//	}
//
//	@Override
//	public Iterator<Entity> iterator() {
//		return this.elements.values().iterator();
//	}
}
