package com.artemsirosh.tij.concurrency.waxomatic;

/**
 * Created on 22 Jun, 2019.
 *
 * @author Artemis A. Sirosh
 */
public class DefaultCar implements Car {

    private boolean waxOn;

    DefaultCar() {
        this.waxOn =false;
    }

    @Override
    public synchronized void waxed() {
        waxOn = true;
        notifyAll();
    }

    @Override
    public synchronized void buffed() {
        waxOn = false;
        notifyAll();
    }

    @Override
    public synchronized void waitForWaxing() throws InterruptedException {
        while (!waxOn) {
            wait();
        }
    }

    @Override
    public synchronized void waitForBuffing() throws InterruptedException {
        while (waxOn) {
            wait();
        }
    }
}
