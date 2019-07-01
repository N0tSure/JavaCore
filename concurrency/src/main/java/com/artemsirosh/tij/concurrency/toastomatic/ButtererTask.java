package com.artemsirosh.tij.concurrency.toastomatic;

import java.util.concurrent.BlockingQueue;

/**
 * Created at 01-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class ButtererTask implements Runnable {

    private final BlockingQueue<Toast> dryQueue, butteredQueue;

    ButtererTask(BlockingQueue<Toast> dryQueue, BlockingQueue<Toast> butteredQueue) {
        this.dryQueue = dryQueue;
        this.butteredQueue = butteredQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast toast = dryQueue.take();
                toast.butter();
                System.out.println(toast);
                butteredQueue.put(toast);
            }
        } catch (InterruptedException exc) {
            System.out.println("Butterer: interrupted.");
        }
    }
}
