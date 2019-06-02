package com.artemsirosh.tij.typeinfo.proxy;

/**
 * Created by cresh on 15.08.16.
 */
class RealObject implements Inteface {
    @Override
    public void doSomething() {
        System.out.println("doSomething");
    }

    @Override
    public void somethingElse(String argument) {
        System.out.println("somethingElse " + argument);
    }
}
