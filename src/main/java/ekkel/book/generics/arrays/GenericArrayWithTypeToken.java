package ekkel.book.generics.arrays;

import java.lang.reflect.Array;

/**
 * Created by cresh on 03.12.16.
 */
class GenericArrayWithTypeToken<T> {
    private T[] array;
    @SuppressWarnings("unchecked")
    GenericArrayWithTypeToken(Class<T> type, int size) {
        array = (T[]) Array.newInstance(type, size);
    }

    void put(int index, T item) {
        array[index] = item;
    }

    T get(int index) {
        return array[index];
    }

    T[] replica() {
        return array;
    }
}
