package com.artemsirosh.tij.concurrency;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created on 24 Jun, 2019.
 *
 * @author Artemis A. Sirosh
 */
public class BusyWait implements Runnable {

    private final boolean busyWaiter;
    private final long interval;

    private volatile boolean ready = false;

    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length >= 1) {
            final ExecutorService executorService = Executors.newCachedThreadPool();
            final long interval = Long.parseLong(args[0]);

            final Runnable waiter;
            final BusyWait busyWait;
            if (args.length > 1 && "-b".equals(args[1])) {
                busyWait = new BusyWait(interval, true);
                waiter = busyWait.new BusyWaiter();
            } else {
                busyWait = new BusyWait(interval, false);
                waiter = busyWait.new RegularWaiter();
            }

            executorService.execute(busyWait);
            executorService.execute(waiter);

            System.out.print("Press any key to exit: ");
            System.in.read();

            executorService.shutdownNow();
            if (!executorService.awaitTermination(interval * 3, TimeUnit.MILLISECONDS)) {
                System.err.println("Not all tasks finished.");
                System.exit(2);
            }
        } else {
            System.out.println("Usage: java com.artemsirosh.tij.concurrency.BusyWait interval");
            System.exit(1);
        }
    }

    private BusyWait(long interval, boolean busyWaiter) {
        this.busyWaiter = busyWaiter;
        this.interval = interval;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(interval);
                if (busyWaiter) {
                    ready = true;
                } else {
                    synchronized (this) {
                        notifyAll();
                    }
                }
                System.out.println("Main: signal was sent.");
            }
        } catch (InterruptedException exc) {
            System.out.println("Main: interrupted.");
        }

        System.out.println("Main: stopping.");
    }

    private class BusyWaiter implements Runnable {

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                while (!ready)
                    ;
                System.out.println("Busy waiter: ready.");
                BusyWait.this.ready = false;
            }
        }
    }

    private class RegularWaiter implements Runnable {

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    synchronized (BusyWait.this) {
                        BusyWait.this.wait();
                        System.out.println("Regular waiter: ready.");
                    }
                }
            } catch (InterruptedException exc) {
                System.out.println("Regular waiter: interrupted.");
            }

            System.out.println("Regular waiter: stopping.");
        }
    }

}
