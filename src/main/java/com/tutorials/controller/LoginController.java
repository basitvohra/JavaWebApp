package com.tutorials.controller;

import com.tutorials.models.User;
import com.tutorials.services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginController extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = new UserService();
        System.out.println("LoginController initialised");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Validating info for login");
        HttpSession session = request.getSession();
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        if (userService.validateCredentials(userId, password)) {
            System.out.println("Validated");
            User user = userService.retrieveUserDetails(userId);
            session.setAttribute("loggedInUser", user);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("home.jsp");
            requestDispatcher.forward(request, response);
        } else {
            System.out.println("Invalid credentials");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
            request.setAttribute("error", "Invalid credentials!");
            requestDispatcher.include(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("LoginController destroyed");
    }
}