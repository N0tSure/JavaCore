package com.artemsirosh.tij.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created at 26-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public final class Restaurant implements Runnable {

    private final Chief chief;
    private final WaiterPerson waiter;
    private final BusyBoy busyBoy;
    private final ReentrantLock chiefLock, waiterLock, busyBoyLock, restaurantLock;
    private final Condition cleanCondition, hasFoodCondition, mealTakedCondition, mealReadyCondition;
    private final int mealLimit;

    private Meal meal;
    private boolean clean;
    private int mealCount;

    public Restaurant(int mealLimit) {
        this.mealLimit = mealLimit;
        this.waiter = this.new WaiterPerson();
        this.chief = this.new Chief();
        this.busyBoy = this.new BusyBoy();
        this.chiefLock = new ReentrantLock();
        this.waiterLock = new ReentrantLock();
        this.busyBoyLock = new ReentrantLock();
        this.restaurantLock = new ReentrantLock();
        this.cleanCondition = busyBoyLock.newCondition();
        this.hasFoodCondition = restaurantLock.newCondition();
        this.mealTakedCondition = chiefLock.newCondition();
        this.mealReadyCondition = waiterLock.newCondition();
        this.mealCount = 0;
        this.clean = false;
    }

    public Runnable getBusyBoy() {
        return busyBoy;
    }

    public Runnable getChief() {
        return chief;
    }

    public Runnable getWaiterPerson() {
        return waiter;
    }

    @Override
    public void run() {

        restaurantLock.lock();
        try {
            while (hasFood())
                hasFoodCondition.await();

            System.out.println("Restaurant: out of food.");
        } catch (InterruptedException exc) {
            System.out.println("Restaurant: interrupted.");
        } finally {
            restaurantLock.unlock();
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

    private class BusyBoy implements Runnable {

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    busyBoyLock.lock();
                    try {
                        while (clean)
                            cleanCondition.await();
                    } finally {
                        busyBoyLock.unlock();
                    }

                    TimeUnit.MILLISECONDS.sleep(100);
                    System.out.println(this + ": clean up.");
                    clean = true;
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

    private class Chief implements Runnable {

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    if (hasFood()) {
                        chiefLock.lock();
                        try {
                            while (meal != null)
                                mealTakedCondition.await();
                        } finally {
                            chiefLock.unlock();
                        }

                        System.out.println(this + ": order up!");
                        waiterLock.lock();
                        try {
                            meal = new Meal(++mealCount);
                            mealReadyCondition.signalAll();
                        } finally {
                            waiterLock.unlock();
                        }

                        TimeUnit.MILLISECONDS.sleep(100);
                    } else {
                        restaurantLock.lock();
                        try {
                            hasFoodCondition.signalAll();
                        } finally {
                            restaurantLock.unlock();
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

                    waiterLock.lock();
                    try {
                        while (meal == null)
                            mealReadyCondition.await();
                    } finally {
                        waiterLock.unlock();
                    }

                    System.out.println(this + ": got " + meal);
                    chiefLock.lock();
                    try {
                        meal = null;
                        mealTakedCondition.signalAll();
                    } finally {
                        chiefLock.unlock();
                    }

                    busyBoyLock.lock();
                    try {
                        clean = false;
                        cleanCondition.signalAll();
                    } finally {
                        busyBoyLock.unlock();
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
