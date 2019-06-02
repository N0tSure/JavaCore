package com.artemsirosh.tij.collections;

import java.util.*;
/**
 * Created by cresh on 15.03.16.
 */
public class ForEachCollections {
    public static void main(String[] args) {
        Collection<String> strings = new LinkedList<String>();
        Collections.addAll(strings,"one two three four five".split(" "));
        for (String s : strings) System.out.print("'" + s + "' ");
    }
}
