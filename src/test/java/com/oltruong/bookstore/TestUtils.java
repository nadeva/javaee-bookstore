package com.oltruong.bookstore;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static junit.framework.TestCase.fail;
import static org.assertj.core.api.Assertions.assertThat;

public class TestUtils {

    private TestUtils() {
    }

    public static void setPrivateAttribute(Object object, Object attribute, String name) {
        setPrivateAttribute(object, object.getClass(), attribute, name);
    }

    public static void setPrivateAttribute(Object object, @SuppressWarnings("rawtypes")
            Class className, Object attribute, String name) {
        try {
            Field field = className.getDeclaredField(name);
            field.setAccessible(true);
            field.set(object, attribute);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }


    public static Object getPrivateAttribute(Object object, @SuppressWarnings("rawtypes")
            String name) {
        return getPrivateAttribute(object, object.getClass(), name);

    }


    public static Object getPrivateAttribute(Object object, @SuppressWarnings("rawtypes")
            Class className, String name) {
        Object result = null;
        try {
            Field field = className.getDeclaredField(name);
            field.setAccessible(true);
            result = field.get(object);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;

    }

    public static Object callPrivateMethod(Object object, String methodName, Object... arguments) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = object.getClass().getDeclaredMethod(methodName);
        method.setAccessible(true);
        return method.invoke(object, arguments);
    }

    public static void testConstructorIsPrivate(Class<?> clazz) {
        try {
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            assertThat(Modifier.isPrivate(constructor.getModifiers())).isTrue();
            constructor.setAccessible(true);

            constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            fail(e.getMessage());
        }
    }
}
