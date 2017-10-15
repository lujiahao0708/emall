package com.lujiahao.rest.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lujiahao
 * @version 1.0
 * @date 2017-10-15 22:20
 */
public class ACAOFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin","*");
        filterChain.doFilter(servletRequest, response);
    }

    @Override
    public void destroy() {

    }
}
