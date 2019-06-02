package com.artemsirosh.tij.util;

/**
 * Created by cresh on 26.08.16.
 */
public class TwoTuple<A,B> {
    public final A first;
    public final B second;

    public TwoTuple(A first, B second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return "(" + this.first + ", " + this.second + ")";
    }
}
