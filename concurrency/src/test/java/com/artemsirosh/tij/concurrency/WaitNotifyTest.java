package com.artemsirosh.tij.concurrency;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created on 24 Jun, 2019.
 *
 * @author Artemis A. Sirosh
 */
class WaitNotifyTest {

    private static void sendingASignal(final long timeout) {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(timeout);
                synchronized (WaitNotifyTest.class) {
                    WaitNotifyTest.class.notifyAll();
                    System.out.println("Sender: signal was sent.");
                }
            }
        } catch (InterruptedException exc) {
            System.out.println("Sender: interrupted.");
        }
    }

    private static void waitingForSignal() {
        try {
            while (!Thread.interrupted()) {
                synchronized (WaitNotifyTest.class) {
                    WaitNotifyTest.class.wait();
                    System.out.println("Waiter: acquired signal.");
                }
            }
        } catch (InterruptedException exc) {
            System.out.println("Waiter: interrupted.");
        }
    }

    @Test
    @DisplayName("Simple wait-notify working demo")
    void simpleWaitNotifyDemo() throws InterruptedException {

        final ExecutorService executorService = Executors.newCachedThreadPool();
        final Runnable waiterTask = WaitNotifyTest::waitingForSignal;
        final Runnable senderTask = () -> sendingASignal(500);

        executorService.execute(waiterTask);
        executorService.execute(senderTask);

        TimeUnit.SECONDS.sleep(5);
        executorService.shutdownNow();

    }
}
