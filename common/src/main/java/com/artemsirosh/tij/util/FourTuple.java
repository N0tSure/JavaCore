package com.artemsirosh.tij.util;

/**
 * Created by cresh on 26.08.16.
 */
public class FourTuple<A,B,C,D> extends ThreeTuple<A,B,C> {
    public final D fourth;

    public FourTuple(A first, B second, C third, D fourth) {
        super(first, second, third);
        this.fourth = fourth;
    }

    @Override
    public String toString() {
        return "(" + this.first + ", " + this.second + ", " + this.third + ", " + this.fourth + ")";
    }
}
