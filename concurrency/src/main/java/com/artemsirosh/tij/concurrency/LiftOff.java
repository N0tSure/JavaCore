package com.artemsirosh.tij.concurrency;

/**
 * Created at 03-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class LiftOff implements Runnable {
    private static int taskCount = 0;

    private int countDown;
    private final int id;

    LiftOff() {
        this(10);
    }

    LiftOff(int countDown) {
        this.id = taskCount++;
        this.countDown = countDown;
    }

    @Override
    public void run() {
        while (countDown-- > 0) {
            System.out.print(this);
            Thread.yield();
        }
    }

    @Override
    public String toString() {
        return String.format("#%d(%s), ", id, countDown > 0 ? countDown : "Liftoff!");
    }
}
