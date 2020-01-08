package com.regi.usermanagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HandleRequests {

	static Connection connection;

	private static Connection getConnection() {
		if (connection == null) {
			Properties props = new Properties();
			Path myPath = Paths.get("src/main/resources/database.properties");

			try {
				BufferedReader bf = Files.newBufferedReader(myPath, 
						StandardCharsets.UTF_8);

				props.load(bf);
			} catch (IOException ex) {
				Logger.getLogger(PostgreSqlAccessObject.class.getName()).log(
						Level.SEVERE, null, ex);
			}

			String url = props.getProperty("db.url");
			String user = props.getProperty("db.user");
			String passwd = props.getProperty("db.passwd");

			try {
				connection = DriverManager.getConnection(url, user, passwd); 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return connection;
	}

	public static List<User> getAllUsers() {
		try {
			PreparedStatement pst = getConnection().prepareStatement("SELECT * FROM test_table");
			ResultSet rs = pst.executeQuery();

			List<User> users = new ArrayList<User>();

			while(rs.next()){
				int id  = rs.getInt(5);
				String name = rs.getString(2);
				Date dateOfBirth  = rs.getDate(1);
				String email = rs.getString(3);
				String username = rs.getString(4);

				users.add(new User(id,name,dateOfBirth,email,username));
			}

			return users;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void deleteUser(int id) {
		try {
			PreparedStatement pst = getConnection().prepareStatement("DELETE FROM test_table WHERE id = ?");

			pst.setLong(1, id);
			pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static User getUser(int userId) {
		try {
			PreparedStatement pst = getConnection().prepareStatement("SELECT * FROM test_table WHERE id = ?");
			pst.setInt(1, userId);
			pst.execute();
			
			ResultSet rs = pst.executeQuery();

			User u = null;
			while(rs.next()){
				int id  = rs.getInt(5);
				String name = rs.getString(2);
				Date dateOfBirth  = rs.getDate(1);
				String email = rs.getString(3);
				String username = rs.getString(4);
				
				u = new User(id,name,dateOfBirth,email,username);
				
			}
			return u;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static User insertUser(User user) {
		try {
			PreparedStatement pst = getConnection().prepareStatement("INSERT INTO public.test_table(date_of_birth, name, email, username) VALUES (?, ?, ?, ?)");
			pst.setDate(1, user.getDateOfBirth());
			pst.setString(2, user.getName());
			pst.setString(3, user.getEmail());
			pst.setString(4, user.getUsername());
			pst.execute();	

			pst = getConnection().prepareStatement("SELECT * FROM public.test_table WHERE date_of_birth = ? and name = ? and email = ? and username = ?");
			pst.setDate(1, user.getDateOfBirth());
			pst.setString(2, user.getName());
			pst.setString(3, user.getEmail());
			pst.setString(4, user.getUsername());		
			ResultSet rs = pst.executeQuery();
	
			User u = null;
			while(rs.next()){
				int id  = rs.getInt(5);
				String name = rs.getString(2);
				Date dateOfBirth  = rs.getDate(1);
				String email = rs.getString(3);
				String username = rs.getString(4);
				
				u = new User(id,name,dateOfBirth,email,username);	
			}
			return u;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static User updateUser(int userId, User user) {
		try {
			PreparedStatement pst = getConnection().prepareStatement("UPDATE test_table SET date_of_birth=?, name=?, email=?, username=? WHERE id=?");
			pst.setDate(1, user.getDateOfBirth());
			pst.setString(2, user.getName());
			pst.setString(3, user.getEmail());
			pst.setString(4, user.getUsername());
			pst.setInt(5, userId);
			pst.execute();	

			User u = null;
			u = getUser(userId);
			
			return u;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
