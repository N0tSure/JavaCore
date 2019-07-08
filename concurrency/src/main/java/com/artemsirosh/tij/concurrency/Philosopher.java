package com.artemsirosh.tij.concurrency;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created at 05-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class Philosopher implements Runnable {

    private final Chopstick left;
    private final Chopstick right;
    private final int id;
    private final int ponderFactor;
    private final Random random;

    Philosopher(Chopstick left, Chopstick right, int id, int ponderFactor) {
        this.left = left;
        this.right = right;
        this.id = id;
        this.ponderFactor = ponderFactor;
        this.random = new Random(47);
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println(this + " thinking.");
                pause(); // think for a while

                System.out.println(this + " grabbing right.");
                right.taken();

                System.out.println(this + " grabbing left.");
                left.taken();

                System.out.println(this + " eating.");
                pause(); // eat for a while

                right.drop();
                left.drop();
            }
        } catch (InterruptedException exc) {
            System.out.println(this + " was interrupted.");
        }
    }

    private void pause() throws InterruptedException {
        if (ponderFactor > 0) {
            TimeUnit.MILLISECONDS.sleep(250 * random.nextInt(ponderFactor));
        }
    }

    @Override
    public String toString() {
        return "Philosopher #" + id;
    }
}
