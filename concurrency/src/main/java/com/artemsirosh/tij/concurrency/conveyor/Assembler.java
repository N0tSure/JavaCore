package com.artemsirosh.tij.concurrency.conveyor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.function.Consumer;

/**
 * Created at 22-09-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
abstract class Assembler implements Runnable {

    private final BlockingQueue<Vehicle> incomingQueue;
    private final BlockingQueue<Vehicle> outComingQueue;
    private final RobotPool pool;
    private final CyclicBarrier cyclicBarrier;

    private Vehicle vehicle;

    Assembler(BlockingQueue<Vehicle> incomingQueue, BlockingQueue<Vehicle> outComingQueue, RobotPool pool) {
        this.incomingQueue = incomingQueue;
        this.outComingQueue = outComingQueue;
        this.pool = pool;
        this.cyclicBarrier = new CyclicBarrier(4);
    }

    BlockingQueue<Vehicle> getIncomingQueue() {
        return incomingQueue;
    }

    BlockingQueue<Vehicle> getOutComingQueue() {
        return outComingQueue;
    }

    RobotPool getPool() {
        return pool;
    }

    CyclicBarrier getCyclicBarrier() {
        return cyclicBarrier;
    }

    Vehicle getVehicle() {
        return vehicle;
    }

    void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    void processVehicle(Consumer<Vehicle> vehicleProcessor, Robot robot) throws InterruptedException {
        try {
            synchronized (this) {
                vehicleProcessor.accept(vehicle);
                System.out.println(this + ": " + robot + " process " + vehicle + ".");
            }
            cyclicBarrier.await();
        } catch (BrokenBarrierException exc) {
            System.out.println(this + ": broken barrier report.");
        } finally {
            pool.release(robot);
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
