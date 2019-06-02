package com.artemsirosh.tij.generics.factories;

/**
 * Created by cresh on 01.12.16.
 */
class ClassAsFactory<T> {
    private T t;

    ClassAsFactory(Class<T> kind) {
        try {
            t = kind.newInstance();
        } catch (InstantiationException | IllegalAccessException exc) {
            throw new RuntimeException(exc);
        }
    }

    public T get() {
        return t;
    }
}
