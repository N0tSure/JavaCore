package com.artemsirosh.tij.generics.mixins;


/**
 * Created by cresh on 17.12.16.
 */
class BasicImpl implements Basic {
    private String value;

    @Override
    public void set(String value) {
        this.value = value;
    }

    @Override
    public String get() {
        return value;
    }
}
