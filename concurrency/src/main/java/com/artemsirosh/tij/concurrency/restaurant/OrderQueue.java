package com.artemsirosh.tij.concurrency.restaurant;

import com.artemsirosh.tij.enumerated.menu.Food;

import java.util.concurrent.BlockingQueue;
import java.util.function.IntSupplier;

/**
 * Created at 20-09-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class OrderQueue {

    private final BlockingQueue<Order> orders;
    private final IntSupplier orderIdSupplier;

    public OrderQueue(BlockingQueue<Order> orders, IntSupplier orderIdSupplier) {
        this.orders = orders;
        this.orderIdSupplier = orderIdSupplier;
    }

    void placeOrder(Customer customer, Food food, Waiter waiter) throws InterruptedException {
        orders.put(new Order(orderIdSupplier.getAsInt(), customer, food, waiter));
    }

    Order nextOrder() throws InterruptedException {
        return orders.take();
    }
}
