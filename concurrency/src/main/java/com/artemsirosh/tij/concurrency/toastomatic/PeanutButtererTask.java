package com.artemsirosh.tij.concurrency.toastomatic;

import java.util.concurrent.BlockingQueue;

/**
 * Created at 02-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class PeanutButtererTask implements Runnable {

    private final BlockingQueue<Toast> dryQueue, butteredQueue;

    PeanutButtererTask(BlockingQueue<Toast> dryQueue, BlockingQueue<Toast> butteredQueue) {
        this.dryQueue = dryQueue;
        this.butteredQueue = butteredQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                final Toast toast = dryQueue.take();
                toast.peanutButter();

                System.out.println(toast);
                butteredQueue.put(toast);
            }
        } catch (InterruptedException exc) {
            System.out.println("Peanut Butterer: interrupted.");
        }
    }
}
