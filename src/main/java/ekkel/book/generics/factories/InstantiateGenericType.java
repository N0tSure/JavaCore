package ekkel.book.generics.factories;

import ekkel.book.generics.coffee.Coffee;

/**
 * Created by cresh on 01.12.16.
 */
class InstantiateGenericType {
    public static void main(String[] args) {
        ClassAsFactory<Coffee> coffeeClassAsFactory = new ClassAsFactory<>(Coffee.class);
        System.out.println("ClassAsFactory<Coffee> succeed");
        try {
            ClassAsFactory<Integer> integerFactory = new ClassAsFactory<>(Integer.class);
        } catch (RuntimeException exc) {
            System.out.println("ClassAsFactory<Integer> failed");
        }
    }
}
