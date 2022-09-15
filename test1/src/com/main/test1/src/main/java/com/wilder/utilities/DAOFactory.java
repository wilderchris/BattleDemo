package com.wilder.utilities;

import com.wilder.data.HeroDAO;
import com.wilder.data.postgres.HeroPostgres;

public class DAOFactory {

	
	public static HeroDAO getHeroDAO() {
		return new HeroPostgres();
		
	}
}
