package com.artemsirosh.tij.concurrency.restaurant;

import com.artemsirosh.tij.enumerated.menu.Food;

import java.util.StringJoiner;

/**
 * Created at 20-09-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
final class Plate {

    private final Order order;
    private final Food food;

    Plate(Order order, Food food) {
        this.order = order;
        this.food = food;
    }

    Customer getCustomer() {
        return order.getCustomer();
    }

    Food getFood() {
        return food;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Plate.class.getSimpleName() + "[", "]")
                .add("order=" + order)
                .add("food=" + food)
                .toString();
    }
}
