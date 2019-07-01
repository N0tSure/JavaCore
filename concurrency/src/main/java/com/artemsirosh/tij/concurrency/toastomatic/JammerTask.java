package com.artemsirosh.tij.concurrency.toastomatic;

import java.util.concurrent.BlockingQueue;

/**
 * Created at 01-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class JammerTask implements Runnable {

    private final BlockingQueue<Toast> butteredQueue, jammedQueue;

    JammerTask(BlockingQueue<Toast> butteredQueue, BlockingQueue<Toast> jammedQueue) {
        this.butteredQueue = butteredQueue;
        this.jammedQueue = jammedQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                final Toast toast = butteredQueue.take();
                toast.jam();

                System.out.println(toast);
                jammedQueue.put(toast);
            }
        } catch (InterruptedException exc) {
            System.out.println("Jammer: interrupted.");
        }
    }
}
