package com.artemsirosh.tij.concurrency;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * Created at 26-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class NotifyVsNotifyAllTest {

    private static Runnable getBlockedTask(Blocker blocker) {
        return blocker::waitingCall;
    }

    @Test
    @DisplayName("Demonstration of differences between notify() and notifyAll()")
    void notifyVsNotifyAllDemo() throws InterruptedException {
        final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(7);
        final Blocker blocker1 = new Blocker(1);
        final Blocker blocker2 = new Blocker(2);
        Stream.generate(() -> getBlockedTask(blocker1))
                .limit(5)
                .forEach(executorService::execute);

        executorService.execute(getBlockedTask(blocker2));
        Future<?> future = executorService.scheduleAtFixedRate(
                new UnBlocker(blocker1), 0, 400, TimeUnit.MILLISECONDS
        );

        TimeUnit.SECONDS.sleep(5);
        future.cancel(true);
        System.out.println("\nScheduled task stopped.");

        TimeUnit.MILLISECONDS.sleep(500);
        System.out.print(blocker2 + " notifyAll: ");
        blocker2.prodAll();

        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("\nMain: executor stopped.");
        executorService.shutdownNow();
        if (!executorService.awaitTermination(1, TimeUnit.SECONDS)) {
            System.out.println("Not all task was finished.");
        }

    }

    private static class Blocker {

        private final int id;

        Blocker(int id) {
            this.id = id;
        }

        synchronized void waitingCall() {
            try {
                while (!Thread.interrupted()) {
                    wait();
                    System.out.print(Thread.currentThread().getName() + " ");
                }
            } catch (InterruptedException exc) {
                System.out.println(this + ": interrupted.");
            }
        }

        synchronized void prod() {
            notify();
        }

        synchronized void prodAll() {
            notifyAll();
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + " #" + id;
        }
    }

    private static class UnBlocker implements Runnable {

        private boolean prod;
        private final Blocker blocker;

        UnBlocker(Blocker blocker) {
            this.blocker = blocker;
            this.prod = true;
        }

        @Override
        public void run() {
            if (prod) {
                System.out.print("\n" + blocker + " notify: ");
                blocker.prod();
                prod = false;
            } else {
                System.out.print("\n" + blocker + " notifyAll: ");
                blocker.prodAll();
                prod = true;
            }
        }
    }
}
