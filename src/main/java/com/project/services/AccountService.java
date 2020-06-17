package com.project.services;

import java.util.List;

import com.project.dao.AccountDAO;
import com.project.dao.IAccountDAO;
import com.project.models.Account;
import com.project.models.User;

public class AccountService {
	
	private IAccountDAO dao = new AccountDAO();
	
	public int insert(Account a) {
		return dao.insert(a);
	}
	public User findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
