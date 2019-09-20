package com.artemsirosh.tij.concurrency.restaurant;

import com.artemsirosh.tij.enumerated.menu.Food;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created at 20-09-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class Chief implements Runnable {

    private final int id;
    private final OrderQueue orderQueue;
    private final Random random;

    Chief(int id, OrderQueue orderQueue, Random random) {
        this.id = id;
        this.orderQueue = orderQueue;
        this.random = random;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                final Order order = orderQueue.nextOrder();
                final Food item = order.getItem();
                TimeUnit.MILLISECONDS.sleep(random.nextInt(500));

                final Plate plate = new Plate(order, item);
                order.getWaiter().fillOrder(plate);
            }
        } catch (InterruptedException exc) {
            // acceptable way to exit
        }

        System.out.println(this + ": off duty.");
    }

    @Override
    public String toString() {
        return "Chief #" + id;
    }
}
