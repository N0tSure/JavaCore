package com.artemsirosh.tij.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * Created at 18-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class Teller implements Runnable, Comparable<Teller> {

    private final int id;
    private final CustomerLine customers;

    private int servedCustomerCounter;
    private boolean serving;

    public Teller(int id, CustomerLine customers) {
        this.id = id;
        this.customers = customers;
        this.servedCustomerCounter = 0;
        this.serving = true;
    }

    @Override
    public int compareTo(Teller that) {
        return Integer.compare(this.servedCustomerCounter, that.servedCustomerCounter);
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                final Customer customer = customers.take();
                TimeUnit.MILLISECONDS.sleep(customer.getServiceTime());
                synchronized (this) {
                    servedCustomerCounter++;
                    while (!serving)
                        wait();
                }
            }
        } catch (InterruptedException exc) {
            // acceptable way to stop task
        }

        System.out.println(this + ": stopped.");
    }

    synchronized void doSomethingElse() {
        servedCustomerCounter = 0;
        serving = false;
    }

    synchronized void serveCustomers() {
        serving = true;
        notifyAll();
    }

    String shortString() {
        return String.format("T%d", id);
    }

    @Override
    public String toString() {
        return String.format("Teller #%d", id);
    }
}
