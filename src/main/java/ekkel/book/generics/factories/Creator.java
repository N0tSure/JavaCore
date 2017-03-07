package ekkel.book.generics.factories;

import ekkel.book.generics.coffee.Cappuccino;

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
