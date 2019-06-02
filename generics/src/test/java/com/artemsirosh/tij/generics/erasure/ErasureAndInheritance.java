package com.artemsirosh.tij.generics.erasure;

import org.junit.Test;

/**
 * Created by cresh on 01.12.16.
 */
public class ErasureAndInheritance {
    @Test
    @SuppressWarnings("unchecked")
    public void test() {
        SecondDerived derived = new SecondDerived();
        Object o = derived.getT();
        derived.setT(o);
    }
}
