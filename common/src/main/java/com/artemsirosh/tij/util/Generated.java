package com.artemsirosh.tij.util;

import java.lang.reflect.Array;

/**
 * Created by cresh on 06.02.17.
 */
public class Generated {
    public static <T> T[] toArray(T[] a, Generator<T> generator) {
        for (int i = 0; i < a.length; i++) {
            a[i] = generator.next();
        }
        return a;
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] toArray(Class<T> type, Generator<T> generator, int size) {
        T[] result = (T[]) Array.newInstance(type, size);
        for (int i = 0; i < size; i++) {
            result[i] = generator.next();
        }
        return result;
    }
}
