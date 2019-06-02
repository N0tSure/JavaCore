package com.artemsirosh.tij.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cresh on 27.08.16.
 */
class GenericVarargs {
    @SafeVarargs
    public static <T> List<T> makeList(T... args) {
        List<T> result = new ArrayList<T>();
        for (T item : args) result.add(item);
        return result;
    }

    public static void main(String[] args) {
        List<String> list = makeList("A");
        System.out.println(list);
        list = makeList("A","B","C");
        System.out.println(list);
        list = makeList("АБВГДЕЁЖЗИЙКЛМНОПРСТЧЦХФЫШЩЪЬЭЮЯ".split(""));
        System.out.println(list);
    }
}
