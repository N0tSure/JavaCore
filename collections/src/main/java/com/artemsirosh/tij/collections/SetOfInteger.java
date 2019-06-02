package com.artemsirosh.tij.collections;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by NotSure on 03.03.16.
 */
public class SetOfInteger {
    public static void main(String[] args) {
        Random rand = new Random(47);
        Set<Integer> intset = new TreeSet<Integer>();
        for (int i = 0; i < 10000; i++) {
            intset.add(rand.nextInt(30));
        }
        System.out.println(intset);
    }
}
