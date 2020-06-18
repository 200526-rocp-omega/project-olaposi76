package com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.project.models.Account;
import com.project.models.AccountStatus;
import com.project.models.AccountType;
import com.project.util.ConnectionUtil;

public class AccountDAO implements IAccountDAO {
	
	@Override
	public int insert(Account a) {
		
		// Step 1: Get a Connection using ConnectionUtil
		// The Connection interface represents the physical connection to the database
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			// Step 2: Define our SQL Statements
			String columns = "balance, status, type";
			String sql = "INSERT INTO ACCOUNT (" + columns + ") VALUES (?, ?, ?)";
			// The ? marks are placeholders for input values
			// They work for PreparedStatements, and are designed to protect from SQL Injection
			
			// Step 3: Obtain our Statement object
			// PreparedStatements are a sub-interface of Statement that provide extra security to prevent
			// SQL Injection. It accomplishes this by allowing to use ? marks that we can replace
			// with whatever data we want
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			// Step 3b: If we are using a PreparedStatement, then inject values to replace the ? marks
			// We insert values into each of the ? marks above
			// Note: It is HEAVILY Recommended to use PreparedStatements instead of String concatenation
			stmt.setLong(1, (long) a.getBalance()); // replace 1st ? mark with u.getUsername()
			stmt.setString(2, a.getStatus()); // replace 2nd ? mark with u.getPassword()
			stmt.setString(3, a.getType()); // replace 3rd ? mark with u.getFirstName()
		
			
			// Step 4: Execute the Statement
			
			return stmt.executeUpdate();
			
		} catch(SQLException e) {
			// Step 5: Perform any exception handling in an appropriate means
			
			// This particular example might not be what you want to ultimately use
			e.printStackTrace();
		}
		
		return 0;
	}
	@Override
	public List<Account> findAll() {
		
		List<Account> allAccount = new ArrayList<>();
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "SELECT * FROM Accounts INNER JOIN ACCOUNT_STATUS ON Account_status.ID = Accounts.Status_id "
					+ "INNER JOIN ACCOUNT_TYPE ON Account_Type.ID = Accounts.Type_id";
			
			Statement stmt = conn.createStatement();
			
			
			// Steps 1 - 3 are the same as listed above, except there is
			// a ResultSet returned from executing our statement
			
			// We must make sure to use this ResultSet to save our data to the List that was prepared at the top
			// The ResultSet interface represents all of the data obtained from our query.
			// It has data for every column that we obtained from our query, per record
			ResultSet rs = stmt.executeQuery(sql);
			
			// ResultSets are similar to iterators, so this while-loop will allow us to iterate over every record
			while(rs.next()) {
				// We obtain the data from every column that we need
				
				int accountId = rs.getInt("id");
				double balance = rs.getDouble("balance");
				String type = rs.getString("type");
				
			
			}
		} catch(SQLException e) {
			// If something goes wrong, return an empty list
			e.printStackTrace();
			return new ArrayList<>();
		}
		
		// At the end we simply return all of our Users
		
		return allAccount;
	}


	@Override
	public Account findById(int accountId) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM ACCOUNTS INNER JOIN Accounts ON ACCOUNT.Account_id = ACCOUNTS.id WHERE ACCOUNTS.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, accountId);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				String type = rs.getString("type");
				double balance = rs.getDouble("balance");
				int AccountId = rs.getInt("AccountId");
			
				
				// And use that data to create a User object accordingly
				AccountType Type = new AccountType (AccountId, type);
				return new Account();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Account findByAccountType(String type) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM ACCOUNTS INNER JOIN Accounts ON ACCOUNT.Account_Status = STATUS_ID WHERE STATUS_ID = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(3, type);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				double balance = rs.getDouble("balance");
				int AccountId = rs.getInt("AccountId");
			
				
				// And use that data to create a User object accordingly
			
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	
	}

	@Override
	public int update(Account a) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int accountId) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public Account findByAccountStatus(String status) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Account findByAccountType(Object type) {
		// TODO Auto-generated method stub
		return null;
	}


}
