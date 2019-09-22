package com.artemsirosh.tij.concurrency.conveyor;

import java.util.concurrent.BlockingQueue;

/**
 * Created at 22-09-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class Reporter implements Runnable {

    private final BlockingQueue<Vehicle> vehicles;

    Reporter(BlockingQueue<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println(this + ": " + vehicles.take());
            }
        } catch (InterruptedException exc) {
            // acceptable way to exit
        }

        System.out.println(this + ": stopping.");
    }

    @Override
    public String toString() {
        return "Reporter";
    }
}
