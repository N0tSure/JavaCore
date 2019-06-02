package com.artemsirosh.tij.generics.arrays;

import com.artemsirosh.tij.collections.DoubleQueue;

/**
 * Created by cresh on 02.12.16.
 */
class ArrayOfGeneric {
    static final int SIZE = 100;
    static Generic<Integer>[] gia;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
//        gia = (Generic<Integer>[]) new Object[SIZE];
        gia = (Generic<Integer>[]) new Generic[SIZE];
        System.out.println(gia.getClass().getSimpleName());
        gia[0] = new Generic<Integer>();
        gia[1] = new Generic();
    }
}
