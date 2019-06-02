package com.artemsirosh.tij.generics.latency;

import java.util.Collection;

/**
 * Created by cresh on 20.12.16.
 */
class AddableCollectionAdapter<T> implements Addable<T> {
    private Collection<T> collection;

    public AddableCollectionAdapter(Collection<T> collection) {
        this.collection = collection;
    }

    @Override
    public void add(T t) {
        collection.add(t);
    }
}
