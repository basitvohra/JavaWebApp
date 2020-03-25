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

public class HomeController extends HttpServlet {

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