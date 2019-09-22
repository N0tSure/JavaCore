package com.artemsirosh.tij.concurrency.restaurant;

import com.artemsirosh.tij.concurrency.SerialNumberGenerator;
import com.artemsirosh.tij.finisher.Finisher;
import com.artemsirosh.tij.finisher.Finishers;

import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created at 20-09-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class RestaurantDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final ExecutorService executor = Executors.newCachedThreadPool();
        final Random random = new Random(47);
        final SerialNumberGenerator serialNumberGen = new SerialNumberGenerator();
        final OrderQueue orderQueue = new OrderQueue(new LinkedBlockingQueue<>(), serialNumberGen::nextSerialNumber);

        final List<Waiter> waiters = IntStream.range(1, 6)
                .mapToObj(id -> new Waiter(id, orderQueue, new LinkedBlockingQueue<>()))
                .peek(executor::execute)
                .collect(Collectors.toList());

        IntStream.range(1, 3)
                .mapToObj(id -> new Chief(id, orderQueue, random))
                .forEach(executor::execute);

        final Restaurant restaurant = Restaurant.builder()
                .setGuestExecutor(executor)
                .setIdSupplier(serialNumberGen::nextSerialNumber)
                .setRandom(random)
                .setWaiters(waiters)
                .build();

        executor.execute(restaurant);

        final Finisher<?> finisher;
        if (args.length > 0) {
            finisher = Finishers.newSecondsTimeoutFinisher(new Integer(args[0]));
        } else {
            finisher = Finishers.newNetworkFinisher(8080);
        }

        Future<?> future = executor.submit(finisher);
        future.get();
        executor.shutdownNow();
    }
}
