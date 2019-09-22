package com.artemsirosh.tij.concurrency.restaurant;

import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * Created at 20-09-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class Waiter implements Runnable {

    private final int id;
    private final OrderQueue orderQueue;
    private final BlockingQueue<Plate> filledOrders;

    Waiter(int id, OrderQueue orderQueue, BlockingQueue<Plate> filledOrders) {
        this.id = id;
        this.orderQueue = orderQueue;
        this.filledOrders = filledOrders;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                final Plate plate = filledOrders.take();
                System.out.println(this + ": received " + plate.getFood() + " delivering for " + plate.getCustomer());
                plate.getCustomer().deliver(plate);
            }
        } catch (InterruptedException exc) {
            // way to end a task
        }

        System.out.println(this + ": off duty.");
    }

    void fillOrder(Plate plate) throws InterruptedException {
        filledOrders.put(plate);
    }

    void placeOrder(List<Dish> dishes) {
        try {
            orderQueue.placeOrder(dishes, this);
        } catch (InterruptedException exc) {
            System.out.println(this + ": placing of order ticket interrupted.");
        }
    }

    @Override
    public String toString() {
        return "Waiter #" + id;
    }
}
