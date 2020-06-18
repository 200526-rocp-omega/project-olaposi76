package com.project.services;

import java.util.List;

import com.project.dao.AccountDAO;
import com.project.dao.IAccountDAO;
import com.project.models.Account;
import com.project.models.User;

public class AccountService {
	public static Object instance;
	
	private IAccountDAO dao = new AccountDAO();
	
	public int insert(Account a) {
		return dao.insert(a);
	}

	public List<Account> findAll() {
		return dao.findAll();
	}
	public Account findById(int AccountId) {
		
		return dao.findById(AccountId);
	}
	public Account findByAccountStatus(String status) {
		return dao.findByAccountStatus(status);
}
}
