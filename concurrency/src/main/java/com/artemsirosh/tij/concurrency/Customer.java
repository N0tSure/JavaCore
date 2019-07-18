package com.artemsirosh.tij.concurrency;

/**
 * Created at 18-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class Customer {

    private final int serviceTime;

    Customer(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    int getServiceTime() {
        return serviceTime;
    }

    @Override
    public String toString() {
        return "[" + serviceTime + "]";
    }
}
