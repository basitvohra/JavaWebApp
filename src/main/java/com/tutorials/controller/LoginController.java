package com.tutorials.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.tutorials.models.User;
import com.tutorials.services.UserService;

public class LoginController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2445112288618121414L;
	private static final Logger LOGGER = Logger.getLogger(LoginController.class);
	private UserService userService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		userService = new UserService();
		LOGGER.debug("LoginController initialised");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("Validating info for login");
		HttpSession session = request.getSession();
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		if (userId.equals("ABC")) {
			LOGGER.error("User id is invalid");
			throw new ServletException("User id is invalid");
		}
		if (userService.validateCredentials(userId, password)) {
			LOGGER.info("Validated");
			User user = userService.retrieveUserDetails(userId);
			session.setAttribute("loggedInUser", user);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("home.jsp");
			requestDispatcher.forward(request, response);
		} else {
			LOGGER.info("Invalid credentials");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
			request.setAttribute("error", "Invalid credentials!");
			requestDispatcher.include(request, response);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
		requestDispatcher.forward(request, response);
	}

	@Override
	public void destroy() {
		LOGGER.debug("LoginController destroyed");
	}
}