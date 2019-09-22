package com.artemsirosh.tij.concurrency.restaurant;

import com.artemsirosh.tij.enumerated.menu.Food;

/**
 * Created at 20-09-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
final class Order {

    private final int id;
    private final Guest guest;
    private final Food item;
    private final Waiter waiter;

    Order(int id, Guest guest, Food item, Waiter waiter) {
        this.id = id;
        this.guest = guest;
        this.item = item;
        this.waiter = waiter;
    }

    Food getItem() {
        return item;
    }

    Waiter getWaiter() {
        return waiter;
    }

    Guest getGuest() {
        return guest;
    }

    @Override
    public String toString() {
        return "Order #" + id + ": " + item + " for " + guest + " served by " + waiter + ".";
    }
}
