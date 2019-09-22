package com.artemsirosh.tij.concurrency.conveyor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.BrokenBarrierException;

/**
 * Created at 23-09-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class MainAssembler extends Assembler {

    MainAssembler(BlockingQueue<Vehicle> chassisQueue, BlockingQueue<Vehicle> vehicleQueue, RobotPool pool) {
        super(chassisQueue, vehicleQueue, pool);
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    setVehicle(getIncomingQueue().take());
                    System.out.println(this + ": " + getVehicle() + " acquired.");
                }

                getPool().hire(Robot.ENGINE_ROBOT, this);
                getPool().hire(Robot.TRANSMISSION_ROBOT, this);
                getPool().hire(Robot.DRIVE_TRAIN, this);
                getCyclicBarrier().await();

                getOutComingQueue().put(getVehicle());
            }
        } catch (InterruptedException exc) {
            // way to stop task
        } catch (BrokenBarrierException exc) {
            System.out.println(this + ": a " + "broken barrier.");
        }
    }
}
