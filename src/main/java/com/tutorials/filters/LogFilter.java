package com.tutorials.filters;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class LogFilter implements Filter {

	private static final Logger LOGGER = Logger.getLogger(LogFilter.class);

	public void init(FilterConfig filterConfig) throws ServletException {
		LOGGER.debug("LogFilter initialised");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String ipAddress = request.getRemoteAddr();
		String requestUri = ((HttpServletRequest) request).getRequestURI();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z");
		String currentTime = sdf.format(date);
		LOGGER.info("<Requested Uri: " + requestUri + ">< IP : " + ipAddress + "><Time : " + currentTime + ">");
		chain.doFilter(request, response);
	}

	public void destroy() {
		System.out.println("LogFilter destroyed");
	}
}
