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

@WebServlet(urlPatterns = "/downloadasync", asyncSupported = true)
public class TransferDataAsyncServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log(format("TransferDataAsyncServlet in 'doGet', thread id: %d", Thread.currentThread().getId()));
        var ctx = req.startAsync();
        ctx.addListener(new AsyncListener() {
            public void onComplete(AsyncEvent event) {
                log("TransferDataAsyncServlet onComplete called, thread id:  " + Thread.currentThread().getId());
            }
            public void onTimeout(AsyncEvent event) {
                log("TransferDataAsyncServlet onTimeout called, thread id:  " + Thread.currentThread().getId());
            }
            public void onError(AsyncEvent event) {
                log("TransferDataAsyncServlet onError called , thread id: " + Thread.currentThread().getId());
            }
            public void onStartAsync(AsyncEvent event) {
                log("TransferDataAsyncServlet onStartAsync called, thread id:  " + Thread.currentThread().getId());
            }
        });

        ctx.setTimeout(15 * 60 * 1000);
        ClientTransfer.addClient(new Client(ctx));
    }
}
