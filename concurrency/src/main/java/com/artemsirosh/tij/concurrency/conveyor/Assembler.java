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
class Assembler implements Runnable {

    private final BlockingQueue<Vehicle> chassisQueue;
    private final BlockingQueue<Vehicle> vehicleQueue;
    private final RobotPool pool;
    private final CyclicBarrier cyclicBarrier;

    private Vehicle vehicle;

    Assembler(BlockingQueue<Vehicle> chassisQueue, BlockingQueue<Vehicle> vehicleQueue, RobotPool pool) {
        this.chassisQueue = chassisQueue;
        this.vehicleQueue = vehicleQueue;
        this.pool = pool;
        this.cyclicBarrier = new CyclicBarrier(4);
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    vehicle = chassisQueue.take();
                    System.out.println(this + ": " + vehicle + " acquired.");
                }

                pool.hire(Robot.ENGINE_ROBOT, this);
                pool.hire(Robot.TRANSMISSION_ROBOT, this);
                pool.hire(Robot.DRIVE_TRAIN, this);
                cyclicBarrier.await();

                vehicleQueue.put(vehicle);
            }
        } catch (InterruptedException exc) {
            // way to stop task
        } catch (BrokenBarrierException exc) {
            System.out.println(this + ": a " +
                    "broken barrier.");
        }
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
