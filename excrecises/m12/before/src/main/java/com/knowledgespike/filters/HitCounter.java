package com.knowledgespike.filters;

import java.text.*;
import java.util.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;


/**
 * Filter to track hit counts on page reads.
 */
@WebFilter(urlPatterns = {"/home", "*.do"})
public class HitCounter implements Filter
{
    private FilterConfig config;
    private final String attributeName = "hitcount";
    private Date refDate;

    private static final SimpleDateFormat datef = new SimpleDateFormat("yyyyMMdd");

    public void init(FilterConfig config)
    {
        this.config = config;

        refDate = new Date();

        config.getServletContext().log("Initialized hitcount filter");
        config.getServletContext().setAttribute(attributeName, 0);
    }

    public void destroy()
    {
    }


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, java.io.IOException
    {
        ServletContext context = config.getServletContext();

        Date todayDate = new Date();
        if (!datef.format(todayDate).equals(datef.format(refDate)))
        {
                refDate = todayDate;
                context.setAttribute(attributeName, 1);
        }
        else
        {
                Integer hitCount = (Integer) context.getAttribute(attributeName);
                context.setAttribute(attributeName, hitCount + 1);
        }

        chain.doFilter(request, response);
    }
}
