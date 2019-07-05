package com.artemsirosh.tij.concurrency;

/**
 * Created at 05-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class Chopstick {

    private boolean taken;

    Chopstick() {
        taken = false;
    }

    synchronized void taken() throws InterruptedException {
        while (taken)
            wait();

        taken = true;
    }

    synchronized void drop() {
        taken = false;
        notifyAll();
    }
}
