package com.artemsirosh.tij.innerclasses.hardtask;

/**
 * Created by cresh on 01.07.16.
 */
class Anon {
    Methods getMethods() {
        return new Methods() {
            @Override
            public void first() {
                System.err.println("first");
            }

            @Override
            public void second() {
                System.err.println("second");
            }

            @Override
            public void third() {
                System.err.println("third");
            }
        };
    }
}
