# Module 12 - Asynchronous Programming

## General Instructions

To run the tests you must first deploy the application, to do that make sure you are in the `before` directory (`~/workspace/before`) and then run `gradle cargeRedeployRemote` and then run `gradle build`. The first time you fun `gradle build` it may take a little while as it has to establish its environment but subsequent uses of this command will be quicker.

The exercises have two directories, the `before` directory is where you will work and the `after ` directory contains the solution. If you want to check that the solution works then change directories to the `after` directory and run `gradle cargeRedeployRemote` and `gradle build`

## Deploy the application

Deploy and test the app

1. In this exercise you will add and test a simple asynchronous servlet.
1. Create a new package named `com.knowledgespike.async`
1. In this package create a servlet called `firstAsyncServlet`, add a `WebServlet` attribute with a URL of `/simple`
```java
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
}
```
1. Override the `doGet` method
1. In the `doGet` method add a call to `req.startAsync()` and store the result in a variable called `ctx`
1. Call `ctx.start` passing a lambda.
1. In the lambda use the `ctx` to get an `HttpResponse`, get a writer from the response and write a message to the client, the message should say
```html
"<h1>Processing task in thread id:[%s]</h1>"
``` 
Where the %s should be the current thread id
1. Call `ctx.complete` to close the asynchronous context
1. Deploy and test the application