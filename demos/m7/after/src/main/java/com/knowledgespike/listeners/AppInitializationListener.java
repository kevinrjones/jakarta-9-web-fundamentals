package com.knowledgespike.listeners;

import com.knowledgespike.blog.ApplicationSettings;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class AppInitializationListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        var applicationSettings = new ApplicationSettings();
        sce.getServletContext().setAttribute("app", applicationSettings);
    }
}
