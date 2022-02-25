package com.knowledgespike.filters;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.IOException;
import java.io.PrintWriter;

public class GZIPResponseWrapper extends HttpServletResponseWrapper
{
    protected HttpServletResponse origResponse;
    protected ServletOutputStream stream = null;
    protected PrintWriter writer = null;

    public GZIPResponseWrapper(HttpServletResponse response)
    {
        super(response);
        origResponse = response;
    }

    public void finishResponse() throws IOException {
        if (writer != null) writer.close();
        else if (stream != null) stream.close();
    }

    @Override
    public void flushBuffer() throws IOException{
        stream.flush();
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (writer != null){
            throw new IllegalStateException();
        }
        if (stream == null)
            stream = createOutputStream();
        return stream;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        if (writer != null){return (writer);}
        if (stream != null){
            throw new IllegalStateException();
        }
        stream = createOutputStream();
        writer = new PrintWriter(stream);
        return writer;
    }

    protected ServletOutputStream createOutputStream() throws IOException {
        return new GZIPResponseStream(origResponse);
    }
}
