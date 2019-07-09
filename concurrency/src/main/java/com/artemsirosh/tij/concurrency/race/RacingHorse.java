package com.artemsirosh.tij.concurrency.race;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created at 09-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class RacingHorse implements Horse {

    private final int id;
    private final Random random;
    private final CyclicBarrier barrier;

    private int strides;

    RacingHorse(int id, Random random, CyclicBarrier barrier) {
        this.id = id;
        this.random = random;
        this.barrier = barrier;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public synchronized int getStrides() {
        return strides;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    strides += random.nextInt(3);
                }

                barrier.await();
            }
        } catch (InterruptedException exc) {
            System.out.println(this + ": interrupted.");
        } catch (BrokenBarrierException exc) {
            throw new RuntimeException(exc);
        }

        System.out.println(this + ": stopped.");
    }

    @Override
    public String toString() {
        return "Horse #" + id;
    }
}
