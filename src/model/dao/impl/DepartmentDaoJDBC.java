package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {

	private Connection conn;
	
	
	
	public DepartmentDaoJDBC(Connection conn) {		
		this.conn = conn;
	}

	@Override
	public void insert(Department obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO Department (name) VALUES (?)",Statement.RETURN_GENERATED_KEYS);
			st.setString(1,obj.getName());
			int rowsAffected = st.executeUpdate();			
			if(rowsAffected>0) {
				ResultSet rs=st.getGeneratedKeys();
				if(rs.next()) {
					obj.setId(rs.getInt(1));
				} else {
					throw new DbException("Inespacted error! No rows affected");
				}
				DB.closeResultSet(rs);
			}
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatment(st);
		}
		
	}

	@Override
	public void update(Department obj) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("UPDATE Department SET name=? where id=?");			
			st.setString(1,obj.getName());
			st.setInt(2,obj.getId());
			st.executeUpdate();
		}
		catch(SQLException e ) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatment(st);
		}
		
		
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st=conn.prepareStatement("DELETE FROM Department WHERE id=?");
			st.setInt(1,id);
			st.executeUpdate();
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatment(st);
		}
		
	}

	@Override
	public Department findByID(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT * FROM department WHERE id=?");
			st.setInt(1,id);
			rs = st.executeQuery();
			if(rs.next()) {
				return new Department(rs.getInt("id"),rs.getString("name"));
			}
			return null;
		}
		catch (SQLException e ){
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatment(st);
			DB.closeResultSet(rs);
		}		
	}

	@Override
	public List<Department> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Department> list = new ArrayList<Department>();
		try {
			st = conn.prepareStatement("SELECT * FROM Department");
			rs = st.executeQuery();
			while(rs.next()) {
				list.add(new Department(rs.getInt("id"),rs.getString("name")));
			}
			return list;			
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatment(st);
			DB.closeResultSet(rs);
		}
		
	}

}
