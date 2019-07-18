package com.artemsirosh.tij.concurrency;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static java.util.Objects.requireNonNull;

/**
 * Created at 18-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class CustomerGenerator implements Runnable {

    private final CustomerLine customers;
    private final Random random;
    private final int spawnRestInterval;
    private final int maxServiceTime;

    public static Builder builder() {
        return new Builder();
    }

    private CustomerGenerator(CustomerLine customers, Random random, int spawnRestInterval, int maxServiceTime) {
        this.customers = customers;
        this.random = random;
        this.spawnRestInterval = spawnRestInterval;
        this.maxServiceTime = maxServiceTime;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(random.nextInt(spawnRestInterval));
                customers.put(new Customer(random.nextInt(maxServiceTime)));
            }
        } catch (InterruptedException exc) {
            // acceptable way to exit
        }

        System.out.println("CustomerGenerator: stopped.");
    }

    public static class Builder {
        private CustomerLine customers;
        private Random random;
        private int spawnRestInterval;
        private int maxServiceTime;

        private Builder() {
            // no - op
        }

        public Builder setCustomers(CustomerLine customers) {
            this.customers = customers;
            return this;
        }

        public Builder setRandom(Random random) {
            this.random = random;
            return this;
        }

        public Builder setSpawnRestInterval(int spawnRestInterval) {
            this.spawnRestInterval = spawnRestInterval;
            return this;
        }

        public Builder setMaxServiceTime(int maxServiceTime) {
            this.maxServiceTime = maxServiceTime;
            return this;
        }

        public CustomerGenerator build() {
            return new CustomerGenerator(
                    requireNonNull(customers, "CustomerLine is null."),
                    requireNonNull(random, "ThreadLocalRandom is null."),
                    spawnRestInterval,
                    maxServiceTime
            );
        }
    }
}
