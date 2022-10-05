# Writing a Servlet

## General Instructions

To run the tests you must first deploy the application, to do that make sure you are in the `before` directory (`~/workspace/before`) and then run `gradle cargeRedeployRemote` and then run `gradle build`. The first time you fun `gradle build` it may take a little while as it has to establish its environment but subsequent uses of this command will be quicker.

The exercises have two directories, the `before` directory is where you will work and the `after ` directory contains the solution. If you want to check that the solution works then change directories to the `after` directory and run `gradle cargeRedeployRemote` and `gradle build`

## Writing Your First Servlet

There are several steps to writing, deploying and making accessible your servlets, you have to write the code, provide the mapping and deploy the application. There is skeleton code provided in the `before` directory, for this exercise you will be working in the `before/src/main/java/com/knowledgespike/MainServlet.java` file, open this file now.

This file already has the `MainServlet` class defined as well as the `urlPatterns` to allow you to browse to the file. In this exercise you will provide simple implementations of `doGet` and `doPost`

+ If you deploy and test the application now then two tests should pass, you should be able to browse to the application you have deployed and you should be able to 'post' something to the application, although in both cases nothing substantial will happen. You can also see this by browsing to the site at http://localhost:8081/myblog (you can open a browser tab and copy or enter this address)

+ The main part of this exercise is to capture the `name` query parameter from the input URL and send that name back in XML content to the browser, to do that perform the following steps

    +  Add code to the `doGet` method to get the value of the `name` request parameter from the query string
    + If the name value is null then respond with an error message
    + Otherwise set the response content type to `text/xml`
    + Respond with structured XML that conatains the name, the XML should look like this
    ```xml
    <application>
        <name>Hello, [name]</name>
    </application>
    ```
    where [name] is the value from the query string

    + Now redeploy the application and run the tests
    + If you are successful three tests should now pass

+ Now that you can get a value from the query string the next part will extend the response to include a servlet initialization parameter and a servlet context initialization parameter.
    + Add two member variables to the `MainServlet` and initialize them to empty strings, one will hold the servlet initialization parameter and one will hold the servlet context initialization parameter.

    + Add an `init` method to the servlet

    + In the `init` method add calls to `getInitParameter` to get the servlet initialization parameter, the name of this parameter is 'productName'

    + In the `init` method add a call to `getServletContext().getInitParameter` to get the servlet context initialization parameter, this is called 'connStr'

    + Update the XML output to include this values, the XML should look like this
    ```xml
    <application>
        <name>Hello, [name]</name>
        <product>[productName]</product>
        <connectionStr>[connStr]</connectionStr>
    </application>
    where [name] is the value from the query string, [productName] is the value of the servlet initialization parameter and [connStr] is value of the servlet context initialization parameter.
    
    ```
    + You now need to edit the `web.xml` file to add the two files.
    Open this file and in here add a `<context-param>` entry, give it the name 'connStr' and the value 'My Connection String'
    Also in this file, in the `<Servlet>` entry add an `<init-param>` section, give this parameter the name of 'productName' and the value 'Super Blog'
    + Redeploy the application and run the tests again, four tests should now pass

+ For the last part of this exercise you will implement the `doPost` method to respond to a user's input. The code will be similar to the `doGet` above, you will extract the name parameter from the form request and then send XML back to the user that contains that value.

    + In the `doPost` method add a call to get the name parameter from the request
    + Add code to send XML back to the caller if the name parameter exists, the XML should look like this
    ```xml
    <name>Hello, [name]</name>
    ```
    where [name] is the value from the post request.
    + Make sure to set the content type to `text/xml`
    + If the name is not present then add code to redirect to 'index.html'
    + Now all the tests should pass, CONGRATULATIONS