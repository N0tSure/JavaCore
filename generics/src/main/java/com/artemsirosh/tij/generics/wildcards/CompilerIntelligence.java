package com.artemsirosh.tij.generics.wildcards;

import java.util.Arrays;
import java.util.List;

/**
 * Created by cresh on 12.12.16.
 */
class CompilerIntelligence {
    public static void main(String[] args) {
        List<? extends Fruit> fruits = Arrays.asList(new Apple(), new Apple());
        Apple apple = (Apple) fruits.get(0);
        fruits.contains(new Apple());
        fruits.indexOf(new Apple());
    }
}
