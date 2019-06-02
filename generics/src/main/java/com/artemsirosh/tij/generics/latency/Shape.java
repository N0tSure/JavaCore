package com.artemsirosh.tij.generics.latency;

/**
 * Created by cresh on 19.12.16.
 */
class Shape {
    public void rotate() {
        System.out.println(this + " rotate");
    }
    public void resize(int newSize) {
        System.out.println(this + " resize " + newSize);
    }
}
