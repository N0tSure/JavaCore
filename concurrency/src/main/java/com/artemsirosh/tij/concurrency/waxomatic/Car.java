package com.artemsirosh.tij.concurrency.waxomatic;

/**
 * Created on 22 Jun, 2019.
 *
 * Model of car which could be waxed or buffed.
 *
 * @author Artemis A. Sirosh
 */
public interface Car {

    /**
     * Cover car by wax.
     */
    void waxed();

    /**
     * Buff waxed car.
     */
    void buffed();

    /**
     * Waits for car coated by wax.
     * @throws InterruptedException if thread will be interrupted due waiting
     */
    void waitForWaxing() throws InterruptedException;

    /**
     * Waits for car buffed.
     * @throws InterruptedException if thread will be interrupted due waiting
     */
    void waitForBuffing() throws InterruptedException;

}
