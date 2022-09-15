

package com.wilder.data.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.wilder.bean.Hero;
import com.wilder.data.HeroDAO;
import com.wilder.utilities.ConnectionUtil;

public class HeroPostgres implements HeroDAO {

	private ConnectionUtil connUtil = ConnectionUtil.getConnectionUtil();
	
	
	
	@Override
	public int create(Hero dataToAdd) {
		int generatedId = 0;
		
		try (Connection conn = connUtil.getConnection()){
			conn.setAutoCommit(false);
			String[] keys = {"id"};
			String sql="insert into battleduel.hero"
					+ " (name,"
					+ " type,"
					+ " attack,"
					+ " defense,"
					+ " magic)";
			PreparedStatement pStmt = conn.prepareStatement(sql,keys);
			pStmt.setString(1, dataToAdd.getName());
			pStmt.setString(2, dataToAdd.getType());
			pStmt.setInt(3, dataToAdd.getAttack());
			pStmt.setInt(4, dataToAdd.getDefense());
			pStmt.setInt(5, dataToAdd.getMagic());
			
			pStmt.executeUpdate();
			ResultSet generatedKeys = pStmt.getGeneratedKeys();
			
			if( generatedKeys.next()) {
				generatedId = generatedKeys.getInt(1);
				conn.commit();
			}else {
				conn.rollback();
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		return generatedId;
	}

	@Override
	public Hero getById(int id) {
		Hero hero = new Hero();
		return hero;
	}

	@Override
	public Set<Hero> getAll() {
		Set<Hero> heros = new HashSet<Hero>();

		return heros;
	}

	@Override
	public void update(Hero dataToUpdate) {


		
	}

	@Override
	public void delete(Hero dataToDelete) {


		
	}

	@Override
	public Hero getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
