package com.knowledgespike;

import com.knowledgespike.blog.ApplicationSettings;
import com.knowledgespike.blog.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
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
    public void init() throws ServletException {
        product = getServletContext().getInitParameter("productName");
        if (product == null || product.isEmpty()) throw new ServletException("Unable to initialise the application");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setUpData(req, ApplicationSettings.topic, ApplicationSettings.all);

        String color = getColorFromCookie(req.getCookies());

        req.setAttribute("color", color);

        if (req.getRequestURI().contains("showlogin.do")) {
            req.setAttribute("action", "login");
        } else if (req.getRequestURI().contains("color.do")) {
            String newColor = setColor(req, resp);
            req.setAttribute("color", newColor);
        }
        if (req.getRequestURI().contains("logout.do")) {
            var session = req.getSession(false);
            if(session != null)
                session.invalidate();
        }
        var dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/index.jsp");
        dispatcher.forward(req, resp);
    }

    private String getColorFromCookie(Cookie[] cookies) {
        var color = defaultColor;

        if(cookies != null) {
            for (var cookie : cookies) {
                if(cookie.getName().equals("color")) {
                    color = cookie.getValue();
                }
            }
        }

        return color;
    }

    private String setColor(HttpServletRequest req, HttpServletResponse resp) {
        var color = req.getParameter("color");

        if (color == null || color.length() == 0) {
            color = defaultColor;
        }

        var cookie = new Cookie("color", color);
        cookie.setHttpOnly(true);

        resp.addCookie(cookie);

        return color;

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().contains("login.do")) {
            var userName = req.getParameter("username");
            var password = req.getParameter("password");

            if (userName.isEmpty() || !userName.equals(password)) {
                resp.sendRedirect(resp.encodeURL("showlogin.do"));
            } else {
                var user = new User(userName);
                var session = req.getSession(true);
                session.setAttribute("user", user);

                resp.sendRedirect(resp.encodeURL("home"));
            }
        }
    }


    private void setUpData(HttpServletRequest request, String type, String detail) {
        ApplicationSettings applicationSettings = (ApplicationSettings) getServletContext().getAttribute("app");
        var data = applicationSettings.setupData(type, detail);
        request.setAttribute("items", data);
    }


}
