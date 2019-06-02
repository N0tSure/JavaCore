package com.artemsirosh.tij.generics;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cresh on 01.12.16.
 */
class ClassTypeCapture {
    private Map<String, Class<?>> kinds;

    ClassTypeCapture() {
        kinds = new HashMap<>();
    }

    void addType(String name, Class<?> kind) {
        kinds.put(name, kind);
    }

    Object createNew(String name) {
        try {
            return kinds.get(name).newInstance();
        } catch (InstantiationException | IllegalAccessException | NullPointerException exception) {
            System.out.println(exception);
        }
        return null;
    }

}
