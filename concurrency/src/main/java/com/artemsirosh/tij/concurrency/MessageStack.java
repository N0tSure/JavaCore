package com.artemsirosh.tij.concurrency;

import java.lang.reflect.Array;

/**
 * Created at 28-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
abstract class MessageStack<T> {

    private final T[] storage;
    private int index;

    /**
     * Direct type mark is used to instantiate T array, hence casting is safe.
     * @param type type mark
     * @param size size of storage
     */
    @SuppressWarnings("unchecked")
    MessageStack(Class<T> type, int size) {
        this.storage = (T[]) Array.newInstance(type, size);
        this.index = 0;

    }

    synchronized void push(T element) {
        try {
            while (index >= storage.length)
                wait();

            storage[index++] = element;
        } catch (InterruptedException exc) {
            System.out.println(this + ": interrupted.");
        }
    }

    synchronized T pop() {
        try {
            while (index <= 0)
                wait();

            notifyAll();
            return storage[--index];
        } catch (InterruptedException exc) {
            System.out.println(this + ": interrupted.");
            return null;
        }
    }
}
