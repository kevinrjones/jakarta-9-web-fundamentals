package com.knowledgespike;

import jakarta.servlet.ServletContextListener;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.function.Try;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.platform.commons.util.ReflectionUtils.tryToLoadClass;

public class ServletTest {


    public static Optional<Class<?>> getClass(String classToFind) {
        Try<Class<?>> aClass = tryToLoadClass(classToFind);
        return aClass.toOptional();
    }
    @Test
    public void testListenerImplemented() {

        final Optional<Class<?>> maybeMainServlet = getClass("com.knowledgespike.MyListener");
        final Class<?> mainServletClass = maybeMainServlet.get();

        Class<?>[] interfaces = mainServletClass.getInterfaces();

        assertEquals(1, interfaces.length);

        assertEquals(ServletContextListener.class, interfaces[0]);

        final Optional<Method> maybecontextInitialized = Arrays.stream(mainServletClass.getDeclaredMethods())
                .filter(method -> method.getName().equals("contextInitialized"))
                .findFirst();


        assertTrue(maybecontextInitialized.isPresent());

    }

}

