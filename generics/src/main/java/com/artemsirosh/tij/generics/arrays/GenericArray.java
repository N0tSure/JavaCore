package com.artemsirosh.tij.generics.arrays;

/**
 * Created by cresh on 03.12.16.
 */
class GenericArray<T> {
    private Object[] array;

    GenericArray(int size) {
        array = new Object[size];
    }

    void put(int index, T item) {
        array[index] = item;
    }

    @SuppressWarnings("unchecked")
    T get(int index) {
        return (T) array[index];
    }

    @SuppressWarnings("unchecked")
    T[] replica() {
        return (T[]) array;
    }

    public static void main(String[] args) {
        GenericArrayWithTypeToken<Integer> gai = new GenericArrayWithTypeToken<>(Integer.class, 10);
        for (int i = 0; i < 10; i++) {
            gai.put(i, i);
        }
        for (int i = 0; i < 10; i++) {
            System.out.print(gai.get(i) + " ");
        }
        Integer[] integers = gai.replica();
    }
}
