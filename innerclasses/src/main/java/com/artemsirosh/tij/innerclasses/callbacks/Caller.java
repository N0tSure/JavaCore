package com.artemsirosh.tij.innerclasses.callbacks;

/**
 * Created by cresh on 01.07.16.
 */
class Caller {
    private Incrementable callBackReference;
    Caller(Incrementable incrementable) {
        this.callBackReference = incrementable;
    }
    void go() {
        callBackReference.increment();
    }
}
