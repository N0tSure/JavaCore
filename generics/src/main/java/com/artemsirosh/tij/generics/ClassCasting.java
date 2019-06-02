package com.artemsirosh.tij.generics;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * Created by cresh on 15.12.16.
 */
class ClassCasting {
    @SuppressWarnings("unchecked")
    void f(String[] args) throws Exception {
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(args[0]));

        List<String> strings = List.class.cast(inputStream.readObject());
    }
}
