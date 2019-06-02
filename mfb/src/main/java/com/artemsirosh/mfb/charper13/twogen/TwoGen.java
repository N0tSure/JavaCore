package com.artemsirosh.mfb.charper13.twogen;

/**
 * Created by cresh on 11.04.16.
 */
class TwoGen<T, V> implements Same<T> {
    T t;
    V v;

    TwoGen(T t, V v) {
        this.t = t;
        this.v = v;
    }

    void showTypes() {
        System.out.println("Тип Т - это " + t.getClass().getName());
        System.out.println("Тип V - это " + v.getClass().getName());
    }

    @Override
    public boolean isSame(T t) {
        return this.t.getClass().getName().equalsIgnoreCase(t.getClass().getName());
    }

    public T getT() {
        return t;
    }

    public V getV() {
        return v;
    }
}
