package com.knowledgespike.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebFilter("/*")
public class LoggingFilter implements Filter {

    static Logger logger = LogManager.getLogger(LoggingFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        var wrapper = new LogRequestWrapper(req, logger);

        logger.debug(String.format("Request made to %s", req.getRequestURI()));

        chain.doFilter(wrapper, response);
    }

}
