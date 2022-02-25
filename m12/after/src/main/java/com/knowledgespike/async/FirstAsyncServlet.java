package com.knowledgespike.async;

import jakarta.servlet.AsyncEvent;
import jakarta.servlet.AsyncListener;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static java.lang.String.format;

@WebServlet(urlPatterns = "/simple", asyncSupported = true)
public class FirstAsyncServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log(format("FirstAsyncServlet in 'doGet', thread id: %d", Thread.currentThread().getId()));

        final var ctx = req.startAsync();

        ctx.setTimeout(3000);

        ctx.addListener(new AsyncListener() {
            @Override
            public void onComplete(AsyncEvent event) throws IOException {
                log("FirstAsyncServlet onComplete called, thread id:  " + Thread.currentThread().getId());
            }

            @Override
            public void onTimeout(AsyncEvent event) throws IOException {
                log("FirstAsyncServlet onTimeout called, thread id:  " + Thread.currentThread().getId());
            }

            @Override
            public void onError(AsyncEvent event) throws IOException {
                log("FirstAsyncServlet onError called , thread id: " + Thread.currentThread().getId());
            }

            @Override
            public void onStartAsync(AsyncEvent event) throws IOException {
                log("FirstAsyncServlet onStartAsync called, thread id:  " + Thread.currentThread().getId());
            }
        });

        ctx.start(() -> {
            try {
                log("FirstAsyncServlet in 'start', thread id: " + Thread.currentThread().getId());

                Thread.sleep(5000);
                ctx.getResponse().getWriter().write(format(
                        "<h1>Processing task in thread id:[%s]</h1>",
                        Thread.currentThread().getId()));
            } catch (Exception e) {
                log("FirstAsyncServlet Problem processing task", e);
            }

            ctx.complete();
        });
    }
}
