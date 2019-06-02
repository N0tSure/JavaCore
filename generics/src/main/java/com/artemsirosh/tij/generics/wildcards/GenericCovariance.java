package com.artemsirosh.tij.generics.wildcards;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cresh on 12.12.16.
 */
class GenericCovariance {
    public static void main(String[] args) {
        List<? extends Fruit> fruits = new ArrayList<>();
//        fruits.add(new Apple());
//        fruits.add(new Fruit());
//        fruits.add(new Object());
        fruits.add(null);
        Fruit fruit = fruits.get(0);

        List<? extends Number> numbers = new ArrayList<Integer>();
//        numbers.add(new Integer(1));
//        numbers.add(new CovariantArrays.Value());
        numbers.add(null);
        Number number = numbers.get(0);
    }
}
