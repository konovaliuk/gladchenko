package com.conference.web.filters;

import com.conference.web.properties.ConfigProperties;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Created by gleb on 13.01.18.
 */
public class FilterConnect implements Filter {
    private static final Logger LOG = Logger.getLogger(FilterConnect.class.getName());
    private FilterConfig filterConfig = null;
    private boolean active = false;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        String act = filterConfig.getInitParameter("active");
        if (act != null)
            active = (act.toUpperCase().equals("TRUE"));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (active) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            HttpSession session = request.getSession();
            long role = 0L;
            if (session.getAttribute("role") != null) {
                role = (long) session.getAttribute("role");
            } else {
                String page = ConfigProperties.getInstance().getProperty(ConfigProperties.LOGIN_PAGE_PATH);
                RequestDispatcher dispatcher = request.getRequestDispatcher(page);  //вызов страницы ответа на запрос
                dispatcher.forward(request, response);
            }
            if (role == 2 || role == 3 || role == 1) {
                filterChain.doFilter(servletRequest,servletResponse);
            } else if (role == 4) {
                String page = ConfigProperties.getInstance().getProperty(ConfigProperties.USER_PAGE_PATH);
                RequestDispatcher dispatcher = request.getRequestDispatcher(page);  //вызов страницы ответа на запрос
                dispatcher.forward(request, response);
            } else {
                String page = ConfigProperties.getInstance().getProperty(ConfigProperties.LOGIN_PAGE_PATH);
                RequestDispatcher dispatcher = request.getRequestDispatcher(page);  //вызов страницы ответа на запрос
                dispatcher.forward(request, response);
            }

            // Get the IP address of client machine.
            //String ipAddress = servletRequest.getRemoteAddr();
            // Log the IP address and current timestamp.
            //String message = "IP "+ ipAddress + ", Time "+ new Date().toString();
            //LOG.info(message);
            // Pass request back down the filter chain
            //filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }
}
