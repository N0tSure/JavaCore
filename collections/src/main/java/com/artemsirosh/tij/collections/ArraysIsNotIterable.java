package com.artemsirosh.tij.collections;

import java.util.*;
/**
 * Created by cresh on 16.03.16.
 */
public class ArraysIsNotIterable {
    static <T> void test(Iterable<T> iterable) {
        for (T t : iterable) {
            System.out.print(t+" ");
        }
    }

    public static void main(String[] args) {
        test(Arrays.asList(1,2,3));
        String[] strings = {"A", "B", "C"};
        //!test(strings);
        test(Arrays.asList(strings));
    }
}
