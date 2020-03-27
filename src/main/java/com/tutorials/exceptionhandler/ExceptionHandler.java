package com.tutorials.exceptionhandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionHandler extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ExceptionHandler handling exception");
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
        if (servletName == null) {
            servletName = "Unknown";
        }
        String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "Unknown";
        }
        Integer status = (Integer) request.getAttribute("javax.servlet.error.status_code");
        String statusCode = null;
        if (status == null) {
            statusCode = "Unknown";
        } else {
            statusCode = String.valueOf(status);
        }
        request.setAttribute("statusCode", statusCode);
        request.setAttribute("servletName", servletName);
        request.setAttribute("exceptionType", throwable.getClass().getName());
        request.setAttribute("requestUri", requestUri);
        request.setAttribute("exceptionMessage", throwable.getMessage());
        request.getSession().invalidate();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("error.jsp");
        requestDispatcher.include(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}

