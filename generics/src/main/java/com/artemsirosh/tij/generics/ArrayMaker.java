package com.artemsirosh.tij.generics;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by cresh on 01.12.16.
 */
class ArrayMaker<T> {
    private Class<T> kind;

    public ArrayMaker(Class<T> kind) {
        this.kind = kind;
    }

    @SuppressWarnings("unchecked")
    T[] create(int size) {
        return (T[]) Array.newInstance(kind, size);
    }

    public static void main(String[] args) {
        ArrayMaker<String> stringMaker = new ArrayMaker<>(String.class);
        String[] strings = stringMaker.create(9);
        System.out.println(Arrays.toString(strings));
    }
}
