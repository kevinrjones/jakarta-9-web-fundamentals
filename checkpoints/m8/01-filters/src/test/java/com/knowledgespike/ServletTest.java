package com.knowledgespike;

import jakarta.servlet.*;
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
    public void testFilterImplemented() {

        final Optional<Class<?>> maybeFilter = getClass("com.knowledgespike.MyFilter");
        final Class<?> filterClass = maybeFilter.get();

        Class<?>[] interfaces = filterClass.getInterfaces();

        assertEquals(1, interfaces.length);

        assertEquals(Filter.class, interfaces[0]);

        final Optional<Method> maybeDoFilter = Arrays.stream(filterClass.getDeclaredMethods())
                .filter(method -> method.getName().equals("doFilter"))
                .findFirst();



        assertTrue(maybeDoFilter.isPresent());

        Method doFilter = maybeDoFilter.get();
        assertEquals(3,doFilter.getParameterCount());

        assertEquals(ServletRequest.class, doFilter.getParameterTypes()[0]);
        assertEquals(ServletResponse.class, doFilter.getParameterTypes()[1]);
        assertEquals(FilterChain.class, doFilter.getParameterTypes()[2]);
    }

}

