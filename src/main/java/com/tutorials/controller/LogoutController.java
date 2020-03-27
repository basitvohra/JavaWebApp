package com.tutorials.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutController extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4002226620888748094L;

	@Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("LogoutController initialised");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Logging out");
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("login.jsp");
    }

    @Override
    public void destroy() {
        System.out.println("LogoutController destroyed");
    }
}