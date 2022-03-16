package com.knowledgespike;

import com.knowledgespike.blog.ApplicationSettings;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/home", "*.do"}, name = "Main")
public class MainServlet extends HttpServlet {
    String product = "";

    @Override
    public void init() throws ServletException {
        product = getServletContext().getInitParameter("productName");
        if (product == null || product.isEmpty()) throw new ServletException("Unable to initialise the application");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("");
    }

    private void setUpData(HttpServletRequest request, String type, String detail) {
        ApplicationSettings applicationSettings = (ApplicationSettings) getServletContext().getAttribute("app");
        var data = applicationSettings.setupData(type, detail);
    }

}
