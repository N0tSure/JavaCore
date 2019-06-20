package com.artemsirosh.tij.concurrency.interrupting;

import java.util.concurrent.TimeUnit;

/**
 * Created at 20-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class CleanUpTask implements Runnable {

    private volatile double number = 0;

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                // point 1
                final NeedsCleanup cleanup1 = new NeedsCleanup(1);
                try {

                    System.out.println(this + ": sleeping.");
                    TimeUnit.SECONDS.sleep(1);

                    // point 2
                    final NeedsCleanup cleanup2 = new NeedsCleanup(2);
                    try {

                        System.out.println(this + ": start calculating.");
                        for (int i = 0; i < 250_000; i++) {
                            number = number + (Math.PI + Math.E) / number;
                        }
                        System.out.println(this + ": calculating finished.");
                    } finally {
                        cleanup2.cleanup();
                    }
                } finally {
                    cleanup1.cleanup();
                }

            }
            System.out.println(this + ": stopping task.");
        } catch (InterruptedException exc) {
            System.out.println(this + ": stopped by sleep interruption.");
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
