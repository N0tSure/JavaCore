package com.artemsirosh.mfb.charper09;

/**
 * Created by cresh on 08.01.16.
 */
public class NoIntResultException extends Exception {
    int n;
    int d;

    public NoIntResultException(int n, int d) {
        this.n = n;
        this.d = d;
    }

    public String toString() {
        return "NoIntResultException " +
                " n= " + n +
                ", d= " + d;
    }
}
