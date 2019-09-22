package com.artemsirosh.tij.concurrency.conveyor;

import com.artemsirosh.tij.concurrency.SerialNumberGenerator;

import java.util.function.Consumer;

/**
 * Created at 22-09-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
enum Robot implements Runnable {

    ENGINE_ROBOT(Robot::setEngineNumber),
    TRANSMISSION_ROBOT(Robot::setTransmissionNumber),
    DRIVE_TRAIN(Robot::setDriveTrainNumber);

    private static final SerialNumberGenerator SERIAL_NUMBER_GENERATOR = new SerialNumberGenerator();

    private final Consumer<Vehicle> vehicleProcessor;
    private Assembler assembler;

    private static void setEngineNumber(Vehicle vehicle) {
        final int num = SERIAL_NUMBER_GENERATOR.nextSerialNumber() % 10_000;
        vehicle.setEngineIdentityNumber(String.format("BFB%05d", num));
    }

    private static void setTransmissionNumber(Vehicle vehicle) {
        final int num = SERIAL_NUMBER_GENERATOR.nextSerialNumber() % 10_000;
        vehicle.setTransmissionIdentityNumber(String.format("AMT%05d", num));
    }

    private static void setDriveTrainNumber(Vehicle vehicle) {
        final int num = SERIAL_NUMBER_GENERATOR.nextSerialNumber() % 10_000;
        vehicle.setDriveTrainIdentityNumber(String.format("8E%05d", num));
    }

    Robot(Consumer<Vehicle> vehicleProcessor) {
        this.vehicleProcessor = vehicleProcessor;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                final Assembler assembler = waitForAssembler();
                assembler.processVehicle(vehicleProcessor, this);
            }
        } catch (InterruptedException exc) {
            // acceptable way to exit
        }

        System.out.println(this + ": stopping.");
    }

    synchronized void engage(Assembler assembler) {
        this.assembler = assembler;
        notifyAll();
    }

    private synchronized Assembler waitForAssembler() throws InterruptedException {
        while (this.assembler == null)
            wait();

        System.out.println(this + ": power on.");

        final Assembler assembler = this.assembler;
        this.assembler = null;
        return assembler;
    }
}
