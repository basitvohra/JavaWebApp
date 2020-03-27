package com.tutorials.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4482388185757634724L;

	@Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("HomeController initialised");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("home.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("HomeController destroyed");
    }
}