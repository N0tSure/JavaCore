//package com.artemsirosh.tij.collections;
//
//import java.util.*;
//import typeinfo.pets.*;
///**
// * Created by cresh on 15.03.16.
// */
//public class InterfaceVsIterator {
//    public static void display(Iterator<Pet> it) {
//        System.out.println("Iterator:");
//        while (it.hasNext()) {
//            Pet p = it.next();
//            System.out.print(p.id() + ":" + p + " ");
//        }
//        System.out.println();
//    }
//    public static void display(Collection<Pet> collection) {
//        System.out.println("Collection:");
//        for (Pet p : collection) System.out.print(p.id() + ":" + p + " ");
//        System.out.println();
//    }
//
//    public static void main(String[] args) {
//        List<Pet> petList = Pets.arrayList(8);
//        Set<Pet> petSet = new HashSet<Pet>(petList);
//        Map<String,Pet> petMap = new LinkedHashMap<String, Pet>();
//        String[] names = ("Ralph Eric Robyn Lacey Britney Sam Spot Fluffy").split(" ");
//        for (int i = 0; i < names.length; i++) petMap.put(names[i],petList.get(i));
//        display(petList);
//        display(petSet);
//        display(petList.iterator());
//        display(petSet.iterator());
//        System.out.println(petMap);
//        System.out.println(petMap.keySet());
//        display(petMap.values());
//        display(petMap.values().iterator());
//    }
//}
