package com.artemsirosh.tij.collections;

import java.util.*;

/**
 * Created by cresh on 28.02.16.
 */
public class IntegersTransport {
    ArrayList<Integer> list = new ArrayList<Integer>();

    IntegersTransport() {
        Random rnd = new Random();
        for (int i = 0; i < rnd.nextInt(10); i++) {
            list.add(rnd.nextInt(Integer.MAX_VALUE));
        }
    }

    public ArrayList<Integer> getList() {
        return list;
    }

    public static List<Integer> transpherOne(List<Integer> oldList) {
        List<Integer> newList = new ArrayList<Integer>();
        ListIterator<Integer> iterator = oldList.listIterator(oldList.size());
        while (iterator.hasPrevious()) {
            newList.add(iterator.previous());
        }
        return newList;
    }

    public static List<Integer> transpherTwo(List<Integer> oldList) {
        List<Integer> newList = new ArrayList<Integer>();
        ListIterator<Integer> iterator = oldList.listIterator(oldList.size());
        while (iterator.hasPrevious()) {
            newList.add(iterator.previous());
        }
        return newList;
    }

    public static void main(String[] args) {
        IntegersTransport ob = new IntegersTransport();
        System.out.println("Original list");
        System.out.println(ob.getList());

        System.out.println("Reverted list");
        List<Integer> list = transpherOne(ob.getList());
        System.out.println(list);
        /*ArrayList<Integer> list = new ArrayList<Integer>();
        List<Integer> integerList = new ArrayList<Integer>(ob.list);
        System.out.println(integerList);
        ListIterator<Integer> iterator = integerList.listIterator(ob.list.size());
        System.out.println(iterator.previousIndex());
        while (iterator.hasPrevious()) list.add(iterator.previous());
        System.out.println(list);*/
    }
}
