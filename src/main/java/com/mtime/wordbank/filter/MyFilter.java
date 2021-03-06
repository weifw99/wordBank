package com.mtime.wordbank.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Mtime on 2016/2/18.
 */
@Component
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("filter start");
        filterChain.doFilter(servletRequest, servletResponse );
        System.out.println("filter end");
    }

    @Override
    public void destroy() {
        System.out.println("filter destroy");
    }
}
