package com.artemsirosh.tij.typeinfo.pets;

import com.artemsirosh.tij.util.New;

import java.util.List;
import java.util.Map;

/**
 * Created by cresh on 27.08.16.
 */
class LimitsOfInference {
    static void f(Map<Person, List<? extends Pet>> petPeople) {

    }
    static void g(Map<Person, List<Pet>> petPeople) {

    }

    public static void main(String[] args) {
        g(New.<Person, List<Pet>>map());

    }
}
