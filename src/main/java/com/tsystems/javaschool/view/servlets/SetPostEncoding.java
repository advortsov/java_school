package com.tsystems.javaschool.view.servlets;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 02.03.2016
 */
public class SetPostEncoding implements Filter {

    private String encoding;

    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("requestEncoding");
        System.out.println("SetPostEncoding init = " + encoding);
        if (encoding == null) encoding = "UTF-8";
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain next)
            throws IOException, ServletException {

        if (null == request.getCharacterEncoding())
            request.setCharacterEncoding(encoding);


        /**
         * Set the default response content type and encoding
         */
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        next.doFilter(request, response);
    }

    public void destroy() {
    }
}