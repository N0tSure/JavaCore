package com.artemsirosh.tij.concurrency;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.concurrent.*;

/**
 * Created at 26-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class RestaurantTest {

    @ParameterizedTest
    @ValueSource(ints = {5, 10, 20, 50})
    @DisplayName("Demonstration of producers and consumers")
    void producersAndConsumersDemo(int foodLimit) throws InterruptedException, ExecutionException, TimeoutException {
        final ExecutorService executorService = Executors.newCachedThreadPool();
        final Restaurant restaurant = new Restaurant(foodLimit);

        final Future<?> future = executorService.submit(restaurant);
        executorService.execute(restaurant.getChief());
        executorService.execute(restaurant.getWaiterPerson());

        try {
            future.get(10, TimeUnit.SECONDS);
        } finally {
            executorService.shutdownNow();
        }

        Assertions.assertTrue(executorService.awaitTermination(500, TimeUnit.MILLISECONDS));

    }
}
