package com.artemsirosh.tij.innerclasses.callbacks;

/**
 * Created by cresh on 01.07.16.
 */
class MyIncrement {
    public void increment() {
        System.out.println("Another operation");
    }

    public static void f(MyIncrement m) {
        m.increment();
    }
}
