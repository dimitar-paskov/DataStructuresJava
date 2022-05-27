package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import interfaces.Entity;

public abstract class BaseEntity implements Entity {

    public enum Status {
        IN_STORE,
        RESERVED,
        PENDING_FUNDS,
        PAYED,
        SOLD
    }

    private int id;
    private int parentId;
    private List<Entity> children;
    private Status status;

    public BaseEntity(int id, int parentId) {
        this.id = id;
        this.parentId = parentId;
        this.children = new ArrayList<>();
        this.status = Status.IN_STORE;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public int getParentId() {
        return this.parentId;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setParentId(Integer parentId) {
        this.parentId = parentId == null ? 0 : parentId;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public List<Entity> getChildren() {
        return children;
    }

    @Override
    public void addChild(Entity child) {
        this.children.add(child);
    }

    @Override
    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public int compareTo(Entity other) {
        return other.getId() - this.id;
    }

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		
		Class<?> thisParentClass = getClass().getSuperclass();
		Class<?> ogjParentClass =  obj.getClass().getSuperclass();
		

		if( !thisParentClass.equals(ogjParentClass)) {
			return false;
		}
		
		
		BaseEntity other = (BaseEntity) obj;
		return id == other.id;
	}
    
    
}
