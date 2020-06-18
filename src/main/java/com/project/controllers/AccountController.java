package com.project.controllers;

import java.util.List;

import com.project.models.Account;
import com.project.services.AccountService;

public class AccountController {
	
private final static AccountService accountService = new AccountService();
	
	public static Account findAccountById(int id) {
		return accountService.findById(id);
	}
	
	public static List<Account> findAllAccount() {		
		return accountService.findAll();
	}

	public static void UpdateAccount(Account a) {
		return;
		
	}

}
