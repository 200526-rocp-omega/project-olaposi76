package com.project.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.project.Exceptions.NotLoggedInException;
import com.project.models.User;
import com.project.services.UserService;

public class UserController {

	private final UserService userService = new UserService();
	
	public boolean logout(HttpSession session) {
		try {
			userService.logout(session);
		} catch(NotLoggedInException e) {
			return false;
		}
		return true;
	}
	
	public User findUserById(int id) {
		return userService.findById(id);
	}
	
	public List<User> findAllUsers() {		
		return userService.findAll();
	}

	public void updateUser(User u) {
		return;
		
	}
}

