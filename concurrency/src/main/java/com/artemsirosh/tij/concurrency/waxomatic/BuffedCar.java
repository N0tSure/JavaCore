package com.artemsirosh.tij.concurrency.waxomatic;

/**
 * Created at 01-07-2019
 *
 * Represents a car which wait for buffing and then be waxed.
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
interface BuffedCar {

    /**
     * Cover car by wax.
     */
    void waxed();

    /**
     * Waits for car buffed.
     * @throws InterruptedException if thread will be interrupted due waiting
     */
    void waitForBuffing() throws InterruptedException;
}
