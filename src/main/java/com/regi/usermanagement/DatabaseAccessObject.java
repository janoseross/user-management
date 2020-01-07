package com.regi.usermanagement;

import java.util.List;

public interface DatabaseAccessObject {
	public boolean connect();
	public List<User> getAllUsers();
	public User getUser(int id);
	public User updateUser(User u);
	public void deleteUser(int id);
}
