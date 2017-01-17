package com.mtime.wordbank.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Created by Mtime on 2016/2/18.
 */
@WebFilter(filterName = "myFilter2", urlPatterns = "/*")
public class MyFilter2 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter2 init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("filter2 start");
        filterChain.doFilter(servletRequest, servletResponse );
        System.out.println("filter2 end");
    }

    @Override
    public void destroy() {
        System.out.println("filter2 destroy");
    }
}
