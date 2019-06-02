package com.artemsirosh.tij.generics.coffee;

/**
 * Created by cresh on 18.12.16.
 */
class Foam extends Decorator {
    private String odour;
    public Foam(Coffee coffee) {
        super(coffee);
        odour = "Strong odour of " + coffee.getClass().getSimpleName();
    }

    String getOdour() {
       return odour;
    }
}
