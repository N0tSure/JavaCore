package com.artemsirosh.tij.concurrency.restaurant;

import com.artemsirosh.tij.enumerated.menu.Course;
import com.artemsirosh.tij.enumerated.menu.Food;

import java.util.concurrent.SynchronousQueue;

/**
 * Created at 20-09-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class Guest implements Runnable {

    private final int id;
    private final Table table;
    private final SynchronousQueue<Plate> plateSetting;

    Guest(int id, Table table, SynchronousQueue<Plate> plateSetting) {
        this.id = id;
        this.table = table;
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
                System.out.println(this + " want " + food);
                table.placeOrder(this, food);
                System.out.println(this + ": eating " + plateSetting.take());
            }
        } catch (InterruptedException exc) {
            System.out.println(this + ": waiting course interrupted.");
        }
    }

    @Override
    public String toString() {
        return "Guest #" + id;
    }
}
