package com.artemsirosh.tij.generics;

/**
 * Created by cresh on 27.08.16.
 */
public class Counted {
    private static long counter = 0;
    private final long id = counter++;

    @Override
    public String toString() {
        return "Counted: " + id;
    }
}
