# Add Jakarta Server Pages

## General Instructions

To run the tests you must first deploy the application, to do that make sure you are in the `before` directory (`~/workspace/before`) and then run `gradle cargeRedeployRemote` and then run `gradle build`. The first time you fun `gradle build` it may take a little while as it has to establish its environment but subsequent uses of this command will be quicker.

The exercises have two directories, the `before` directory is where you will work and the `after ` directory contains the solution. If you want to check that the solution works then change directories to the `after` directory and run `gradle cargeRedeployRemote` and `gradle build`

## Deploy the application

Deploy and test the app, two tests should pass, these are the tests from the previous exercise.

## Using Includes

Move the header to a header block and include it

+ In the `webapp/WEB-INF` directory  create an `_header.jsp` page
+ Move the 'header' block from the 'index.jsp' page
+ Add a call to 'include' directive to the 'index.jsp' page to include the header
+ Redeploy and browse to and test the application, 2 tests should pass
+ If you open a browser tab and browse to http://localhost:8081/myblog/index.jsp you should still see the header

## Use a Scriptlet

+ In `_header.jsp` there is a section that displays a list of colors, you are going to replace this with a dynamic block
+ At the top of the page add a code block to initialise the colors, the code should look like this:
```jsp
<%
  List<String> colors = new ArrayList<>();

  colors.add("Default");
  colors.add("Red");
  colors.add("Green");
%>
``` 
Remember that you will also need to add the correct package references
```jsp
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>Ø
```
+ Where the colors are shown in the HTML replace that block with a Java code block that iterates over the `colors` collection and displays each color, something like this:
```jsp
<% for (String color:colors) { %>
<li><%= color %></li>
<% } %>
```
+ Redeploy and browse to and test the application, 2 tests should pass
+ If you open a browser tab and browse to http://localhost:8081/myblog/index.jsp you should be able to click on the colors menu and still see the colors

## Setup MVC

+ Delete index.jsp and _header.jsp from the root directory, if you open the WEB-INF folder you will see that these files exist there, the original files are no longer needed 
+ You are now working in `MainServlet.java`
+ In `MainServlet` replace the code in `doGet` with calls to get the `RequestDispatcher` and to dispatch to `index.jsp`
```java
        var dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/index.jsp");
        dispatcher.forward(req, resp);
```
+ Redeploy and build the application, one test should now pass
+ If you browse to http://localhost:8081/myblog/index.jsp you should get a 404 but if you browse to http://localhost:8081/myblog/home then the page should appear
## Create and Use a Model to Display the Topics

+ In the `init` method create a new `ApplicationSettings` instance and store it in the `ServletContext`giving it the name `app`
```java
        var applicationSettings = new ApplicationSettings();
        getServletContext().setAttribute("app", applicationSettings);
```
+ Create a page called `_topics.jsp` in the `webapp/WEB-INF` directory
+ Add the HTML to show the topics, the HTML should initially look like this
```jsp
<div>
    <h3>Topics</h3>
    <ul id="topics">
        <li><a href="topic/all.do">All</a></li>
    </ul>
</div>
```
+ Now add the Java code to display the topics, to do this you need to get the `ApplicationSettings` instance from the `ServletContext` and then get the `List<Topic>` from the `ApplicationSettings`. 

That code looks like this
```java
<%
ApplicationSettings app = (ApplicationSettings)request.getServletContext().getAttribute("app");
List<Topic> topics = app.getTopicList().getTopics();

for(Topic topic: topics) { %>
    <li><a href="/topic<%= topic.getUrl() %>"><%= topic.getTitle() %> </a></li>
<% } %>
```
+ Open `webapp\WEB-INF\index.jsp`
+ In index.jsp add a call to `<%@include file="_topics.jsp"%>` to make sure the topics are included in the page
+ Deploy and run the tests, 5 tests should pass

## Create and Use a Model to Display the News Items

+ In the `MainServlet` find the `setupData` method and add a call to add the data into the `request` with the name `items`
The code should look like this:
```java
        request.setAttribute("items", data);
```
+ Inside `doGet` make this call
```java
setUpData(req, ApplicationSettings.topic, ApplicationSettings.all);
```
+ Create a page called `_newsitems.jsp` in the `webapp\WEB-INF` directory
+ Add the HTML and code to show the topics, the HTML should initially look like this
```jsp
<%@ page import="com.knowledgespike.blog.NewsItem" %>
<%@ page import="java.util.List" %>
<div class="p-3 border bg-light" id="newsitems">

    <%
        List<NewsItem> items = (List<NewsItem>) request.getAttribute("items");
    %>
    <% for (NewsItem item: items) { %>

        <div>
            <h2><a href="/news/<%= item.getTitle()%>.do"><%= item.getTitle() %></a></h2>
            <div>
                    <%= item.getEntry() %>
            </div>
        </div>
    <% } %>

</div>
```
+ In `index.jsp` add a call to `<%@include file="_newsitems.jsp"%>` to make sure the topics are included in the page
+ Deploy and run the tests, all tests should pass