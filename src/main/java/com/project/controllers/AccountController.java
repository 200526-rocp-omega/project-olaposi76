package com.project.controllers;

import java.util.List;

import com.project.models.User;
import com.project.services.AccountService;

public class AccountController {
	
private final AccountService accountService = new AccountService();
	
	public User findUserById(int id) {
		return accountService.findById(id);
	}
	
	public List<User> findAllUsers() {		
		return accountService.findAll();
	}

}
