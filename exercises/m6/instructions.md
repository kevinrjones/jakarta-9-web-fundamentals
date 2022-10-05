# Using the Core Tag Library

## General Instructions

To run the tests you must first deploy the application, to do that make sure you are in the `before` directory (`~/workspace/before`) and then run `gradle cargeRedeployRemote` and then run `gradle build`. The first time you fun `gradle build` it may take a little while as it has to establish its environment but subsequent uses of this command will be quicker.

The exercises have two directories, the `before` directory is where you will work and the `after ` directory contains the solution. If you want to check that the solution works then change directories to the `after` directory and run `gradle cargeRedeployRemote` and `gradle build`

## Deploy the application

Deploy and test the app

## Using Tag Libraries
+ You are now working in `index.jsp`
+ At the top of `index.jsp` add the `taglib` directive to include the tag library in the page
```jsp
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
```

+ In the `doGet` method of the main servlet you are going to add code to respond to the `showlogin.do` URL. When this URL is requested you need to create a user and store it in the request
```java
if (req.getRequestURI().endsWith("showlogin.do")) {
    var user = new User("Kevin");
    req.setAttribute("user", user);
}
``` 
+ You'll also need to import the `User` class at the top of the servlet
```java
import com.knowledgespike.blog.User;
```
+ In `_header.jsp` use a conditional tag to test if there is a user, if there is not then show the `Login` link with the `showLogin.do` URL and if there is a user then show the `Logout` link with the `logout.do` URL. The logout link should also show the user's name (so it should say 'Logout Kevin' for example).
```jsp
<c:if test="${empty user}">
    <a class="nav-link" href='showlogin.do'>Login</a>
</c:if>
<c:if test="${!empty user}">
    <a class="nav-link" href='logout.do'>Logout ${user.name}</a>
</c:if>
```
+ Also in the `_header.jsp` file you are going to display the colors only if the user is logged in, to do this use the `if` tag to check that the user object exists, if they do then show the colors, if they do not exist then show the message 'No user logged in'
```jsp
<c:choose>
    <c:when test="${!empty user}">
        <li>${ colors[0] }</li>
        <li>${ colors[1] }</li>
        <li>${ colors[2] }</li>
    </c:when>
    <c:otherwise>
        <li>No user logged in</li>
    </c:otherwise>
</c:choose>
```
+ Deploy the app and run the tests, all of the tests should pass


## Using Iteration Tags

+ At the moment the color menu is created by selecting each color value one at a time by index from the colors list. In this exercise you will replace that with the `forEach` tag
+ In the `_header.jsp` page replace the three `<li>` elements with a `<c:forEach` loop and add each color one at a time, something like this
```jsp
<c:forEach items="${colors}" var="color">
    <li>${color}</li>
</c:forEach>
```
+ Deploy the app and run the tests, all of the tests should still pass
