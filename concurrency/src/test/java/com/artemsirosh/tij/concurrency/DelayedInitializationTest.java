package com.artemsirosh.tij.concurrency;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.LongConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created on 16 May, 2020.
 *
 * @author Artemis A. Sirosh
 */
public class DelayedInitializationTest {

    @Test
    void shouldCheckSimpleHolder() {
        assertNumberOfHeaviesReturnedByHolder(
                new SimpleHolder(),
                actual -> Assertions.assertNotEquals(1, actual, "Expected broken initialization.")
        );
    }

    @Test
    void shouldCheckSynchronizedHolder() {
        assertNumberOfHeaviesReturnedByHolder(
                new SynchronizedHeavyHolder(),
                actual -> Assertions.assertEquals(1, actual, "Expected proper initialization.")
        );
    }

    @Test
    void shouldCheckConcurrentHolder() {
        assertNumberOfHeaviesReturnedByHolder(
                new ConcurrentHolder(),
                actual -> Assertions.assertEquals(1, actual, "Expected proper initialization.")
        );
    }

    private static void assertNumberOfHeaviesReturnedByHolder(final HeavyHolder holder, final LongConsumer assertion) {
        final ExecutorService executor = Executors.newCachedThreadPool();

        final List<CompletableFuture<Heavy>> futures = IntStream.range(0, 10)
                .mapToObj(i -> new HeavyTask(holder))
                .map(task -> CompletableFuture.supplyAsync(task, executor))
                .collect(Collectors.toList());

        final long actualNumberOfHeavies = futures.stream()
                .map(CompletableFuture::join)
                .distinct()
                .count();

        assertion.accept(actualNumberOfHeavies);
        executor.shutdownNow();
    }

    private static class HeavyTask implements Supplier<Heavy> {

        private final HeavyHolder holder;

        public HeavyTask(HeavyHolder holder) {
            this.holder = holder;
        }

        @Override
        public Heavy get() {

            return holder.getHeavy();
        }
    }
}
