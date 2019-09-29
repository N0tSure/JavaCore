package com.artemsirosh.tij.concurrency;

import com.artemsirosh.tij.concurrency.ContainerComparison.Results;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created at 24-09-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class TestTask<C, T extends ContainerComparison<C>> implements Runnable {

    private final T tester;
    private final Function<T, Long> testProcessor;
    private final BiFunction<TestProcessResults, Results, Results> resultsModification;

    TestTask(
            T tester,
            Function<T, Long> testProcessor,
            BiFunction<TestProcessResults, Results, Results> resultsModification
    ) {
        this.tester = tester;
        this.testProcessor = testProcessor;
        this.resultsModification = resultsModification;
    }

    @Override
    public void run() {
        final long startNanos = System.nanoTime();
        final long result = testProcessor.apply(tester);
        final long duration = System.nanoTime() - startNanos;
        tester.updateResult(results -> resultsModification.apply(new TestProcessResults(result, duration), results));
    }

    static final class TestProcessResults {
        private final long result;
        private final long duration;

        TestProcessResults(long result, long duration) {
            this.result = result;
            this.duration = duration;
        }

        long getResult() {
            return result;
        }

        long getDuration() {
            return duration;
        }
    }
}
