package com.artemsirosh.tij.generics.factories;

/**
 * Created by cresh on 01.12.16.
 */
abstract class Generic<T> {
    protected final T t;
    Generic() {
        t = create();
    }
    abstract T create();
}
