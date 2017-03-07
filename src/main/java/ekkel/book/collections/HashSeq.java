//package ekkel.book.collections;
//
//import java.util.*;
//
//import typeinfo.pets.Pet;
//import typeinfo.pets.Pets;
//
///**
// * Created by cresh on 10.03.16.
// */
//public class HashSeq {
//    static Map<Integer,Pet> build() {
//        Map<Integer,Pet> map = new HashMap<Integer, Pet>();
//        for (int i = 0; i < 8; i++) {
//            map.put(((int) Math.round((Math.random())*100)), Pets.randomPet());
//        }
//        return map;
//    }
//
//    public static void main(String[] args) {
//        Map<Integer,Pet> map = build();
//        System.out.println(map);
//        List<Integer> keylist = new ArrayList<Integer>(map.keySet());
//        Collections.sort(keylist);
//
//        Map<Integer,Pet> linkmap = new LinkedHashMap<Integer, Pet>();
//        Iterator<Integer> iterator = keylist.iterator();
//        int i = 0;
//        while (iterator.hasNext()) {
//            i=iterator.next();
//            linkmap.put(i,map.get(i));
//        }
//
//        System.out.println(linkmap);
//        for (Integer integer : linkmap.keySet()) {
//            System.out.println("Key: " + integer + ", hash: " + integer.hashCode() + ", Pet: " + linkmap.get(integer));
//        }
//
//        System.out.println();
//        Set<Integer> setH = new HashSet<Integer>(map.keySet());
//        System.out.println("Original HashSet: " + setH);
//        System.out.println();
//
//        keylist = new ArrayList<Integer>(setH);
//        Collections.sort(keylist);
//
//        Set<Integer> setL = new LinkedHashSet<Integer>();
//        iterator = keylist.iterator();
//        while (iterator.hasNext()) {
//            i=iterator.next();
//            setL.add(i);
//        }
//        System.out.println("Linked Set: " + setL);
//    }
//}
