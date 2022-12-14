

package com.wilder.data.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
			String sql="insert into public.hero"
					+ " (name,"
					+ " type,"
					+ " attack,"
					+ " defense,"
					+ " magic,"
					+ " health)";
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

		
		try (Connection conn = connUtil.getConnection()) {
			String sql = "select id,"
					+ " name,"
					+ " type,"
					+ " attack,"
					+ " defense,"
					+ " magic,"
					+ " health"
					+ " from public.hero"; //join public.type on (employee.role_id = user_role.role_id))";
			Statement stmt = conn.createStatement();
			
			ResultSet resultSet = stmt.executeQuery(sql);
			
			while (resultSet.next()) {
				Hero hero = new Hero();
				hero.setId(resultSet.getInt("id"));
				hero.setName(resultSet.getString("name"));
				hero.setType(resultSet.getString("type"));
				hero.setAttack(resultSet.getInt("attack"));
				hero.setDefense(resultSet.getInt("defense"));
				hero.setMagic(resultSet.getInt("magic"));
				hero.setHealth(resultSet.getInt("health"));
								
				heros.add(hero);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
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
