package com.project.dao;

import java.util.List;

import com.project.models.Account;

public interface IAccountDAO {
	
	public int insert(Account a); // CREATE
	
	public List<Account> findAll(); // READ
	public Account findById(int accountId);
	public Account findByAccountStatus(String status);
	public Account findByAccountType(String type);
	
	public int update(Account a); // UPDATE
	
	public int delete(int accountId); // DELETE

	Account findByAccountType(Object type);

}
