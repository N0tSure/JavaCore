package com.artemsirosh.tij.concurrency.race;

import com.artemsirosh.tij.concurrency.FinisherTask;
import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created at 09-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class HorseRace implements Runnable {

    private final int finishLine;
    private final List<Horse> horses;
    private final FinisherTask<Horse> finisherTask;

    private static List<Horse> createHorses(final int horsesNum, final CyclicBarrier barrier) {
        final Random random = new Random(47);
        return IntStream.range(0, horsesNum)
                .mapToObj(id -> new RacingHorse(id, random, barrier))
                .reduce(
                        ImmutableList.<Horse>builder(),
                        ImmutableList.Builder::add,
                        (builder1, builder2) -> builder1.addAll(builder2.build())
                ).build();
    }

    public HorseRace(final int finishLine, final int horsesNum, final FinisherTask<Horse> finisherTask) {
        this.finishLine = finishLine;
        this.finisherTask = finisherTask;
        this.horses = createHorses(horsesNum, new CyclicBarrier(horsesNum, this));
    }

    public void executeHorses(ExecutorService executor) {
        horses.forEach(executor::execute);
    }

    @Override
    public void run() {

        System.out.println(
                IntStream.range(0, finishLine)
                        .mapToObj(i -> "=")
                        .collect(Collectors.joining())
        );

        horses.forEach(horse -> System.out.println(horse.tracks()));
        horses.stream()
                .filter(horse -> horse.getStrides() >= finishLine)
                .findFirst()
                .ifPresent(finisherTask::setResult);

        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException exc) {
            System.out.println("HorseRace: interrupted.");
        }
    }
}
