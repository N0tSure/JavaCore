package ekkel.book.typeinfo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cresh on 15.08.16.
 */
class ShowMethods {
    private static String usage = "usage:\nShowMethods qualified.class.name\n" +
            "To show all methods in class or:\nShowMethods qualified.class.name word\n" +
            "To search for methods involving 'word'";

    private static Pattern pattern = Pattern.compile("\\w+\\.");
    private static Pattern allNativeFinal = Pattern.compile("native|final");

    public static void main(String[] args) {
        if (args.length<1) {
            System.out.println(usage);
            System.exit(0);
        }
        int lines = 0;
        try {
            Class<?> clazz = Class.forName(args[0]);
            Method[] methods = clazz.getMethods();
            Constructor[] constructors = clazz.getConstructors();
            if (args.length==1) {
                for (Method method : methods)
                    System.out.println(pattern.matcher(method.toString()).replaceAll(""));
                for (Constructor constructor : constructors)
                    System.out.println(pattern.matcher(constructor.toString()).replaceAll(""));
                lines = methods.length + constructors.length;
            } else {
                for (Method method : methods)
                    if (method.toString().contains(args[1])) {
                        System.out.println(
                                pattern.matcher(method.toString()).replaceAll(""));
                        lines++;
                    }
                for (Constructor constructor : constructors)
                    if (constructor.toString().contains(args[1])) {
                        System.out.println(
                                pattern.matcher(constructor.toString()).replaceAll("")
                        );
                    }
                lines++;
            }
            System.out.println("\nNative and finals:");
            Matcher matcher;
            for (Method method : methods) {
                matcher = allNativeFinal.matcher(method.toString());
                if (matcher.find()) System.out.println(
                        pattern.matcher(method.toString()).replaceAll("")
                );
            }
        } catch (ClassNotFoundException exc) {
            System.err.println("Не найден класс: " + args[0]);
        }
    }
}
