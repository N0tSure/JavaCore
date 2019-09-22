package com.artemsirosh.tij.concurrency.conveyor;

import com.artemsirosh.tij.concurrency.SerialNumberGenerator;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created at 22-09-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class ChassisBuilder implements Runnable {

    private final static String VIN_TEMPLATE = "WAUZZZ8E73A%1$06d";

    private final BlockingQueue<Vehicle> vehicles;
    private final SerialNumberGenerator serialNumberGenerator;

    ChassisBuilder(BlockingQueue<Vehicle> vehicles, SerialNumberGenerator serialNumberGenerator) {
        this.vehicles = vehicles;
        this.serialNumberGenerator = serialNumberGenerator;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(500);
                final int serialNumber = serialNumberGenerator.nextSerialNumber() % 1_000_000;
                final Vehicle vehicle = new Vehicle(String.format(VIN_TEMPLATE, serialNumber));
                System.out.println(this + ": a new chassis built: " + vehicle.getIdentityNumber());
                vehicles.put(vehicle);
            }
        } catch (InterruptedException exc) {
            // acceptable way to stop task
        }

        System.out.println(this + ": stopped.");
    }

    @Override
    public String toString() {
        return ChassisBuilder.class.getSimpleName();
    }
}
