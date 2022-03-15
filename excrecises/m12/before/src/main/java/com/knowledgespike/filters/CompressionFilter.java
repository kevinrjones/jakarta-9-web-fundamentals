package com.knowledgespike.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebFilter(urlPatterns = "*.do")
public class CompressionFilter implements Filter {

    static Logger logger = LogManager.getLogger(LoggingFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            String ae = request.getHeader("accept-encoding");
            if (ae != null && ae.contains("gzip")) {
                logger.info("[Compression Filter] called");

                GZIPResponseWrapper wrappedResponse =
                        new GZIPResponseWrapper(response);
                chain.doFilter(servletRequest, wrappedResponse);
                wrappedResponse.finishResponse();
                return;
            }
        }
        chain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
