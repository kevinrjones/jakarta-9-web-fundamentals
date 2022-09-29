package com.knowledgespike;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/home", "*.do"}, name = "Main")
public class MainServlet extends HttpServlet {
    String product = "";
    private static final String defaultColor = "white";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newColor = setColor(req, resp);
    }

    private String setColor(HttpServletRequest req, HttpServletResponse resp) {
        var color = req.getParameter("color");

        if (color == null || color.length() == 0) {
            color = defaultColor;
        }

        Cookie cookie = null;

        cookie.setHttpOnly(true);

        resp.addCookie(cookie);

        return color;

    }

}
