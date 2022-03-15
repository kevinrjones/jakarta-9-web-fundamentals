package com.knowledgespike.async;

import jakarta.servlet.AsyncContext;
import jakarta.servlet.AsyncEvent;
import jakarta.servlet.AsyncListener;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/dispatch", asyncSupported = true)
public class DispatchAsyncServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final AsyncContext ctx = req.startAsync();

        ctx.addListener(new AsyncListener() {
            public void onComplete(AsyncEvent event) {
                log("DispatchAsyncServlet onComplete called, thread id:  " + Thread.currentThread().getId());
            }

            public void onTimeout(AsyncEvent event) {
                log("DispatchAsyncServlet onTimeout called, thread id:  " + Thread.currentThread().getId());
            }

            public void onError(AsyncEvent event) {
                log("DispatchAsyncServlet onError called , thread id: " + Thread.currentThread().getId());
            }

            public void onStartAsync(AsyncEvent event) {
                log("DispatchAsyncServlet onStartAsync called, thread id:  " + Thread.currentThread().getId());
            }
        });

        ctx.dispatch(getServletContext(), "/simple");
    }
}
