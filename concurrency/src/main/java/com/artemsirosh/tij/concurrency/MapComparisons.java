package com.artemsirosh.tij.concurrency;

import com.artemsirosh.tij.concurrency.ContainerComparison.Results;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

/**
 * Created at 29-09-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
final class MapComparisons {

    static ContainerComparison<Map<Integer, Integer>> createMapComparison(
            int readersNumber, int writersNumber, String displayName,
            IntFunction<Map<Integer, Integer>> containerGenerator, Consumer<Runnable> taskExecutor
    ) {
        return new ContainerComparison<>(
                readersNumber, writersNumber, displayName, containerGenerator,
                comparison -> startTesting(comparison, taskExecutor)
        );
    }

    private static Results combineReadingResults(
            TestTask.TestProcessResults processResults, Results accumulatedResults
    ) {
        return new Results(
                processResults.getResult() + accumulatedResults.getReadResult(),
                processResults.getDuration() + accumulatedResults.getReadTime(),
                accumulatedResults.getWriteTime()
        );
    }

    private static Results combineWritingResults(
            TestTask.TestProcessResults processResults, Results accumulatedResults
    ) {
        return new Results(
                accumulatedResults.getReadResult(),
                accumulatedResults.getReadTime(),
                processResults.getDuration() + accumulatedResults.getWriteTime()
        );
    }

    private static Runnable createReader(ContainerComparison<Map<Integer, Integer>> comparison) {
        return new TestTask<>(comparison, MapComparisons::processReading, MapComparisons::combineReadingResults);
    }

    private static Runnable createWriter(ContainerComparison<Map<Integer, Integer>> comparison) {
        return new TestTask<>(comparison, MapComparisons::processWriting, MapComparisons::combineWritingResults);
    }

    private static long processReading(ContainerComparison<Map<Integer, Integer>> comparison) {
        long result = 0;
        for (int i = 0; i < comparison.getCycles(); i++) {
            for (int index = 0; index < comparison.getContainerSize(); index++) {
                result += comparison.getContainer().get(index);
            }
        }

        return result;
    }

    private static long processWriting(ContainerComparison<Map<Integer, Integer>> comparison) {
        for (int i = 0; i < comparison.getCycles(); i++) {
            for (int index = 0; index < comparison.getContainerSize(); index++) {
                comparison.getContainer().put(index, comparison.getData()[index]);
            }
        }

        return 0;
    }

    private static void startTesting(
            ContainerComparison<Map<Integer, Integer>> comparison, Consumer<Runnable> consumer
    ) {
        IntStream.range(0, comparison.getReadersNumber()).mapToObj(i -> createReader(comparison)).forEach(consumer);
        IntStream.range(0, comparison.getWritersNumber()).mapToObj(i -> createWriter(comparison)).forEach(consumer);
    }

    private MapComparisons() {
        // instantiation not supported
    }
}
