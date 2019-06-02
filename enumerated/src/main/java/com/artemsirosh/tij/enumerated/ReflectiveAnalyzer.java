package com.artemsirosh.tij.enumerated;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Created on 19 Nov, 2018.
 *
 * @author Artemis A. Sirosh
 */
public class ReflectiveAnalyzer {

    public static Set<String> analyze(Class<?> enumClazz) {

        System.out.println("--------- Analyzing " + enumClazz + " ----------");
        System.out.println("Interfaces:");
        Arrays.stream(enumClazz.getGenericInterfaces()).forEach(System.out::println);
        System.out.println("Base: " + enumClazz.getSuperclass());
        System.out.println("Methods:");

        return Arrays.stream(enumClazz.getMethods())
                .map(Method::getName)
                .peek(System.out::println)
                .collect(Collectors.toCollection(TreeSet::new));
    }
}
