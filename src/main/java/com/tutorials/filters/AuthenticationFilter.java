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

import org.apache.log4j.Logger;

import com.tutorials.models.User;

public class AuthenticationFilter implements Filter {

	private static final Logger LOGGER = Logger.getLogger(AuthenticationFilter.class);
	private List<String> ignoreAuthenticationUriList;

	public void init(FilterConfig filterConfig) throws ServletException {
		LOGGER.debug("AuthenticationFilter initialised");
		ignoreAuthenticationUriList = new ArrayList<String>();
		String[] exceptionUris = filterConfig.getInitParameter("exceptions").split(",");
		String[] staticResources = filterConfig.getServletContext().getInitParameter("static-resources").split(",");
		ignoreAuthenticationUriList.addAll(Arrays.asList(exceptionUris));
		ignoreAuthenticationUriList.addAll(Arrays.asList(staticResources));
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		LOGGER.debug("Inside authentication filter");
		String requestUri = ((HttpServletRequest) request).getRequestURI();
		boolean isRequastedUriIgnoreAuthentication = false;
		for (String exceptionUri : ignoreAuthenticationUriList) {
			if (requestUri.startsWith(exceptionUri)) {
				isRequastedUriIgnoreAuthentication = true;
				break;
			}
		}
		if (isRequastedUriIgnoreAuthentication) {
			LOGGER.info("Authentication ignored");
			chain.doFilter(request, response);
		} else {
			LOGGER.info("Checking authentication");
			HttpSession session = ((HttpServletRequest) request).getSession();
			User loggedInUser = (User) session.getAttribute("loggedInUser");
			if (loggedInUser == null) {
				LOGGER.info("Authentication failed");
				((HttpServletResponse) response).sendRedirect("login.jsp");
			} else {
				LOGGER.info("Authentication success");
				chain.doFilter(request, response);
			}
		}
	}

	public void destroy() {
		LOGGER.debug("AuthenticationFilter destroyed");
	}
}
