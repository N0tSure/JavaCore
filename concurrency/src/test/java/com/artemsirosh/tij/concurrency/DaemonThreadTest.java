package com.artemsirosh.tij.concurrency;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created at 07-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class DaemonThreadTest {

    private static void sleepForAWhile() {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException exc) {
            System.err.println("Interrupted!");
        } finally {
            System.out.println("Into finally block.");
        }
    }

    @Test
    void tryFinallyBlock() {
        final Thread thread = new Thread(DaemonThreadTest::sleepForAWhile);
        thread.setDaemon(true);
        thread.start();
    }

}
