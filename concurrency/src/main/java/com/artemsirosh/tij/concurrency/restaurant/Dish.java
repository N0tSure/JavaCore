package com.artemsirosh.tij.concurrency.restaurant;

import com.artemsirosh.tij.enumerated.menu.Food;

import java.util.StringJoiner;

/**
 * Created at 21-09-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
final class Dish {

    private final Guest guest;
    private final Food item;

    Dish(Guest guest, Food item) {
        this.guest = guest;
        this.item = item;
    }

    Guest getGuest() {
        return guest;
    }

    Food getItem() {
        return item;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Dish.class.getSimpleName() + "[", "]")
                .add("guest=" + guest)
                .add("item=" + item)
                .toString();
    }
}
