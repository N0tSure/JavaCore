package com.artemsirosh.tij.concurrency.restaurant;

import com.artemsirosh.tij.enumerated.menu.Food;

/**
 * Created at 20-09-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
final class Order {

    private final int id;
    private final Customer customer;
    private final Food item;
    private final Waiter waiter;

    Order(int id, Customer customer, Food item, Waiter waiter) {
        this.id = id;
        this.customer = customer;
        this.item = item;
        this.waiter = waiter;
    }

    Food getItem() {
        return item;
    }

    Waiter getWaiter() {
        return waiter;
    }

    Customer getCustomer() {
        return customer;
    }

    @Override
    public String toString() {
        return "Order #" + id + ": " + item + " for " + customer + " served by " + waiter + ".";
    }
}
