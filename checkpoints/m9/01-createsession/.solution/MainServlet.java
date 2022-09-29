package com.knowledgespike;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/home", "*.do"}, name = "Main")
public class MainServlet extends HttpServlet {
    String product = "";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().contains("login.do")) {
            var userName = req.getParameter("username");
            var password = req.getParameter("password");

            if (userName.isEmpty() || !userName.equals(password)) {
                resp.sendRedirect(resp.encodeURL("showlogin.do"));
            } else {
                var session = req.getSession(true);

                session.setAttribute("user", userName);

                resp.sendRedirect(resp.encodeURL("home"));
            }
        }
    }
}
