package com.artemsirosh.tij.util;

import java.util.ArrayList;

/**
 * Created by cresh on 04.03.17.
 */
public class CollectionData<T> extends ArrayList<T> {
    public CollectionData(Generator<T> generator, int quantity) {
        for (int i = 0; i < quantity; i++) {
            this.add(generator.next());
        }
    }

    public static <T> CollectionData<T> list(Generator<T> generator, int quantity) {
        return new CollectionData<>(generator, quantity);
    }
}
