package com.artemsirosh.tij.concurrency.garden;

/**
 * Created at 11-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class Count {
    private int count = 0;

    synchronized int increment() {
        return count++;
    }

    synchronized int value() {
        return count;
    }
}
