package com.artemsirosh.tij.concurrency;

import java.util.concurrent.CountDownLatch;

/**
 * Created on 08 Jul, 2019.
 *
 * @author Artemis A. Sirosh
 */
class WaitingTask implements Runnable {

    private final int id;
    private final CountDownLatch latch;

    WaitingTask(int id, CountDownLatch latch) {
        this.id = id;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            latch.await();
            System.out.println(this + ": latch barrier passed.");
        } catch (InterruptedException exc) {
            System.out.println(this + ": interrupted.");
        }
    }

    @Override
    public String toString() {
        return "WaitingTask #" + id;
    }
}
