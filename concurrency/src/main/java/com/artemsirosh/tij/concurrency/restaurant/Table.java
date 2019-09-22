package com.artemsirosh.tij.concurrency.restaurant;

import com.artemsirosh.tij.enumerated.menu.Food;

import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CyclicBarrier;

/**
 * Created at 21-09-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class Table {

    private final Waiter waiter;
    private final List<Dish> ticketDishes;
    private final CyclicBarrier cyclicBarrier;

    Table(Waiter waiter, int size) {
        this.waiter = waiter;
        this.ticketDishes = new CopyOnWriteArrayList<>();
        this.cyclicBarrier = new CyclicBarrier(size, this::prepareOrderTicket);
    }

    void placeOrder(Guest guest, Food food) throws InterruptedException {
        try {
            ticketDishes.add(new Dish(guest, food));
            cyclicBarrier.await();
        } catch (BrokenBarrierException exc) {
            System.out.println(guest + ": a broken barrier.");
        }
    }

    private void prepareOrderTicket() {
        waiter.placeOrder(ticketDishes);
        ticketDishes.clear();
    }
}
