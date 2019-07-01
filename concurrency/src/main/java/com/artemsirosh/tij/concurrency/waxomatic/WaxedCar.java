package com.artemsirosh.tij.concurrency.waxomatic;

/**
 * Created at 01-07-2019
 *
 * Represents car which waiting for waxing and when be waxed: buffing.
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
interface WaxedCar {

    /**
     * Buff waxed car.
     */
    void buffed();

    /**
     * Waits for car coated by wax.
     * @throws InterruptedException if thread will be interrupted due waiting
     */
    void waitForWaxing() throws InterruptedException;
}
