package com.artemsirosh.tij.generics;

import com.artemsirosh.tij.util.BasicGenerator;
import com.artemsirosh.tij.util.Generator;

/**
 * Created by cresh on 27.08.16.
 */
class BasicGeneratorDemo {
    public static void main(String[] args) {
        Generator<Counted> generator = new BasicGenerator<Counted>(Counted.class);
        for (int i = 0; i < 5; i++) {
            System.out.println(generator.next());
        }
//        Class<?> first = new BasicGenerator<Integer>(Integer.class).getClass();
//        Class<?> second = new BasicGenerator<String>(String.class).getClass();
//        System.out.println(first.equals(second));
    }
}
