package com.artemsirosh.tij.generics.latency;

import com.artemsirosh.tij.util.Generator;

/**
 * Created by cresh on 19.12.16.
 */
class Fill {
    static <T> void fill(Addable<T> addable,
                         Class<? extends T> type, int size) {
        for (int i = 0; i < size; i++) {
            try {
                addable.add(type.newInstance());
            } catch (InstantiationException | IllegalAccessException exc) {
                throw new RuntimeException(exc);
            }
        }
    }

    static <T> void fill(Addable<T> addable, Generator<T> generator, int size) {
        for (int i = 0; i < size; i++) {
            addable.add(generator.next());
        }
    }
}
