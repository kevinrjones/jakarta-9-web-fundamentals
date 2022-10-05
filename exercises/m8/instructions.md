# Module 8 - Filters

## General Instructions

To run the tests you must first deploy the application, to do that make sure you are in the `before` directory (`~/workspace/before`) and then run `gradle cargeRedeployRemote` and then run `gradle build`. The first time you fun `gradle build` it may take a little while as it has to establish its environment but subsequent uses of this command will be quicker.

The exercises have two directories, the `before` directory is where you will work and the `after ` directory contains the solution. If you want to check that the solution works then change directories to the `after` directory and run `gradle cargeRedeployRemote` and `gradle build`

## Deploy the application

Deploy and test the app

## Creating a Filter

1. In this exercise you will create a filter that will count the number of incoming requests and store that 'hitcount' in a value that can be displayed on the page
1. Add a new package called `com.knowledgespike.filters`
1. In this package create a class called `HitCounter`
1. This class will need to implement the `Filter` interface and its three methods `init`, `destroy` and `doFilter`
1. Add a `WebFilter` attribute to the class so the filter is only applied to the `home` route
```java
@WebFilter(urlPatterns = "/home")
```
1. Add these three member variables to the class
```java
    private FilterConfig config;
    private final String attributeName = "hitcount";
    private Date refDate;
```
These will hold the current date that you are counting hits on (you will reset the hitcount each day), the `filterConfig` that's passed in the `init` method and the name of the attribute that will store the count
1. Also add a `SimpleDateFormat` variable that you will use when comparing dates
```java
private static final SimpleDateFormat datef = new SimpleDateFormat("yyyyMMdd");
```
1. In `init` store the FilterConfig and set the `refDate` variable defined above, also set the "hitcount" initial value to zero in the servlet context
```java
        this.config = config;

        refDate = new Date();

        config.getServletContext().setAttribute(attributeName, 0);
```

1. In the `doFilter` method
    1. Get the `ServletContext`
    1. Get the date and store it in a variable
    ```java
        ServletContext context = config.getServletContext();

        Date todayDate = new Date();
    ```
    1. You now need to check the date and if it's a new day set the hit count to 1, but if it's the same day increment the hitcount, something like this
    ```java
         if (!datef.format(todayDate).equals(datef.format(refDate)))
        {
                refDate = todayDate;
                context.setAttribute(attributeName, 1);
        }
        else
        {
                Integer hitCount = (Integer) context.getAttribute(attributeName);
                context.setAttribute(attributeName, hitCount + 1);
        }
   
    ```
    1. Finally call `chain.doFilter(request, response);`


1. To display the hitcount update the `_header.jsp` page to show the counter, change the line which says 
```jsp
<a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true" id="hitcount">Disabled</a>
```
to
```jsp
<a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true" id="hitcount">[${hitcount}] hits today!</a>
```
1. Redeploy and test the application, all the tests should pass
1. If you browse to http://localhost:8081/myblog/home you should see the hitcount on the page