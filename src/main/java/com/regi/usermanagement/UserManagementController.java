package com.regi.usermanagement;

import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class UserManagementController {
	
	@RequestMapping(value="/get-all",  produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<User> getAllUsers() {
		List<User> users = HandleRequests.getAllUsers();
		return users;
	}
	
	@RequestMapping(value="/get/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public User getUser(@PathVariable int userId) {
		System.out.println(userId);
		User u = HandleRequests.getUser(userId);
		return u;
	}
	
	@RequestMapping(value="/delete/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<User> deleteUser(@PathVariable int userId) {
		System.out.println(userId);
		HandleRequests.deleteUser(userId);
		List<User> users = HandleRequests.getAllUsers();
		return users;
	}
	
}

