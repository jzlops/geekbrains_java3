package main;

import ru.tikhonov.term7.test.annotations.AfterSuite;
import ru.tikhonov.term7.test.annotations.BeforeSuite;
import ru.tikhonov.term7.test.annotations.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

/**
 * Edit by Tikhonov Sergey
 */
public class Tester {
    private static final int AFTER_BARRIER = 1;
    private static final int BEFORE_BARRIER = 1;
    private static final int TEST_LOWER_PRIORITY_BARRIER = 1;
    private static final int TEST_UPPER_PRIORITY_BARRIER = 10;

    public static void start(Class clazz) {
        try {
            Constructor constructor = clazz.getConstructor();
            Object o = constructor.newInstance();

            Method[] methods = clazz.getMethods();

            List<Method> afterSuiteMethods = new LinkedList<>();
            List<Method> beforeSuiteMethods = new LinkedList<>();
            TreeMap<Integer, Method> testMethods = new TreeMap<>();

            for (Method method : methods) {
                if (method.getAnnotation(AfterSuite.class) != null) {
                    afterSuiteMethods.add(method);
                    if (afterSuiteMethods.size() > AFTER_BARRIER) {
                        throw new RuntimeException("Exceeded the allowed number of methods with an annotation @AfterSuite in Class: " + clazz.getCanonicalName());
                    }
                }

                if (method.getAnnotation(BeforeSuite.class) != null) {
                    beforeSuiteMethods.add(method);
                    if (beforeSuiteMethods.size() > BEFORE_BARRIER) {
                        throw new RuntimeException("Exceeded the allowed number of methods with an annotation @BeforeSuite in Class: " + clazz.getCanonicalName());
                    }

                }

                if (method.getAnnotation(Test.class) != null) {
                    Integer i;
                    i = method.getAnnotation(Test.class).priority();
                    if ((i > TEST_UPPER_PRIORITY_BARRIER) || (i < TEST_LOWER_PRIORITY_BARRIER)) {
                        throw new RuntimeException("Incorrect @Test Annotation priority value in Class: " + clazz.getCanonicalName());
                    }
                    testMethods.put(method.getAnnotation(Test.class).priority(), method);
                }
            }
            for (Method method : beforeSuiteMethods) {
                method.invoke(o);
            }
            for (Method method : testMethods.values()) {
                method.invoke(o);
            }
            for (Method method : afterSuiteMethods) {
                method.invoke(o);
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }

    }
}
