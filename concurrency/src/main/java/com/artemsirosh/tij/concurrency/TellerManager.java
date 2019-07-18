package com.artemsirosh.tij.concurrency;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

/**
 * Created at 18-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class TellerManager implements Runnable {

    private final CustomerLine customers;
    private final PriorityQueue<Teller> servingQueue;
    private final Queue<Teller> notServingTellers;
    private final int adjustmentPeriod;
    private final Supplier<Teller> tellerSupplier;

    public static Builder builder() {
        return new Builder();
    }

    private TellerManager(CustomerLine customers, int adjustmentPeriod, Supplier<Teller> tellerSupplier) {
        this.customers = customers;
        this.adjustmentPeriod = adjustmentPeriod;
        this.tellerSupplier = tellerSupplier;
        this.servingQueue = new PriorityQueue<>();
        this.notServingTellers = new LinkedList<>();
        this.servingQueue.add(this.tellerSupplier.get());
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(adjustmentPeriod);
                manageTellers();
                System.out.print(customers + " {");
                System.out.print(
                        servingQueue.stream()
                                .map(Teller::shortString)
                                .collect(Collectors.joining(" "))
                );
                System.out.println("}");
            }
        } catch (InterruptedException exc) {
            // acceptable way to stop task
        }

        System.out.println(this + ": stopped.");
    }

    private void manageTellers() {
        if (isCustomerQueueLarge()) {
            final Teller teller = Optional.ofNullable(notServingTellers.poll()).orElseGet(tellerSupplier);
            teller.serveCustomers();
            servingQueue.add(teller);
        } else if (isServingTellerEnough()) {
            final Teller teller = servingQueue.remove();
            teller.doSomethingElse();
            notServingTellers.add(teller);
        }
    }

    private boolean isCustomerQueueLarge() {
        return customers.size() / (servingQueue.isEmpty() ? 1 : servingQueue.size()) > 2;
    }

    private boolean isServingTellerEnough() {
        return !servingQueue.isEmpty() && (customers.size() / servingQueue.size() < 2);
    }

    @Override
    public String toString() {
        return "TellerManager";
    }

    public static class Builder {
        private CustomerLine customers;
        private int adjustmentPeriod;
        private Supplier<Teller> tellerSupplier;

        private Builder() {
            // no - op
        }

        public Builder setCustomers(CustomerLine customers) {
            this.customers = customers;
            return this;
        }

        public Builder setAdjustmentPeriod(int adjustmentPeriod) {
            this.adjustmentPeriod = adjustmentPeriod;
            return this;
        }


        public Builder setTellerSupplier(Supplier<Teller> tellerSupplier) {
            this.tellerSupplier = tellerSupplier;
            return this;
        }

        public TellerManager build() {
            return new TellerManager(
                    requireNonNull(customers, "CustomerLine is null."),
                    adjustmentPeriod,
                    requireNonNull(tellerSupplier, "Teller's supplier is null.")
            );
        }
    }
}
