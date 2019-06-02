package com.artemsirosh.tij.generics.functional;

/**
 * Created by cresh on 20.12.16.
 */
interface Collector<T> extends UnaryFunction<T, T> {
    T result();
}
