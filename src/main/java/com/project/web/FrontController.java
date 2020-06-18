package com.project.web;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.Exceptions.AuthorizationException;
import com.project.Exceptions.NotLoggedInException;
import com.project.authorization.AuthService;
import com.project.controllers.AccountController;
import com.project.controllers.UserController;
import com.project.models.Account;
import com.project.models.User;
import com.project.templates.MessageTemplate;

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = -4854248294011883310L;
	private static final UserController userController = new UserController();
	private static final AccountController accountController = new AccountController();
	private static final ObjectMapper om = new ObjectMapper();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		res.setContentType("application/json");
		final String URI = req.getRequestURI().replace("/rocp-project", "").replaceFirst("/", "");
		
		String[] portions = URI.split("/");
		
//		System.out.println(Arrays.toString(portions));
		
		try {
			switch(portions[0]) {
			case "users":
				if(portions.length == 2) {
					// Delegate to a Controller method to handle obtaining a User by ID
					int id = Integer.parseInt(portions[1]);
					AuthService.guard(req.getSession(false), id, "Employee", "Admin");
					User u = userController.findUserById(id);
					res.setStatus(200);
					res.getWriter().println(om.writeValueAsString(u));
				} else {
					// Delegate to a Controller method to handle obtaining ALL Users
					AuthService.guard(req.getSession(false), "Employee", "Admin");
					List<User> all = userController.findAllUsers();
					res.setStatus(200);
					res.getWriter().println(om.writeValueAsString(all));
				}
				return;
			case "accounts":
				if(portions.length == 0) {
					// Delegate to a Controller method to handle obtaining a Account by ID
					int id = Integer.parseInt(portions[1]);
					AuthService.guard(req.getSession(false), id, "Employee", "Admin");
					Account a = AccountController.findAccountById(id);
					res.setStatus(200);
					res.getWriter().println(om.writeValueAsString(a));
				} else {
					// Delegate to a Controller method to handle obtaining ALL Users
					AuthService.guard(req.getSession(false), "Employee", "Admin");
					List<Account> all = AccountController.findAllAccount();
					res.setStatus(200);
					res.getWriter().println(om.writeValueAsString(all));
				}
				return;
				}
		} catch(AuthorizationException e) {
			res.setStatus(401);
			MessageTemplate message = new MessageTemplate("The incoming token has expired");
			
			res.getWriter().println(om.writeValueAsString(message));
		}
		res.setStatus(404);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		res.setContentType("application/json");
		final String URI = req.getRequestURI().replace("/rocp-project", "").replaceFirst("/", "");
		
		String[] portions = URI.split("/");

		try {
			switch(portions[0]) {
			case "logout":
				if(userController.logout(req.getSession(false))) {
					res.setStatus(200);
					res.getWriter().println("You have been successfully logged out");
				} else {
					res.setStatus(400);
					res.getWriter().println("You were not logged in to begin with");
				}
				break;
				
			case "accounts":
				if(portions[1].equals("submit")) {
					HttpSession session = req.getSession(false);
					User u = (User) session.getAttribute("currentUser");
					AuthService.guard(session, u.getId(), "Employee", "Admin");
					if(portions.length==0) {
						Account a = om.readValue(req.getReader(), Account.class);
						
						res.setStatus(201);
						res.getWriter().println("created");
						
						
					}break;
				}else if(portions[0].equals("timelapses")) {
					HttpSession session = req.getSession(false);
					AuthService.guard(session, "Admin");
					if(portions.length==0) {
						Account a = om.readValue(req.getReader(), Account.class);
						
						res.setStatus(201);
						MessageTemplate message = new MessageTemplate("{NumOfMonths} of Accrued Interest");
						res.getWriter().println(om.writeValueAsString(message));
					}break;
				}
			}
		} catch(NotLoggedInException e) {
			res.setStatus(401);
			MessageTemplate message = new MessageTemplate("The incoming token has expired");
			res.getWriter().println(om.writeValueAsString(message));
		}
		res.setStatus(404);
	}


	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("application/json");
		res.setStatus(404);
		final String URI = req.getRequestURI().replace("/rocp-project", "").replaceFirst("/", "");
		String[] portions = URI.split("/");
		System.out.println(Arrays.toString(portions));
		//update users
		try {
			switch(portions[0]) {
			case "users":
				HttpSession session = req.getSession(false);
				AuthService.guard(session, "Admin");
				User u = om.readValue(req.getReader(), User.class);
				userController.updateUser(u);
				res.setStatus(202);
				res.getWriter().println(om.writeValueAsString(u));
			
			break;
			
			case "accounts":
				session = req.getSession(false);
				AuthService.guard(session, "Admin");
				Account a = om.readValue(req.getReader(), Account.class);
				AccountController.UpdateAccount(a);
				
				res.setStatus(202);
				res.getWriter().println(om.writeValueAsString(a));
				
				break;
			}
		}
				catch(NotLoggedInException e) {
					res.setStatus(401);
					MessageTemplate message = new MessageTemplate("The incoming token has expired");
					res.getWriter().println(om.writeValueAsString(message));
				}
	}

	public static AccountController getAccountcontroller() {
		return accountController;
	}	
		
		}		
	
	


