package com.conference.web.filters;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/**
 * Created by gleb on 13.01.18.
 */
public class FilterConnect implements Filter {
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
            // Get the IP address of client machine.
            String ipAddress = servletRequest.getRemoteAddr();

            // Log the IP address and current timestamp.
            //System.out.println("IP "+ ipAddress + ", Time "+ new Date().toString());

            // Pass request back down the filter chain
            filterChain.doFilter(servletRequest,servletResponse);

        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }
}
