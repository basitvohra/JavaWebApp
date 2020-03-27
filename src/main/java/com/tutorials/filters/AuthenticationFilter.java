package com.tutorials.filters;

import com.tutorials.models.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthenticationFilter implements Filter {

	private FilterConfig filterConfig;

	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("AuthenticationFilter initialised");
		this.filterConfig = filterConfig;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Inside authentication filter");
		String requestUri = ((HttpServletRequest) request).getRequestURI();
		String requestMethod = ((HttpServletRequest) request).getMethod();
		System.out.println(requestUri + " : " + requestMethod);
		if (requestUri.contains("/login") || requestUri.contains("/css/") || requestUri.contains("/js/")) {
			chain.doFilter(request, response);
		} else {
			System.out.println("Checking authentication");
			HttpSession session = ((HttpServletRequest) request).getSession();
			User loggedInUser = (User) session.getAttribute("loggedInUser");
			if (loggedInUser == null) {
				System.out.println("Authentication failed");
				((HttpServletResponse) response).sendRedirect("login.jsp");
			} else {
				chain.doFilter(request, response);
			}
		}
	}

	public void destroy() {
		System.out.println("AuthenticationFilter destroyed");
	}
}
