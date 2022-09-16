

package com.wilder.data.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.wilder.bean.Type;
import com.wilder.data.TypeDAO;
import com.wilder.utilities.ConnectionUtil;

public class TypePostgres implements TypeDAO {

	private ConnectionUtil connUtil = ConnectionUtil.getConnectionUtil();
	
	
	
	@Override
	public int create(Type dataToAdd) {
		int generatedId = 0;
		
		try (Connection conn = connUtil.getConnection()){
			conn.setAutoCommit(false);
			String[] keys = {"id"};
			String sql="insert into public.type"
					+ " type,"
					+ " attack,"
					+ " defense,"
					+ " magic,"
					+ " health)";
			PreparedStatement pStmt = conn.prepareStatement(sql,keys);
			pStmt.setString(1, dataToAdd.getType());
			pStmt.setInt(2, dataToAdd.getAttack());
			pStmt.setInt(3, dataToAdd.getDefense());
			pStmt.setInt(4, dataToAdd.getMagic());
			
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
	public Type getById(int id) {
		Type type = new Type();
		return type;
	}

	@Override
	public Set<Type> getAll() {
		Set<Type> types = new HashSet<Type>();

		
		try (Connection conn = connUtil.getConnection()) {
			String sql = "select id,"
					+ " type,"
					+ " attack,"
					+ " defense,"
					+ " magic,"
					+ " health"
					+ " from public.type"; //join public.type on (employee.role_id = user_role.role_id))";
			Statement stmt = conn.createStatement();
			
			ResultSet resultSet = stmt.executeQuery(sql);
			
			while (resultSet.next()) {
				Type type = new Type();
				type.setId(resultSet.getInt("id"));
				type.setType(resultSet.getString("type"));
				type.setAttack(resultSet.getInt("attack"));
				type.setDefense(resultSet.getInt("defense"));
				type.setMagic(resultSet.getInt("magic"));
				type.setHealth(resultSet.getInt("health"));
								
				types.add(type);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
		return types;
	}

	@Override
	public void update(Type dataToUpdate) {


		
	}

	@Override
	public void delete(Type dataToDelete) {


		
	}

	@Override
	public Type getByType(String type) {
		// TODO Auto-generated method stub
		return null;
	}

}
