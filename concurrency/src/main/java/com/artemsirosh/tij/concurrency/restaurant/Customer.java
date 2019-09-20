package com.artemsirosh.tij.concurrency.restaurant;

import com.artemsirosh.tij.enumerated.menu.Course;
import com.artemsirosh.tij.enumerated.menu.Food;

import java.util.concurrent.SynchronousQueue;

/**
 * Created at 20-09-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class Customer implements Runnable {

    private final int id;
    private final Waiter waiter;
    private final SynchronousQueue<Plate> plateSetting;

    Customer(int id, Waiter waiter, SynchronousQueue<Plate> plateSetting) {
        this.id = id;
        this.waiter = waiter;
        this.plateSetting = plateSetting;
    }

    void deliver(Plate plate) throws InterruptedException {
        plateSetting.put(plate);
    }

    @Override
    public void run() {
        try {
            for (final Course course : Course.values()) {
                final Food food = course.randomSelection();
                waiter.placeOrder(this, food);
                System.out.println(this + ": eating " + plateSetting.take());
            }
        } catch (InterruptedException exc) {
            System.out.println(this + ": waiting course interrupted.");
        }
    }

    @Override
    public String toString() {
        return "Customer #" + id;
    }
}
