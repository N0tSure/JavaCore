package com.artemsirosh.tij.generics.factories;

import com.artemsirosh.tij.generics.coffee.Cappuccino;

/**
 * Created by cresh on 01.12.16.
 */
class Creator extends Generic<Cappuccino> {
    @Override
    Cappuccino create() {
        return new Cappuccino();
    }
    void f() {
        System.out.println(t.getClass().getSimpleName());
    }
}
