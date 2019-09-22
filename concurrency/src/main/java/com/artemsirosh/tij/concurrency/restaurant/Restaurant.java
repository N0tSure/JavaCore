package com.artemsirosh.tij.concurrency.restaurant;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;

import static java.util.Objects.requireNonNull;

/**
 * Created at 20-09-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class Restaurant implements Runnable {

    private final List<Waiter> waiters;
    private final IntSupplier guestIdSupplier;
    private final Consumer<Guest> guestExecutor;
    private final Random random;

    static RestaurantBuilder builder() {
        return new RestaurantBuilder();
    }

    private static void createGuests(
            Waiter waiter,
            int size,
            IntSupplier guestIdSupplier,
            Consumer<Guest> guestExecutor
    ) {
        final Table table = new Table(waiter, size);
        IntStream.generate(guestIdSupplier)
                .limit(size)
                .mapToObj(id -> new Guest(id, table, new SynchronousQueue<>()))
                .forEach(guestExecutor);
    }

    private Restaurant(
            List<Waiter> waiters,
            IntSupplier guestIdSupplier,
            Consumer<Guest> guestExecutor,
            Random random
    ) {
        this.waiters = waiters;
        this.guestIdSupplier = guestIdSupplier;
        this.guestExecutor = guestExecutor;
        this.random = random;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                final Waiter waiter = waiters.get(random.nextInt(waiters.size()));
                createGuests(waiter, 1 + random.nextInt(5), guestIdSupplier, guestExecutor);
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException exc) {
            // way to stop task
        }

        System.out.println("Restaurant: closing.");
    }

    static class RestaurantBuilder {
        private List<Waiter> waiters;
        private IntSupplier guestIdSupplier;
        private Consumer<Guest> guestExecutor;
        private Random random;

        RestaurantBuilder setWaiters(List<Waiter> waiters) {
            this.waiters = waiters;
            return this;
        }

        RestaurantBuilder setRandom(Random random) {
            this.random = random;
            return this;
        }

        RestaurantBuilder setGuestExecutor(ExecutorService executor) {
            this.guestExecutor = executor::execute;
            return this;
        }

        RestaurantBuilder setIdSupplier(IntSupplier intSupplier) {
            this.guestIdSupplier = intSupplier;
            return this;
        }

        Restaurant build() {
            return new Restaurant(
                    requireNonNull(waiters),
                    requireNonNull(guestIdSupplier),
                    requireNonNull(guestExecutor),
                    requireNonNull(random)
            );
        }
    }
}
