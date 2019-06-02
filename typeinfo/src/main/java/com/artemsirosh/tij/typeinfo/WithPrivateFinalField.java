package com.artemsirosh.tij.typeinfo;

/**
 * Created by cresh on 25.08.16.
 */
class WithPrivateFinalField {
    private int i = 1;
    private final String string = "I'm totally safe";
    private String string2 = "Am I safe?";

    @Override
    public String toString() {
        return "i = " + i + ", " + string + ", " + string2;
    }
}
