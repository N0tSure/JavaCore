package com.artemsirosh.tij.typeinfo;

/**
 * Created by cresh on 09.08.16.
 */
class GenericClassReferences {
    public static void main(String[] args) {
        Class intClazz = int.class;
        Class<Integer> genericIntClazz = int.class;
        genericIntClazz = Integer.class;
        intClazz = double.class;
        Class<?> genericNumberClazz = int.class;
        Class<? extends Number> bounded = int.class;
        bounded = double.class;
        bounded = Number.class;
    }
}
