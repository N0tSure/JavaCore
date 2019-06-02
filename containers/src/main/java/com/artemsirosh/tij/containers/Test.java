package com.artemsirosh.tij.containers;

/**
 * Created by cresh on 19.03.17.
 */
abstract class Test<C> {
    private String name;

    public Test(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    abstract int test(C container, TestParam testParam);
}
