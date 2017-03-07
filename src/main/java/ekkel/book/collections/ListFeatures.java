//package ekkel.book.collections;
//
//import typeinfo.pets.*;
//import java.util.*;
//import static ekkel.book.util.Print.*;
//
///**
// * Created by cresh on 26.02.16.
// */
//public class ListFeatures {
//    public static void main(String[] args) {
//        Random rand = new Random();
//        List<Pet> pets = Pets.arrayList(7);
//        print("Лист питомцев: " + pets);
//        Hamster h = new Hamster();
//        pets.add(h);
//        print("Добавлен элемент: " + pets);
//        print("Проверка на содержание элемента: " + pets.contains(h));
//        pets.remove(h);
//        Pet p = pets.get(2);
//        print("Элемент р и его индекс: " + p + " " + pets.indexOf(p));
//        Pet cymbic = new Cymric();
//        print("Индекс элемента цимбик: " + pets.indexOf(cymbic));
//        print("Удаление элемента цимбик: " + pets.remove(cymbic));
//        print(": " + pets.remove(p));
//        print("8: " + pets);
//        pets.add(3,new Mouse());
//        print("9: " + pets);
//        List<Pet> sub = pets.subList(1,4);
//        print(" :" + sub);
//        print("10 :" + pets.containsAll(sub));
//        Collections.sort(sub);
//        print(" : " + sub);
//        print("11: " + pets.containsAll(sub));
//        Collections.shuffle(sub,rand);
//        print(" : " + sub);
//        print("12: " + pets.containsAll(sub));
//        List<Pet> copy = new ArrayList<Pet>(pets);
//        sub = Arrays.asList(pets.get(1),pets.get(4));
//        print("sub: " + sub);
//        copy.retainAll(sub);
//        print("13: " + copy);
//        copy = new ArrayList<Pet>(pets);
//        copy.remove(2);
//        print("14: " + copy);
//        copy.retainAll(sub);
//        print("15: " + copy);
//        copy.set(1, new Mouse());
//        print("16: " + copy);
//        copy.addAll(2,sub);
//        print("17: " + copy);
//        print("18: " + pets.isEmpty());
//        pets.clear();
//        print("19: " + pets);
//        print("20: " + pets.isEmpty());
//        pets.addAll(Pets.arrayList(4));
//        print("21: " + pets);
//        Object[] o = pets.toArray();
//        print("22: " + o[1]);
//        Pet[] pa = pets.toArray(new Pet[0]);
//        print("23: " + pa[0].id());
//    }
//
//}
