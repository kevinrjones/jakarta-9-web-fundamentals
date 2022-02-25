package com.knowledgespike.async;

import jakarta.servlet.AsyncContext;

public class Client {

    private final AsyncContext asyncContext;
    private int bytesSent;

    public Client(AsyncContext asyncContext) {
        this.asyncContext = asyncContext;
    }

    public AsyncContext getAsyncContext() {
        return asyncContext;
    }

    public String getDataChunk() {
        try {
            asyncContext.getRequest().getServletContext().log(String.format("Client work handled on %s", Thread.currentThread().getId()));
            Thread.sleep(250);
        } catch (InterruptedException e1) {
            throw new RuntimeException(e1);
        }
        var chunk = getChunk();
        this.bytesSent += chunkSize;
        return chunk;

    }

    public boolean isWorkDone() {
        return bytesSent >= data.length();
    }

    final int chunkSize = 1000;
    private String getChunk() {
        var end = bytesSent + chunkSize;

        if (end > data.length()) end = data.length();

        return data.substring(bytesSent, end);
    }


    final String data =
            "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890";

}
