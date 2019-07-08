package com.artemsirosh.tij.concurrency.garden;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.IntFunction;
import java.util.stream.Collector;
import java.util.stream.IntStream;

/**
 * Created at 11-06-2019
 *
 * Has {@link Entrance}s and {@link Count}. Can accumulate data of people
 * visiting in garden.
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class OrnamentalGarden implements Callable<Integer> {

    private final Count count;
    private final List<Entrance> entrances;
    private final CountDownLatch latch;

    private volatile boolean canceled;

    private static List<Entrance> generateEntrances(final IntFunction<Entrance> entranceMapper, final int num) {
        return IntStream.range(0, num)
                .mapToObj(entranceMapper)
                .collect(Collector.of(
                        ImmutableList::<Entrance>builder,
                        ImmutableList.Builder::add,
                        (left, right) -> left.addAll(right.build()),
                        ImmutableList.Builder::build
                ));
    }

    public OrnamentalGarden(final int entrancesNum) {
        this.count = new Count();
        this.entrances = generateEntrances(id -> this.new EntranceImpl(id), entrancesNum);
        this.latch = new CountDownLatch(entrancesNum);
        this.canceled = false;
    }

    @Override
    public Integer call() throws Exception {

        latch.await();
        System.out.println("All entrance is closed");

        return entrances.stream()
                .mapToInt(Entrance::getValue)
                .sum();
    }

    public void cancel() {
        this.canceled = true;
    }

    public int getTotalCount() {
        return count.value();
    }

    public void startEntrances(ExecutorService executorService) {
        entrances.forEach(executorService::execute);
    }

    private class EntranceImpl implements Entrance {

        private final int id;

        private int number;

        EntranceImpl(int id) {
            this.id = id;
            this.number = 0;
        }

        @Override
        public synchronized int getValue() {
            return number;
        }

        @Override
        public void run() {
            try {
                while (!canceled) {
                    synchronized (this) {
                        ++number;
                    }

                    System.out.println(this + ", total: " + count.increment() + ", current: " + getValue());
                    TimeUnit.MILLISECONDS.sleep(100);
                }
            } catch (InterruptedException exc) {
                System.out.println(this+ ": sleep interrupted.");
            }

            latch.countDown();
            System.out.println(this + " stopping.");
        }

        @Override
        public String toString() {
            return "Entrance #" + id;
        }
    }
}
