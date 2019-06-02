package com.artemsirosh.tij.generics.latency;

import java.util.Collection;

/**
 * Created by cresh on 20.12.16.
 */
public class Adapter {
    static <T> Addable<T> collectionAdapter(Collection<T> collection) {
        return new AddableCollectionAdapter<T>(collection);
    }
}
