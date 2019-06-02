package com.artemsirosh.tij.collections;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cresh on 28.02.16.
 */
public class SubListing {
    static class Subclass {}

    public static void main(String[] args) {
        Subclass[] arrSubclass = new Subclass[10];
        for (int i = 0; i < arrSubclass.length; i++) {
            arrSubclass[i] = new Subclass();
        }
        ArrayList<Subclass> list = new ArrayList<Subclass>(arrSubclass.length);
        for (Subclass subclas : arrSubclass) {
            list.add(subclas);
        }
        List<Subclass> subList = list.subList(2,8);
        list.removeAll(subList);
    }
}
