package com.artemsirosh.tij.generics.store;

import com.artemsirosh.tij.util.Generator;

import java.util.Random;

/**
 * Created by cresh on 29.08.16.
 */
class Product {
    private final int id;
    private String description;
    private double price;

    public Product(int id, String description, double price) {
        this.id = id;
        this.description = description;
        this.price = price;
        System.out.println(this);
    }

    @Override
    public String toString() {
        return this.id + ": " + this.description + ", price: $" +this.price;
    }

    public void setPrice(double change) {
        this.price += change;
    }

    public static Generator<Product> generator() {
        return new Generator<Product>() {
            Random random = new Random(47);
            @Override
            public Product next() {
                return new Product(random.nextInt(1000),"Test",Math.round(random.nextDouble() * 1000.0) + 0.99);
            }
        };
    }
}
