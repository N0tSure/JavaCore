package com.artemsirosh.tij.generics.factories;

/**
 * Created by cresh on 01.12.16.
 */
class CreatorGeneric {
    public static void main(String[] args) {
        Creator creator = new Creator();
        creator.f();

        Foo0 foo = new ClassFactory<Foo0>(Foo0.class).create(new Object());
    }
}
