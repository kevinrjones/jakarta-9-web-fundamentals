# Module 4 - Add Jakarta Server Pages

## General Instructions

To run the tests you must first deploy the application, to do that make sure you are in the `before` directory (`~/workspace/before`) and then run `gradle cargeRedeployRemote` and then run `gradle build`. The first time you fun `gradle build` it may take a little while as it has to establish its environment bu subsequent uses of this command will be quicker.

The exercises have two directories, the `before` directory is where you will work and the `after ` directory contains the solution. If you want to check that the solution works then change directories to the `after` directory and run `gradle cargeRedeployRemote` and `gradle build`

## Deploy the application

Deploy and test the app, two tests should pass, these are the tests from the previous exercise.

## Using Includes

Move the header to a header block and include it

1. In `webapp` create an `_header.jsp` page
1. Move the 'header' block from the 'index.jsp' page
1. Add a call to 'include' directive to the 'index.jsp' page to include the header
1. Redeploy and browse to and test the application, 2 tests should pass
1. If you open a browser tab and browse to http://localhost:8081/myblog/index.jsp you should still see the header

## Use a Scriptlet

1. In `_header.jsp` there is a section that displays a list of colors, you are going to replace this with a dynamic block
1. At the top of the page add a code block to initialise the colors, the code should look like this:
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
1. Where the colors are shown in the HTML replace that block with a Java code block that iterates over the `colors` collection and displays each color, something like this:
```jsp
<% for (String color:colors) { %>
<li><%= color %></li>
<% } %>
```
1. Redeploy and browse to and test the application, 2 tests should pass
1. If you open a browser tab and browse to http://localhost:8081/myblog/index.jsp you should be able to click on the colors menu and still see the colors

## Setup MVC

1. Move index.jsp and _header.jsp to the WEB-INF folder and fix up the CSS/JS references
1. Add a request dispatcher

## Create and Use a Model to Display the Topics

1. In the '`init` method create a new `ApplicationSettings` instance and store it in the `ServletContext`giving it the name `app`
1. Create a page called `_topics.jsp` in the WEB-INF directory
1. Add the HTML to show the topics, the HTML should initially look like this
```jsp
<div>
    <h3>Topics</h3>
    <ul id="topics">
        <li><a href="topic/all.do">All</a></li>
    </ul>
</div>
```
1. Now add the Java code to display the topics, to do this you need to get the `ApplicationSettings` instance from the `ServletContext` and then get the `List<Topic>` from the `ApplicationSettings`. 

That code looks like this
```java
<%
ApplicationSettings app = (ApplicationSettings)request.getServletContext().getAttribute("app");
List<Topic> topics = app.getTopicList().getTopics();

for(Topic topic: topics) { %>
    <li><a href="/topic<%= topic.getUrl() %>"><%= topic.getTitle() %> </a></li>
<% } %>
```
1. In index.jsp add a call to `<%@include file="_topics.jsp"%>` to make sure the topics are included in the page
1. Deploy and run the tests, x tests should pass

## Create and Use a Model to Display the News Items

1. In the `MainServlet` find the `setupData` method and add a call to data in the `request` with the name `items`
The code should look like this:
```java
        request.setAttribute("items", data);
```
1. Inside `doGet` make this call
```java
setUpData(req, ApplicationSettings.topic, ApplicationSettings.all);
```
1. Create a page called `_newsitems.jsp` in the WEB-INF directory
1. Add the HTML and code to show the topics, the HTML should initially look like this
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
1. In index.jsp add a call to `<%@include file="_newsitems.jsp"%>` to make sure the topics are included in the page
1. Deploy and run the tests, x tests should pass