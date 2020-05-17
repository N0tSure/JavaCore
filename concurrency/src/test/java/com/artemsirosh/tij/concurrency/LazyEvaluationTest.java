package com.artemsirosh.tij.concurrency;

import org.junit.jupiter.api.RepeatedTest;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

/**
 * Created on 18 May, 2020.
 *
 * @author Artemis A. Sirosh
 */
public class LazyEvaluationTest {

    private final Consumer<Runnable> measurer = task -> {
        System.out.println("Start measuring.");
        final long startNanos = System.nanoTime();
        task.run();
        System.out.println("Elapsed time: " + Duration.of(System.nanoTime() - startNanos, ChronoUnit.NANOS));
    };

    @RepeatedTest(5)
    void testEvaluator() {
        measurer.accept(() -> evaluator(evaluate(), evaluate()));
    }

    @RepeatedTest(5)
    void testLazyEvaluator() {
        measurer.accept(() -> lazyEvaluator(LazyEvaluationTest::evaluate, LazyEvaluationTest::evaluate));
    }

    private static void evaluator(final boolean first, final boolean second) {
        System.out.println("Call evaluator...");
        System.out.println("Evaluate... " + (first && second));
    }

    private static void lazyEvaluator(final BooleanSupplier first, final BooleanSupplier second) {
        System.out.println("Call lazy evaluator...");
        System.out.println("Evaluate... " + (first.getAsBoolean() && second.getAsBoolean()));
    }

    private static boolean evaluate() {
        new SleeperTask(TimeUnit.SECONDS, 5).run();
        return Math.random() > 0.5;
    }
}
