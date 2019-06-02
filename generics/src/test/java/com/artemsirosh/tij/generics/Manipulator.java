package com.artemsirosh.tij.generics;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by cresh on 01.12.16.
 */
public class Manipulator<T extends HasF> {
    private T t;
    Manipulator(T t) {
        this.t = t;
    }

    public Manipulator() {
    }

    public void manipulate() {
        t.f();
    }

    @Test
    public void manipulation() {
        HasF hasF = new HasF();
        Manipulator<HasF> manipulator = new Manipulator<>(hasF);
        manipulator.manipulate();
    }

    @Test
    public void testAssertNotSame() {
        Object same = new Object();
        assertNotSame("should not be same Object", same, new Object());
    }

    @Test
    public void testAssertSame() {
        Integer aNumber = Integer.valueOf(1767685768);
        assertSame("should be same", aNumber, aNumber);
    }
}
