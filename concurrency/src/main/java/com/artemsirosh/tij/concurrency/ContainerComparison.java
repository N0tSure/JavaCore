package com.artemsirosh.tij.concurrency;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciithemes.u8.U8_Grids;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;

import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created at 24-09-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class ContainerComparison<C> {

    private final int repetitions;
    private final int cycles;
    private final int containerSize;
    private final int readersNumber;
    private final int writersNumber;
    private final String displayName;
    private final Integer[] data;
    private final IntFunction<C> containerGenerator;
    private final Consumer<ContainerComparison<C>> startTestingConsumer;

    private Results results;
    private CountDownLatch latch;
    private C container;

    private static void prepareTableHeader(AsciiTable table, String displayName) {
        table.addStrongRule();
        table.addRow(null, null, null, null, displayName)
                .setTextAlignment(TextAlignment.CENTER);
        table.addStrongRule();
        table.addRow(
                "Readers", "Writers", "Read time", "Write time", "Read & Write time"
        ).setTextAlignment(TextAlignment.CENTER);
    }

    private static void prepareTableFooter(AsciiTable table) {
        table.addStrongRule();
        table.addRow(null, null, null, null, "Summary").setTextAlignment(TextAlignment.CENTER);
        table.addStrongRule();

        table.addRow(null, "Operation", null, "Average", "Standard Deviation")
                .setTextAlignment(TextAlignment.CENTER);
    }

    private static void prepareStatisticRow(
            AsciiTable table, List<Results> repetitionResult, int repetitions,
            ToDoubleFunction<Results> doubleMapper, String operationName
    ) {
        final double average = repetitionResult.stream().mapToDouble(doubleMapper).average().orElse(0);
        final double standardDeviation = Math.sqrt(
                repetitionResult.stream()
                        .mapToDouble(doubleMapper)
                        .map(i -> Math.pow(i - average, 2.0))
                        .sum() / repetitions
        );

        table.addRow(
                null, operationName, null,
                String.format("%.0f", average), String.format("%.0f", standardDeviation)
        );
        table.addRule();
    }

    ContainerComparison(
            int readersNumber,
            int writersNumber,
            String displayName,
            IntFunction<C> containerGenerator,
            Consumer<ContainerComparison<C>> startTestingConsumer
    ) {
        this(
                10, 1000, 1000,
                readersNumber, writersNumber, displayName, containerGenerator, startTestingConsumer
        );
    }

    ContainerComparison(
            int repetitions,
            int cycles,
            int containerSize,
            int readersNumber,
            int writersNumber,
            String displayName,
            IntFunction<C> containerGenerator,
            Consumer<ContainerComparison<C>> startTestingConsumer
    ) {
        this.repetitions = repetitions;
        this.cycles = cycles;
        this.containerSize = containerSize;
        this.readersNumber = readersNumber;
        this.writersNumber = writersNumber;
        this.displayName = displayName;
        this.data = IntStream.range(0, containerSize).boxed().toArray(Integer[]::new);
        this.containerGenerator = containerGenerator;
        this.startTestingConsumer = startTestingConsumer;
        this.results = new Results(0, 0, 0);
    }

    Integer[] getData() {
        return data;
    }

    C getContainer() {
        return container;
    }

    int getContainerSize() {
        return containerSize;
    }

    int getCycles() {
        return cycles;
    }

    int getReadersNumber() {
        return readersNumber;
    }

    int getWritersNumber() {
        return writersNumber;
    }

    void runTest() {
        final AsciiTable table = new AsciiTable();
        prepareTableHeader(table, displayName);


        List<Results> repetitionResults = IntStream.range(0, repetitions)
                .mapToObj(i -> test(table))
                .collect(Collectors.toList());
        prepareTableFooter(table);
        table.addStrongRule();
        prepareStatisticRow(table, repetitionResults, repetitions, Results::getReadTime, "Read");
        prepareStatisticRow(table, repetitionResults, repetitions, Results::getWriteTime, "Write");

        table.getContext().setGrid(U8_Grids.borderStrongDoubleLight());
        System.out.println(table.render());
    }

    void updateResult(Function<Results, Results> resultModification) {
        synchronized (this) {
            this.results = resultModification.apply(this.results);
        }

        latch.countDown();
    }

    private Results test(AsciiTable table) {
        this.latch = new CountDownLatch(readersNumber + writersNumber);
        this.container = containerGenerator.apply(containerSize);
        this.startTestingConsumer.accept(this);

        final Results repetitionResults;
        try {
            latch.await();
            table.addRule();
            table.addRow(
                    readersNumber, writersNumber, results.getReadTime(), results.getWriteTime(),
                    results.hasReadNWriteTime() ? results.getReadTime() + results.getWriteTime() : "N/A"
            );
        } catch (InterruptedException exc) {
            System.out.println(displayName + ": interrupted.");
        } finally {
            repetitionResults = results;
            results = new Results(0,0,0);
        }

        return repetitionResults;
    }

    static final class Results {

        private final long readResult;
        private final long readTime;
        private final long writeTime;

        Results(long readResult, long readTime, long writeTime) {
            this.readResult = readResult;
            this.readTime = readTime;
            this.writeTime = writeTime;
        }

        boolean hasReadNWriteTime() {
            return readTime != 0 && writeTime != 0;
        }

        long getReadResult() {
            return readResult;
        }

        long getReadTime() {
            return readTime;
        }

        long getWriteTime() {
            return writeTime;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Results.class.getSimpleName() + "[", "]")
                    .add("readResult=" + readResult)
                    .add("readTime=" + readTime)
                    .add("writeTime=" + writeTime)
                    .toString();
        }
    }
}
