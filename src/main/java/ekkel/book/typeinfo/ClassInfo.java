package ekkel.book.typeinfo;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.regex.Pattern;

/**
 * Created by cresh on 15.08.16.
 */
public class ClassInfo<T> {
    private StringBuilder result;
    private static Pattern noPackages = Pattern.compile("\\w+\\.");

    public ClassInfo(Class<T> clazz) {
        this.result = new StringBuilder();

        this.append(String.format("Имя класса: %s",clazz.getSimpleName()));
        this.append(String.format("Каноническое имя: %s",clazz.getCanonicalName()));
        this.append(String.format("Имя пакета: %s",clazz.getPackage().toString()));
        this.append(String.format("Это аннотация? %s",this.answer(clazz.isAnnotation())));
        this.append(String.format("Это анонимный класс? %s",this.answer(clazz.isAnonymousClass())));
        this.append(String.format("Это массив? %s",this.answer(clazz.isArray())));
        this.append(String.format("Это перечисление? %s",this.answer(clazz.isEnum())));
        this.append(String.format("Это интерфейс? %s",this.answer(clazz.isInterface())));
        this.append(String.format("Это локальный класс? %s",this.answer(clazz.isLocalClass())));
        this.append(String.format("Это примитив? %s",this.answer(clazz.isPrimitive())));
        //this.append(String.format("Это объект? %s",this.answer(clazz.isSynthetic())));

        this.append(String.format("Класс-предок: %s",clazz.getSuperclass().getSimpleName()));

        this.append("Поля класса:");
        for (Field field : clazz.getDeclaredFields())
            this.append(String.format("Поле %s, доступ: %s",
                    noPackages.matcher(field.toString()).replaceAll(""),Modifier.toString(field.getModifiers())));

        this.append("Методы класса:");
        for (Method method : clazz.getDeclaredMethods())
            this.append(String.format("Метод: %s, доступ: %s",
                    noPackages.matcher(method.toString()).replaceAll(""),Modifier.toString(method.getModifiers())));


        this.append("Интерфейсы класса:");
        for (Class iface : clazz.getInterfaces())
            this.append(iface.getSimpleName());

        this.append("Аннотации класса:");
        for (Annotation annotation : clazz.getAnnotations())
            this.append(annotation.toString());
    }

    private void append(CharSequence c) {
        result.append(c);
        result.append("\n");
    }

    private String answer(boolean b) {
        return b ? "да" : "нет";
    }

    @Override
    public String toString() {
        return new String(this.result);
    }

    public static void main(String[] args) {
    }
}
