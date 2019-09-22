package com.artemsirosh.tij.concurrency.conveyor;

import com.artemsirosh.tij.concurrency.SerialNumberGenerator;
import com.artemsirosh.tij.finisher.Finisher;
import com.artemsirosh.tij.finisher.Finishers;

import java.util.EnumSet;
import java.util.concurrent.*;

/**
 * Created at 23-09-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class CarBuilder {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final ExecutorService executor = Executors.newCachedThreadPool();
        final BlockingQueue<Vehicle>
                chassisQueue = new LinkedBlockingQueue<>(),
                assembledQueue = new LinkedBlockingQueue<>(),
                finishedQueue = new LinkedBlockingQueue<>();
        final RobotPool robotPool = new RobotPool(EnumSet.allOf(Robot.class));

        executor.execute(Robot.ENGINE_ROBOT);
        executor.execute(Robot.TRANSMISSION_ROBOT);
        executor.execute(Robot.DRIVE_TRAIN);
        executor.execute(Robot.ON_BOARD_ELECTRONICS_INSTALLATION_ROBOT);
        executor.execute(Robot.INTERIOR_ASSEMBLY_ROBOT);
        executor.execute(Robot.EXTERIOR_SUITE_INSTALL_ROBOT);

        executor.execute(new Reporter(finishedQueue));
        executor.execute(new MainAssembler(chassisQueue, assembledQueue, robotPool));
        executor.execute(new FinishingAssembler(assembledQueue, finishedQueue, robotPool));
        executor.execute(new ChassisBuilder(chassisQueue, new SerialNumberGenerator()));

        Finisher<?> finisher = Finishers.newJMXFinisher("CarBuilder");
        Future<?> future = executor.submit(finisher);
        future.get();

        executor.shutdownNow();
    }
}
