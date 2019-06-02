package com.artemsirosh.tij.generics.coffee;

/**
 * Created by cresh on 18.12.16.
 */
class Decorator extends Coffee {
    protected Coffee coffee;

    public Decorator(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public String toString() {
        return coffee.toString();
    }
}
