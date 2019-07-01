package com.artemsirosh.tij.concurrency.waxomatic;

import java.util.concurrent.TimeUnit;

/**
 * Created on 22 Jun, 2019.
 *
 * @author Artemis A. Sirosh
 */
public class WaxOff implements Runnable {

    private final WaxedCar car;

    public WaxOff(WaxedCar car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                car.waitForWaxing();
                System.out.println(this + ": wax off!");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
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
