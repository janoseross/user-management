package com.regi.usermanagement;


import java.util.List;

public class PostgreSqlAccessObject implements DatabaseAccessObject{

	public PostgreSqlAccessObject() {
		
	}
	
	@Override
	public boolean connect() {
		return false;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUser(User u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		
	}
}
