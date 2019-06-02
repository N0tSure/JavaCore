package com.artemsirosh.tij.generics.bank;

import com.artemsirosh.tij.util.Generator;

/**
 * Created by cresh on 28.08.16.
 */
class Teller {
    private static long counter = 1;
    private final long id = counter++;
    private Teller() {}

    @Override
    public String toString() {
        return "Teller: " + id;
    }

    public static Generator<Teller> generator = new Generator<Teller>() {
        @Override
        public Teller next() {
            return new Teller();
        }
    };
}
