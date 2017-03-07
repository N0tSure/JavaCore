package ekkel.book.generics.bank;

import ekkel.book.util.Generator;

/**
 * Created by cresh on 28.08.16.
 */
class Customer {
    private static long counter = 1;
    private final long id = counter++;
    private Customer() {}

    @Override
    public String toString() {
        return "Customer: " + id;
    }

    public static Generator<Customer> generator() {
        return new Generator<Customer>() {
            @Override
            public Customer next() {
                return new Customer();
            }
        };
    }
}
