package com.artemsirosh.tij.typeinfo.toys;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Formatter;

/**
 * Created by cresh on 03.08.16.
 */
class ToyTest {
    private static Formatter print = new Formatter(System.out);
    static void printInfo(Class clazz) {
        print.format("Имя класса: %1$s является интерфейсом? \"%2$s\"\n",
                clazz.getName(), clazz.isInterface() ? "да" : "нет");
        print.format("Простое имя: %s\n",clazz.getSimpleName());
        print.format("Каноническое имя: %s\n\n",clazz.getCanonicalName());
    }

    public static void main(String[] args) {
        Class clazz = null;
        try {
            clazz = Class.forName("com.artemsirosh.tij.typeinfo.toys.FancyToy");
        } catch (ClassNotFoundException exc) {
            System.out.println("Не удается найти FancyToy");
            System.exit(1);
        }

        printInfo(clazz);
        for (Class iface : clazz.getInterfaces()) printInfo(iface);

        Class parentClass = clazz.getSuperclass();
        Object parent = null;
        try {
            @SuppressWarnings("unchecked")
            Constructor<?> constructor = parentClass.getDeclaredConstructor(int.class);
            parent = constructor.newInstance(1);
        } catch (InstantiationException exc) {
            System.out.println("Не удалось создать экземпляр");
            System.exit(1);
        } catch (IllegalAccessException exc) {
            System.out.println("Отказано в доступе");
            System.exit(1);
        } catch (NoSuchMethodException exc) {
            System.out.println("Нет такого конструктора");
            System.exit(1);
        } catch (InvocationTargetException exc) {
            System.out.println(exc.getMessage());
            System.exit(1);
        } finally {
            System.out.println("В теле finally");
        }

        printInfo(parent.getClass());

    }
}
