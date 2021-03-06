package com.artemsirosh.tij.concurrency;

import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created at 29-09-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class FastSimulation {
    public static void main(String[] args) throws InterruptedException {
        final int elementsNumber = 100_000;
        final int genesNumber = 30;
        final Random random = new Random(42);
        final Grid<AtomicInteger> atomicGrid = Grid.create(
                elementsNumber, genesNumber,
                (e, g) -> new AtomicInteger[e][g], () -> new AtomicInteger(random.nextInt(1000))
        );
        final Grid<Integer> integerGrid = new SynchronizedGrid<>(
                Grid.create(
                        elementsNumber, genesNumber, (e, g) -> new Integer[e][g],
                        () -> random.nextInt(1000)
                )
        );

        simulation("Atomic", () -> new AtomicEvolver(atomicGrid, random));
        simulation("Simple", () -> new SimpleEvolver(integerGrid, random));
        System.out.println("End of simulation.");
    }

    private static void simulation(String evolverName, Supplier<Callable<Long>> evolversSupplier) throws InterruptedException {
        final ExecutorService executor = Executors.newCachedThreadPool();
        final List<Future<Long>> results = Stream.generate(evolversSupplier)
                .limit(50)
                .map(executor::submit)
                .collect(Collectors.toList());

        TimeUnit.SECONDS.sleep(5);
        executor.shutdownNow();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        final double measurement = results.stream()
                .mapToLong(FastSimulation::getMutationsResult)
                .average()
                .orElse(0);

        System.out.printf("%s Evolver average result: %.0f\n", evolverName, measurement);
    }

    private static long getMutationsResult(Future<Long> evolverFuture) {
        try {
            return evolverFuture.get();
        } catch (InterruptedException | ExecutionException exc) {
            // in case of error result is null
            return 0;
        }
    }
}
