# Event Listeners

## General Instructions

To run the tests you must first deploy the application, to do that make sure you are in the `before` directory (`~/workspace/before`) and then run `gradle cargeRedeployRemote` and then run `gradle build`. The first time you fun `gradle build` it may take a little while as it has to establish its environment but subsequent uses of this command will be quicker.

The exercises have two directories, the `before` directory is where you will work and the `after ` directory contains the solution. If you want to check that the solution works then change directories to the `after` directory and run `gradle cargeRedeployRemote` and `gradle build`

## Deploy the application

Deploy and test the app

## Using a Listener to Initialise the Application

+ Currently the application does application wide initialization in the servlet's `init` method but this initialization is better placed in an event listener and that's what you'll do in this exercise

+ Create a new package call `com.knowledgespike.listeners`
+ In this directory create a listener called `AppInitializationListener` have it implement `ServletContextListener` and add the `contextInitialized` method
+ In this method copy the code from the `MainServlet` that creates the `ApplicationSettings` object and stores it in the `ServletContext`
+ In `MainServlet` and delete the code that creates the `ApplicationSettings` object and stores it in the `ServletContext`
+ In `web.xml` add an entry in make sure the event listener is loaded
```xml
<listener>
    <listener-class>com.knowledgespike.listeners.AppInitializationListener</listener-class>
</listener>
```
+ Deploy the application and run the tests, they should still all run correctly
+ **If the application fails to deploy this may be because of an issue with your startup code, maybe the listener or the listener configuration, in this case you may need fix the error and before you redeploy stop and re-start tomee, do do that run the following commands**
```bash
~/workspace/tomee/bin/catalina.sh stop
~/workspace/tomee/bin/catalina.sh start
``` 
+ **If this doesn't work you can delete the web application and the web application war file in the tomee webapps directory, this is `~/tomee/webapps` and then redeploy the application**

