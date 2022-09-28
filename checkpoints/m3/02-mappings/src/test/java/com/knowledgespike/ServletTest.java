package com.knowledgespike;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.function.Try;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.platform.commons.util.ReflectionUtils.tryToLoadClass;

public class ServletTest {


    public static Optional<Class<?>> getClass(String classToFind) {
        Try<Class<?>> aClass = tryToLoadClass(classToFind);
        return aClass.toOptional();
    }

    @Test
    public void testHasCorrectAttribute() {

        final Optional<Class<?>> maybeMainServlet = getClass("com.knowledgespike.MainServlet");
        final Class<?> mainServletClass = maybeMainServlet.get();


        WebServlet annotation = mainServletClass.getAnnotation(WebServlet.class);

        assertNotNull(annotation);

        assertEquals(2, annotation.urlPatterns().length);

        assertEquals("*.do", annotation.urlPatterns()[0]);
        assertEquals("/home", annotation.urlPatterns()[1]);
    }

}

