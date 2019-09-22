package com.artemsirosh.tij.concurrency.conveyor;

import java.util.EnumSet;

/**
 * Created at 22-09-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class RobotPool {

    private final EnumSet<Robot> pool;

    RobotPool(EnumSet<Robot> pool) {
        this.pool = pool;
    }

    synchronized void hire(Robot robot, Assembler assembler) throws InterruptedException {
        while (!pool.remove(robot))
            wait();

        robot.engage(assembler);
    }

    synchronized void release(Robot robot) {
        pool.add(robot);
        notifyAll();
    }
}
