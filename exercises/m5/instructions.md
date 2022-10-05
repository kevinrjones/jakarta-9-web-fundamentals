# Add The Expression Language

## General Instructions

To run the tests you must first deploy the application, to do that make sure you are in the `before` directory (`~/workspace/before`) and then run `gradle cargeRedeployRemote` and then run `gradle build`. The first time you fun `gradle build` it may take a little while as it has to establish its environment but subsequent uses of this command will be quicker.

The exercises have two directories, the `before` directory is where you will work and the `after ` directory contains the solution. If you want to check that the solution works then change directories to the `after` directory and run `gradle cargeRedeployRemote` and `gradle build`

## Deploy the application

Deploy and test the app

## Using the Expression Language

+ In `_header.jsp` add the list of colors to the available request object by calling `pageContext.setAttribute("colors", colors);`
+ Replace the iteration code that displays the colors and show each individual color from this list
``` jsp
<li>${ colors[0] }</li>
<li>${ colors[1] }</li>
<li>${ colors[2] }</li>
```

+ Redeploy and run the tests, 5 tests should pass

## Updating the News Items Page

+ You are now working in `_newsitems.jsp`
+ Change the code so that each news item is stored in the pageContext object, name the item `item`
```jsp
pageContext.setAttribute("item", item);
```
+ Change the scriptlet code to become expressions instead, using the item you stored in the request in the previous step
```jsp
<h2><a href="/news/${item.title}.do">${item.title}</a></h2>
<div>
    ${item.entry}
</div>
```            
+ Redeploy and run the tests, 5 tests should pass
+ If you browse to http://localhost:8081/myblog/home then you should still see the news items

## Using Intrinsics

+ You are now working in `index.jsp`
+ In `index.jsp` change the `link` and `script` tags to use `contextPath`
```jsp
${pageContext.request.contextPath}
```

+ Change `application.getInitParameter` to use the `initParam` intrinsic and use the expression language to access this
+ Redeploy and run the tests, all the tests should pass
