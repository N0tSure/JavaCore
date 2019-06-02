package com.artemsirosh.tij.generics.functional;

/**
 * Created by cresh on 20.12.16.
 */
interface Combiner<T> {
    T combine(T first, T second);
}
