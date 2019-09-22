package com.artemsirosh.tij.concurrency.conveyor;

import com.artemsirosh.tij.concurrency.SerialNumberGenerator;

import java.time.LocalDate;
import java.util.function.Consumer;

/**
 * Created at 22-09-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
enum Robot implements Runnable {

    ENGINE_ROBOT(Robot::setEngineNumber),
    TRANSMISSION_ROBOT(Robot::setTransmissionNumber),
    DRIVE_TRAIN(Robot::setDriveTrainNumber),
    ON_BOARD_ELECTRONICS_INSTALLATION_ROBOT(Robot::setOnBoardElectronicsNumber),
    INTERIOR_ASSEMBLY_ROBOT(Robot::setInteriorQualityCheck),
    EXTERIOR_SUITE_INSTALL_ROBOT(Robot::setExteriorSuite);

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

    private static void setOnBoardElectronicsNumber(Vehicle vehicle) {
        final int num = SERIAL_NUMBER_GENERATOR.nextSerialNumber() % 10_000;
        vehicle.setOnBoardElectronicsNumber(String.format("8E%05d", num));
    }

    private static void setInteriorQualityCheck(Vehicle vehicle) {
        final LocalDate date = LocalDate.now();
        final int num = SERIAL_NUMBER_GENERATOR.nextSerialNumber() % 1_000;
        vehicle.setInteriorQualityCheck(String.format("%s_%04d", date, num));
    }

    private static void setExteriorSuite(Vehicle vehicle) {
        final int num = SERIAL_NUMBER_GENERATOR.nextSerialNumber() % 10_000;
        vehicle.setExteriorSuiteNumber(String.format("8E%05d", num));
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
