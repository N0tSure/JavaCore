package com.artemsirosh.tij.concurrency.conveyor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.BrokenBarrierException;

/**
 * Created at 23-09-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class FinishingAssembler extends Assembler {

    FinishingAssembler(BlockingQueue<Vehicle> assembledQueue, BlockingQueue<Vehicle> finishedQueue, RobotPool pool) {
        super(assembledQueue, finishedQueue, pool);
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    setVehicle(getIncomingQueue().take());
                    System.out.println(this + ": " + getVehicle() + " acquired.");
                }

                getPool().hire(Robot.ON_BOARD_ELECTRONICS_INSTALLATION_ROBOT, this);
                getPool().hire(Robot.INTERIOR_ASSEMBLY_ROBOT, this);
                getPool().hire(Robot.EXTERIOR_SUITE_INSTALL_ROBOT, this);
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
