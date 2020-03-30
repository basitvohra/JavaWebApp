package com.tutorials.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("LogFilter initialised");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String ipAddress = request.getRemoteAddr();
		String requestUri = ((HttpServletRequest) request).getRequestURI();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z");
		String currentTime = sdf.format(date);
		System.out.println("<Requested Uri: " + requestUri + ">< IP : " + ipAddress + "><Time : " + currentTime + ">");
		chain.doFilter(request, response);
	}

	public void destroy() {
		System.out.println("LogFilter destroyed");
	}
}
