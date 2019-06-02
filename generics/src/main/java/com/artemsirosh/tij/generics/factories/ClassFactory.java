package com.artemsirosh.tij.generics.factories;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by cresh on 01.12.16.
 */
class ClassFactory<T> {
    private Class<T> kind;

    public ClassFactory(Class<T> kind) {
        this.kind = kind;
    }

    @SuppressWarnings("unchecked")
    public T create(Object... initargs) {
        Constructor<T>[] constructors = (Constructor<T>[]) kind.getConstructors();
        return instantiate(constructors[0],initargs);
    }

    private T instantiate(Constructor<T> constructor, Object[] args) {
        try {
            return constructor.newInstance(args);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException exc) {
            throw new RuntimeException(exc);
        }
    }
}
