package com.artemsirosh.tij.concurrency;

import com.artemsirosh.tij.concurrency.ContainerComparison.Results;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

/**
 * Created at 24-09-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class ListComparison {

    static ContainerComparison<List<Integer>> createContainerComparison(
            int readersNumber, int writersNumber, String displayName,
            IntFunction<List<Integer>> containerGenerator, Consumer<TestTask<?, ?>> taskExecutor
    ) {
        return new ContainerComparison<>(
                readersNumber, writersNumber, displayName,
                containerGenerator, tester -> startTest(tester, taskExecutor)
        );
    }

    static ContainerComparison<List<Integer>> createContainerComparison(
            int repetitions, int cycles, int containerSize,
            int readersNumber, int writersNumber, String displayName,
            IntFunction<List<Integer>> containerGenerator, Consumer<TestTask<?, ?>> taskExecutor
    ) {
        return new ContainerComparison<>(
                repetitions, cycles, containerSize, readersNumber, writersNumber, displayName,
                containerGenerator, tester -> startTest(tester, taskExecutor)
        );
    }

    private static Results combineReadResults(
            TestTask.TestProcessResults testProcessResults, Results accumulatedResults
    ) {
        return new Results(
                accumulatedResults.getReadResult() + testProcessResults.getResult(),
                accumulatedResults.getReadTime() + testProcessResults.getDuration(),
                accumulatedResults.getWriteTime()
        );
    }

    private static Results combineWriteResults(
            TestTask.TestProcessResults testProcessResults, Results accumulatedResults
    ) {
        return new Results(
                accumulatedResults.getReadResult(),
                accumulatedResults.getReadTime(),
                accumulatedResults.getWriteTime() + testProcessResults.getDuration()
        );
    }

    private static TestTask<List<Integer>, ContainerComparison<List<Integer>>> newReader(
            ContainerComparison<List<Integer>> comparison
    ) {
        return new TestTask<>(comparison, ListComparison::readTest, ListComparison::combineReadResults);
    }

    private static TestTask<List<Integer>, ContainerComparison<List<Integer>>> newWriter(
            ContainerComparison<List<Integer>> comparison
    ) {
        return new TestTask<>(comparison, ListComparison::writeTest, ListComparison::combineWriteResults);
    }

    private static long readTest(ContainerComparison<List<Integer>> tester) {
        long result = 0;
        for (int i = 0; i < tester.getCycles(); i++) {
            for (int j = 0; j < tester.getContainerSize(); j++) {
                result += tester.getContainer().get(j);
            }
        }

        return result;
    }

    private static long writeTest(ContainerComparison<List<Integer>> tester) {
        for (int i = 0; i < tester.getCycles(); i++) {
            for (int j = 0; j < tester.getContainerSize(); j++) {
                tester.getContainer().set(j, tester.getData()[j]);
            }
        }

        return 0;
    }

    private static void startTest(ContainerComparison<List<Integer>> comparison, Consumer<TestTask<?, ?>> consumer) {
        IntStream.range(0, comparison.getReadersNumber()).mapToObj(i -> newReader(comparison)).forEach(consumer);
        IntStream.range(0, comparison.getWritersNumber()).mapToObj(i -> newWriter(comparison)).forEach(consumer);
    }

    private ListComparison() {
        // not supported
    }
}
