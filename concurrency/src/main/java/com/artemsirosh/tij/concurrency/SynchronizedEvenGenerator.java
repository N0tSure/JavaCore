package com.artemsirosh.tij.concurrency;

/**
 * Created at 08-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class SynchronizedEvenGenerator extends EvenGenerator {

    private int currentValue = 0;

    @Override
    public synchronized int next() {
        ++currentValue;
        Thread.yield(); // cause failure faster
        ++currentValue;
        return currentValue;
    }
}
