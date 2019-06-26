package com.artemsirosh.tij.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * Created at 26-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public final class Restaurant implements Runnable {

    private final Chief chief;
    private final WaiterPerson waiter;
    private final int mealLimit;

    private Meal meal;
    private int mealCount;

    public Restaurant(int mealLimit) {
        this.mealLimit = mealLimit;
        this.waiter = this.new WaiterPerson();
        this.chief = this.new Chief();
        this.mealCount = 0;
    }

    public Runnable getChief() {
        return chief;
    }

    public Runnable getWaiterPerson() {
        return waiter;
    }

    @Override
    public void run() {
        try {
            synchronized (this) {
                while (hasFood())
                    wait();
            }

            System.out.println("Restaurant: out of food.");
        } catch (InterruptedException exc) {
            System.out.println("Restaurant: interrupted.");
        }

        System.out.println("Restaurant: closing.");
    }

    private boolean hasFood() {
        return mealCount < mealLimit;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    private class Chief implements Runnable {

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    if (hasFood()) {
                        synchronized (this) {
                            while (meal != null)
                                wait();
                        }

                        System.out.println(this + ": order up!");
                        synchronized (waiter) {
                            meal = new Meal(++mealCount);
                            waiter.notifyAll();
                        }

                        TimeUnit.MILLISECONDS.sleep(100);
                    } else {
                        synchronized (Restaurant.this) {
                            Restaurant.this.notifyAll();
                        }
                    }
                }
            } catch (InterruptedException exc) {
                System.out.println(this + ": interrupted.");
            }

            System.out.println(this + ": stopping.");
        }

        @Override
        public String toString() {
            return getClass().getSimpleName();
        }
    }

    private static final class Meal {
        private final int orderNumber;

        private Meal(int orderNumber) {
            this.orderNumber = orderNumber;
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + " #" + orderNumber;
        }
    }

    private class WaiterPerson implements Runnable {

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    synchronized (this) {
                        while (meal == null)
                            wait();
                    }

                    System.out.println(this + ": got " + meal);
                    synchronized (chief) {
                        meal = null;
                        chief.notifyAll();
                    }
                }
            } catch (InterruptedException exc) {
                System.out.println(this + ": interrupted due wait.");
            }

            System.out.println(this + ": stopping.");
        }

        @Override
        public String toString() {
            return getClass().getSimpleName();
        }
    }
}
