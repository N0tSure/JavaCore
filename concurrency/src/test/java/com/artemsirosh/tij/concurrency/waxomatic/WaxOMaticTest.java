package com.artemsirosh.tij.concurrency.waxomatic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created on 22 Jun, 2019.
 *
 * @author Artemis A. Sirosh
 */
class WaxOMaticTest {

    @Test
    @DisplayName("Simple wait-notify demo")
    void simpleWaitNotifyDemo() throws InterruptedException {

        final ExecutorService executor = Executors.newCachedThreadPool();
        final Car car = new DefaultCar();
        executor.execute(new WaxOn(car));
        executor.execute(new WaxOff(car));

        TimeUnit.SECONDS.sleep(5);
        executor.shutdownNow();
    }
}
