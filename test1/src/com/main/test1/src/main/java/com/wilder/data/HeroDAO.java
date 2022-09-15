package com.wilder.data;

import com.wilder.bean.Hero;

public interface HeroDAO extends GenericDAO<Hero> {
	public Hero getByName(String name);
}