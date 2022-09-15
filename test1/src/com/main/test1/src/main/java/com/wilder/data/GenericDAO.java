package com.wilder.data;

import java.util.Set;

public interface GenericDAO<T> {
	public int create(T dataToAdd);
	public T getById(int id);
	public Set<T> getAll();
	public void update(T dataToUpdate);
	public void delete(T dataToDelete);
}
