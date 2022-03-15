# Module 2 - Writing a Servlet

## General Instructions

To run the tests you must first deploy the application, to do that make sure you are in the `before` directory (`~/workspace/before`) and then run `gradle cargeRedeployRemote` and then run `gradle build`. The first time you fun `gradle build` it may take a little while as it has to establish its environment bu subsequent uses of this command will be quicker.

The exercises have two directories, the `before` directory is where you will work and the `after ` directory contains the solution. If you want to check that the solution works then change directories to the `after` directory and run `gradle cargeRedeployRemote` and `gradle build`

## Writing Your First Servlet

There are several steps to writing, deploying and making accessible your servlets, you have to write the code, provide the mapping and deploy the application. There is skeleton code provided in the `before` directory, for this exercise you will be working in the `before/src/main/java/com/knowledgespike/MainServlet.java` file, open this file now.

This file already has the `MainServlet` class defined as well as the `urlPatterns` to allow you to browse to the file. In this exercise you will provide simple implementations of `doGet` and `doPost`

1. If you deploy and test the application now then two tests should pass, you should be able to browse to the application you have deployed and you should be able to 'post' something to the application, although in both cases nothing substantial will happen. You can also see this by [browsing to the site at](http://localhost:8081/myblog).