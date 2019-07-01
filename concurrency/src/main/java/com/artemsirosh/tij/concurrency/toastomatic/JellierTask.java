package com.artemsirosh.tij.concurrency.toastomatic;

import java.util.concurrent.BlockingQueue;

/**
 * Created at 02-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class JellierTask implements Runnable {

    private final BlockingQueue<Toast> buttered, jellied;

    JellierTask(BlockingQueue<Toast> buttered, BlockingQueue<Toast> jellied) {
        this.buttered = buttered;
        this.jellied = jellied;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                final Toast toast = buttered.take();
                toast.jelly();

                System.out.println(toast);
                jellied.put(toast);
            }
        } catch (InterruptedException exc) {
            System.out.println("Jellier: interrupted");
        }
    }
}
