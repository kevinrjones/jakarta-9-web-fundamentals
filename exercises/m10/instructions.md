# Module 10 - Cookies

## General Instructions

To run the tests you must first deploy the application, to do that make sure you are in the `before` directory (`~/workspace/before`) and then run `gradle cargeRedeployRemote` and then run `gradle build`. The first time you fun `gradle build` it may take a little while as it has to establish its environment but subsequent uses of this command will be quicker.

The exercises have two directories, the `before` directory is where you will work and the `after ` directory contains the solution. If you want to check that the solution works then change directories to the `after` directory and run `gradle cargeRedeployRemote` and `gradle build`

## Deploy the application

Deploy and test the app

## Using a Cookie

1. In this exercise you will use a cookie to hold the selected background color

1. In `MainServlet` add a `private static final String` member call `defaultColor` with the initial value of "white"
1. Make sure you import the `jakarta.servlet.http.Cookie` package
1. You need two helper methods, `getColorFromCookie` and `setColor`. `getColorFromCookie` needs to return either the color stored in a cookie called 'color' or the default calor value if the cookie does not exist.
```java
    private String getColorFromCookie(Cookie[] cookies) {
        var color = defaultColor;

        if(cookies != null) {
            for (var cookie : cookies) {
                if(cookie.getName().equals("color")) {
                    color = cookie.getValue();
                }
            }
        }

        return color;
    }
```
1. `setColor` needs to get the color from the request parameter and if that value is available store it in the cookie
```java
    private String setColor(HttpServletRequest req, HttpServletResponse resp) {
        var color = req.getParameter("color");

        if (color == null || color.length() == 0) {
            color = defaultColor;
        }

        var cookie = new Cookie("color", color);
        cookie.setHttpOnly(true);

        resp.addCookie(cookie);

        return color;

    }
```
1. In the `doGet` method after the call to `setUpData` call `getColorFromCookie` and the store that color as an attribute named `color` on the request
```java
        String color = getColorFromCookie(req.getCookies());

        req.setAttribute("color", color);
```
1. Also in the `doGet` method if the URI is `color.do` then call `setColor` and again store that color as an attribute named `color` on the request
```java
else if (req.getRequestURI().contains("color.do")) {
    String newColor = setColor(req, resp);
    req.setAttribute("color", newColor);
}
```

1. Redeploy and test the application

