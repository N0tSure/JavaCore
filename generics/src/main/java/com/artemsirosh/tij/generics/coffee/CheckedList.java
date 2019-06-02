package com.artemsirosh.tij.generics.coffee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by cresh on 15.12.16.
 */
class CheckedList {
    @SuppressWarnings("unchecked")
    private static void oldStyleMethod(List probablyMocha) {
        probablyMocha.add(new Latte());
    }

    public static void main(String[] args) {
        List<Breve> breves = new ArrayList<>();
        oldStyleMethod(breves);
        List<Breve> checked = Collections.checkedList(new ArrayList<Breve>(), Breve.class);
        try {
            oldStyleMethod(checked);
        } catch (ClassCastException exc) {
            System.err.println(exc.getMessage());
        }
        List<Coffee> coffees = Collections.checkedList(new ArrayList<Coffee>(), Coffee.class);
        coffees.add(new Cappuccino());
        coffees.add(new Breve());
        oldStyleMethod(coffees);
    }
}
