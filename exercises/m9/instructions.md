# Module 9 - Session Management

## General Instructions

To run the tests you must first deploy the application, to do that make sure you are in the `before` directory (`~/workspace/before`) and then run `gradle cargeRedeployRemote` and then run `gradle build`. The first time you fun `gradle build` it may take a little while as it has to establish its environment bu subsequent uses of this command will be quicker.

The exercises have two directories, the `before` directory is where you will work and the `after ` directory contains the solution. If you want to check that the solution works then change directories to the `after` directory and run `gradle cargeRedeployRemote` and `gradle build`

## Deploy the application

Deploy and test the app

## Logging into the Application

1. In this exercise you will allow the user to log on to the application. You will create a form that will `POST` the user's credentials to the application, the application will check that the credentials are valid and then either allow the user to logon by creating a session or redirect the user back to the logon form
1. In the `WEB-INF` directory there is a file called `_login.jsp`, if you open this file you will see that it will `POST` to `login.do`. You need to display this page in response to a 'can I login' request and then respond to the login request.
1. Open `_index.jsp`, in this file where it currently shows the `_newsitems.jsp`, change this so that the news items are shown unless the user has requested the `_login.jsp` page, in which case that page should be shown
```jsp
<c:choose>
    <c:when test="${action eq 'login'}" ><c:import url="_login.jsp" /></c:when>
    <c:otherwise><c:import url="_newsitems.jsp"  /></c:otherwise>
</c:choose>
```
1. To get the above to work you need to edit the `MainServlet`
1. Open the `MainServlet` class now, in here, inside the `doGet` method after the call to `setUpData` check what the incoming request URI is, if the request URI contains `login.do` then set an attribute in the request called `action` with a value of `login` (this is what triggers the `index.jsp` to display the login page)
```java
        if (req.getRequestURI().contains("showlogin.do")) {
            req.setAttribute("action", "login");
        }
```
1. In the `MainServlet` add a `doPost` method, this method should
    1. Check that the incoming request is to `login.do`, if it's not then do nothing
    1. If the request is to `login.do` then grab the `username` and `password` from the request
    1. If they are not equal (this is the authentication check) then redirect to `showlogin.do'
    1. If they are equal 
        1. Create a new user
        1. Create a new session
        1. Store the user in the session
        1. Redirect to `home`.

            ```java
            @Override
            protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                if (req.getRequestURI().contains("login.do")) {
                    var userName = req.getParameter("username");
                    var password = req.getParameter("password");

                    req.getServletContext().log(String.format("Trying to log in userName: %s, password: %s", userName, password));

                    if (userName.isEmpty() || !userName.equals(password)) {
                        resp.sendRedirect(resp.encodeURL("showlogin.do"));
                    } else {
                        var user = new User(userName);
                        var session = req.getSession(true);
                        session.setAttribute("user", user);

                        resp.sendRedirect(resp.encodeURL("home"));
                    }
                }
            }
            ```

1. All the tests should pass
## Adding a Logout Action

1. To logout you need to send a request to and when that request is received invalidate the session
1. In `doGet` if the URI is `logout.do` then invalidate the session
``` java
       if (req.getRequestURI().contains("logout.do")) {
            var session = req.getSession(false);
            if(session != null)
                session.invalidate();
        }
```