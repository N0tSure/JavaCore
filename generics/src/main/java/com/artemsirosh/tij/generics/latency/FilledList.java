package com.artemsirosh.tij.generics.latency;

import java.util.ArrayList;

/**
 * Created by cresh on 19.12.16.
 */
class FilledList<T> extends ArrayList<T> {
    public FilledList(Class<? extends T> type, int size) {
        try {
            for (int i = 0; i < size; i++) {
                this.add(type.newInstance());
            }
        } catch (IllegalAccessException | InstantiationException exc) {
            throw new RuntimeException(exc);
        }
    }
}
