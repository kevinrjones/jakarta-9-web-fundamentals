package com.knowledgespike;

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
    public void testHasMethod() {

        final Optional<Class<?>> maybeMainServlet = getClass("com.knowledgespike.MainServlet");
        final Class<?> mainServletClass = maybeMainServlet.get();


        final Optional<Method> maybeDoGetMethod = Arrays.stream(mainServletClass.getDeclaredMethods())
                .filter(method -> method.getName().equals("doGet"))
                .findFirst();


        assertTrue(maybeDoGetMethod.isPresent());

        Method doGetMethod = maybeDoGetMethod.get();

        assertEquals(2, doGetMethod.getParameterCount());

        Class<?>[] paramTypes = doGetMethod.getParameterTypes();

        assertEquals(HttpServletRequest.class, paramTypes[0]);
        assertEquals(HttpServletResponse.class, paramTypes[1]);

    }

}

