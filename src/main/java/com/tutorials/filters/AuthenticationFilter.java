package com.tutorials.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tutorials.models.User;

public class AuthenticationFilter implements Filter {

	private List<String> ignoreAuthenticationUriList;

	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("AuthenticationFilter initialised");
		ignoreAuthenticationUriList = new ArrayList<String>();
		String[] exceptionUris = filterConfig.getInitParameter("exceptions").split(",");
		String[] staticResources = filterConfig.getServletContext().getInitParameter("static-resources").split(",");
		ignoreAuthenticationUriList.addAll(Arrays.asList(exceptionUris));
		ignoreAuthenticationUriList.addAll(Arrays.asList(staticResources));
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Inside authentication filter");
		String requestUri = ((HttpServletRequest) request).getRequestURI();
		boolean isRequastedUriIgnoreAuthentication = false;
		for (String exceptionUri : ignoreAuthenticationUriList) {
			if (requestUri.startsWith(exceptionUri)) {
				isRequastedUriIgnoreAuthentication = true;
				break;
			}
		}
		if (isRequastedUriIgnoreAuthentication) {
			System.out.println("Authentication ignored");
			chain.doFilter(request, response);
		} else {
			System.out.println("Checking authentication");
			HttpSession session = ((HttpServletRequest) request).getSession();
			User loggedInUser = (User) session.getAttribute("loggedInUser");
			if (loggedInUser == null) {
				System.out.println("Authentication failed");
				((HttpServletResponse) response).sendRedirect("login.jsp");
			} else {
				System.out.println("Authentication success");
				chain.doFilter(request, response);
			}
		}
	}

	public void destroy() {
		System.out.println("AuthenticationFilter destroyed");
	}
}
