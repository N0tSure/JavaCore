package com.artemsirosh.tij.concurrency.waxomatic;

import java.util.concurrent.TimeUnit;

/**
 * Created on 22 Jun, 2019.
 *
 * @author Artemis A. Sirosh
 */
public class WaxOn implements Runnable {

    private final Car car;

    public WaxOn(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println(this + ": wax on!");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                car.waitForBuffing();
            }
        } catch (InterruptedException exc) {
            System.out.println(this + ": interrupted due sleep or waiting.");
        }

        System.out.println(this + ": ending task.");
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
