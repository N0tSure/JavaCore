package com.artemsirosh.tij.util;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by cresh on 28.08.16.
 */
public class Sets {
    public static <T> Set<T> union(Set<T> a, Set<T> b) {
        Set<T> result = new HashSet<T>(a);
//        if (a instanceof EnumSet) {
//            System.out.print("Check enumset: ");
//            result = ((EnumSet) a).clone();
//        }
//        else result = new HashSet<T>(a);
        result.addAll(b);
        return result;
    }

    public static <T> Set<T> intersection(Set<T> a, Set<T> b) {
        Set<T> result = new HashSet<T>(a);
        result.retainAll(b);
        return result;
    }

    public static <T> Set<T> difference(Set<T> superset, Set<T> subset) {
        Set<T> result = new HashSet<T>(superset);
        result.removeAll(subset);
        return result;
    }

    public static <T> Set<T> complement(Set<T> a, Set<T> b) {
        return difference(union(a,b),difference(a,b));
    }
}
