package com.artemsirosh.tij.concurrency.restaurant;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.function.IntSupplier;

/**
 * Created at 20-09-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class OrderQueue {

    private final BlockingQueue<Order> orders;
    private final IntSupplier orderIdSupplier;

    OrderQueue(BlockingQueue<Order> orders, IntSupplier orderIdSupplier) {
        this.orders = orders;
        this.orderIdSupplier = orderIdSupplier;
    }

    void placeOrder(final List<Dish> dishes, final Waiter waiter) throws InterruptedException {
        for (final Dish dish : dishes) {
            orders.put(new Order(orderIdSupplier.getAsInt(), dish.getGuest(), dish.getItem(), waiter));
        }
    }

    Order nextOrder() throws InterruptedException {
        return orders.take();
    }
}
