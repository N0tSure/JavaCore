package com.artemsirosh.tij.concurrency;

import java.util.Arrays;

/**
 * Created at 09-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class CircularSet {

    private final int[] storage;
    private final int length;

    private int index;

    CircularSet(int length) {
        this.storage = new int[length];
        this.length = length;

        Arrays.fill(storage, -1);
    }

    synchronized void add(int item) {
        storage[index] = item;
        index = ++index % length;
    }

    synchronized boolean contains(int item) {
        return Arrays.stream(storage).anyMatch(i -> i == item);
    }


}
