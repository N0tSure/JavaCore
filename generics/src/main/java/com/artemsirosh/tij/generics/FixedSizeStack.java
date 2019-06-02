package com.artemsirosh.tij.generics;

import java.util.ArrayList;

/**
 * Created by cresh on 15.12.16.
 */
class FixedSizeStack<T> {
    private int index = 0;
    private ArrayList<T> storage;
    FixedSizeStack(int size) {
        storage = new ArrayList<T>(size);
    }
    void push(T item) {
        storage.add(item);
        index++;
    }

    T pop() {
        if (--index < 0) return null;
        return storage.get(index);
    }
}
