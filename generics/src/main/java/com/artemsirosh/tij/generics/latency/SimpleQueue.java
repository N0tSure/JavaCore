package com.artemsirosh.tij.generics.latency;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by cresh on 19.12.16.
 */
class SimpleQueue<T> implements Iterable<T> {
    private LinkedList<T> storage;

    public SimpleQueue() {
        this.storage = new LinkedList<T>();
    }

    void add(T t) {
        storage.offer(t);
    }

    T get() {
        return storage.poll();
    }

    @Override
    public Iterator<T> iterator() {
        return storage.iterator();
    }
}
