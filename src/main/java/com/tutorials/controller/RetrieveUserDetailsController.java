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

public class RetrieveUserDetailsController extends HttpServlet {

    UserService userService = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("RetrieveUserDetailsController initialised");
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Retrieving user details via get method");
        String userId = request.getParameter("userId");
        System.out.println("Userid: " + userId);
        User user = userService.retrieveUserDetails(userId);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("userDetails.jsp");
        request.setAttribute("user", user);
        requestDispatcher.forward(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("RetrieveUserDetailsController destroyed");
    }
}