package com.knowledgespike.async;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;

import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

@WebListener
public class ClientTransfer implements ServletContextListener {
    private static final int CLIENT_THREAD_COUNT = 10;
    private final Executor executor = Executors.newFixedThreadPool(CLIENT_THREAD_COUNT);
    private static final BlockingQueue<Client> Clients = new LinkedBlockingQueue<>();

    static void addClient(Client client) {
        Clients.add(client);
    }

    public void contextInitialized(ServletContextEvent event) {

        int count = 0;
        while (count < CLIENT_THREAD_COUNT) {
            executor.execute(this::sendDataToClient);
            count++;
        }
    }

    private void sendDataToClient() {
        while(true) {
            Client client = getClient();

            AsyncContext asyncContext = client.getAsyncContext();
            var response = asyncContext.getResponse();
            var request = asyncContext.getRequest();

            response.setContentType("text/plain");

            String dataChunk = client.getDataChunk();

            sendDataChunk(dataChunk, client, asyncContext);
        }
    }

    private void sendDataChunk(String chunk, Client client, AsyncContext asyncContext) {
        ServletRequest request = asyncContext.getRequest();
        ServletResponse response = asyncContext.getResponse();

        try {
            PrintWriter out = response.getWriter();
            out.write(chunk);
            out.flush();
            response.flushBuffer();

            if (client.isWorkDone()) {
                // if the work is done
                asyncContext.complete();
            } else {
                // if not, put the client again in the queue
                Clients.put(client);
            }

        } catch (Exception e) {
            // discard current client
            request.getServletContext().log(String.format("Error: %s", e.getMessage()));
            asyncContext.complete();
        }
    }

    private Client getClient() {
        Client client;
        try {
            // fetch a remote client from the waiting queue
            // (this call blocks until a client is available)
            client = Clients.take();
        } catch (InterruptedException e1) {
            throw new RuntimeException("Interrupted while waiting for remote clients");
        }
        return client;
    }
}
