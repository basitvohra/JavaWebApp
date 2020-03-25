package com.tutorials.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

public class LogFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("LogFilter initialised");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String ipAddress = request.getRemoteAddr();
        String requestUri = ((HttpServletRequest) request).getRequestURI();
        System.out.println(requestUri + " is called from IP : " + ipAddress + "at time : " + new Date().toString());
        chain.doFilter(request, response);
    }

    public void destroy() {
        System.out.println("LogFilter desproyed");
    }
}
