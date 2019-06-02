package com.artemsirosh.tij.generics.factories;

import com.artemsirosh.tij.typeinfo.factory.Factory;

/**
 * Created by cresh on 01.12.16.
 */
class Foo<T> {
    private T t;
    <F extends Factory<T>> Foo(F factory) {
        t = factory.create();
    }
}
