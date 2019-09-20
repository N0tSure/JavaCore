package com.artemsirosh.tij.concurrency.restaurant;

import com.artemsirosh.tij.enumerated.menu.Food;

import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntSupplier;

import static java.util.Objects.requireNonNull;

/**
 * Created at 20-09-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class Restaurant implements Runnable {

    private final List<Waiter> waiters;
    private final Function<Waiter, Customer> customerGenerator;
    private final Consumer<Customer> customerExecutor;
    private final Random random;

    static RestaurantBuilder builder() {
        return new RestaurantBuilder();
    }

    private Restaurant(
            List<Waiter> waiters,
            Function<Waiter, Customer> customerGenerator, Consumer<Customer> customerExecutor,
            Random random
    ) {
        this.waiters = waiters;
        this.customerGenerator = customerGenerator;
        this.customerExecutor = customerExecutor;
        this.random = random;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                final Waiter waiter = waiters.get(random.nextInt(waiters.size()));
                customerExecutor.accept(customerGenerator.apply(waiter));
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException exc) {
            // way to stop task
        }

        System.out.println("Restaurant: closing.");
    }

    static class RestaurantBuilder {
        private List<Waiter> waiters;
        private Function<Waiter, Customer> customerGenerator;
        private Consumer<Customer> customerExecutor;
        private Random random;

        RestaurantBuilder setWaiters(List<Waiter> waiters) {
            this.waiters = waiters;
            return this;
        }

        RestaurantBuilder setRandom(Random random) {
            this.random = random;
            return this;
        }

        RestaurantBuilder setCustomerExecutor(ExecutorService executor) {
            this.customerExecutor = executor::execute;
            return this;
        }

        RestaurantBuilder setCustomerGenerator(IntSupplier intSupplier) {
            this.customerGenerator =
                    waiter -> new Customer(intSupplier.getAsInt(), waiter, new SynchronousQueue<>());
            return this;
        }

        Restaurant build() {
            return new Restaurant(
                    requireNonNull(waiters),
                    requireNonNull(customerGenerator),
                    requireNonNull(customerExecutor),
                    requireNonNull(random)
            );
        }
    }
}
