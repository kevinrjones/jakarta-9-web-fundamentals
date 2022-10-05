# Asynchronous Programming

## General Instructions

To run the tests you must first deploy the application, to do that make sure you are in the `before` directory (`~/workspace/before`) and then run `gradle cargeRedeployRemote` and then run `gradle build`. The first time you fun `gradle build` it may take a little while as it has to establish its environment but subsequent uses of this command will be quicker.

The exercises have two directories, the `before` directory is where you will work and the `after ` directory contains the solution. If you want to check that the solution works then change directories to the `after` directory and run `gradle cargeRedeployRemote` and `gradle build`

## Deploy the application

Deploy and test the app

+ In this exercise you will add and test a simple asynchronous servlet.
+ Create a new package named `com.knowledgespike.async`
+ In this package create a servlet called `firstAsyncServlet`. Add a `WebServlet` attribute with a URL of `/simple`

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
+ Override the `doGet` method
+ In the `doGet` method add a call to `req.startAsync()` and store the result in a variable called `ctx`
+ Call `ctx.start` passing a lambda.
+ In the lambda use the `ctx` to get an `HttpResponse`, get a writer from the response and write a message to the client, the message should say

```html
<h1>Processing task in thread id:[%s]</h1>
``` 
Where the %s should be the current thread id
+ Call `ctx.complete` to close the asynchronous context
+ Deploy and test the application