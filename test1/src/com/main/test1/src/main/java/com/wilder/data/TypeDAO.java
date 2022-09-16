package com.wilder.data;

import com.wilder.bean.Type;

public interface TypeDAO extends GenericDAO<Type> {
	public Type getByType(String type);
}