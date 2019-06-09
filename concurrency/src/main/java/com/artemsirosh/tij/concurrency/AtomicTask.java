package com.artemsirosh.tij.concurrency;

/**
 * Created at 09-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class AtomicTask implements Runnable {

    private int value = 0;

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted())
            evenIncrement();
    }

    synchronized int getValue() {
        return value;
    }

    private synchronized void evenIncrement() {
        value++;
        value++;
    }
}
