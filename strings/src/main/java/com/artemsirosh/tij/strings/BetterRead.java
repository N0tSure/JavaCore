package com.artemsirosh.tij.strings;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cresh on 29.07.16.
 */
public class BetterRead {

    private static void hackScanner(Class<Scanner> scannerClass, Scanner scanner)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Pattern target;
        Method floatPattern = scannerClass.getDeclaredMethod("floatPattern");
        floatPattern.setAccessible(true);
        target = (Pattern) floatPattern.invoke(scanner);
        Matcher matcher = target.matcher("Sir Robin of Camelot\n" +
                "22 1.61803");
        while (matcher.find()) System.out.println(matcher.group());
    }

    private static void typeCatcheHack(Class<Scanner> scannerClass, Scanner scanner)
            throws NoSuchFieldException, IllegalAccessException {
        Field typeCache = scannerClass.getDeclaredField("typeCache");
        typeCache.setAccessible(true);
        Object o = typeCache.get(scanner);
        System.out.println(o==null);
    }

    private static void processFloatTokenHack(Class<Scanner> scannerClass, Scanner scanner)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        Method processFloatToken = scannerClass.getDeclaredMethod("processIntegerToken");
        for (Method method : scannerClass.getDeclaredMethods()) System.out.println(method.getName());
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(SimpleRead.input);
        System.out.println("What is your name?");
        String name = scanner.nextLine();
        System.out.println(name);
        System.out.println("How old are you? What's your favorite double?");
        System.out.println("(input: <age> <double>)");
        int age = scanner.nextInt();
        double favorite = scanner.nextDouble();
        processFloatTokenHack(Scanner.class,scanner);

        System.out.printf("Hi %s.\nIn 5 years you will be %d.\nMy favorite double is %f",name,age+5,favorite/2);




    }
}
