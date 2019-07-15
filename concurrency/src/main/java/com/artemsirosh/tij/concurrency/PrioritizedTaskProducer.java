package com.artemsirosh.tij.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created at 15-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class PrioritizedTaskProducer implements Callable<List<PrioritizedTask>> {

    private final Queue<PrioritizedTask> queue;
    private final FinisherTask<?> finisher;

    private static PrioritizedTask createPrioritizedTask(int id, IntSupplier prioritySupplier, long timeOut) {
        try {
            TimeUnit.MILLISECONDS.sleep(timeOut);
            return new PrioritizedTask(id, prioritySupplier.getAsInt());
        } catch (InterruptedException exc) {
            throw new RuntimeException("TaskProducer not end work.");
        }
    }

    private static List<PrioritizedTask> createPrioritizedTasks(
            IntFunction<PrioritizedTask> mapper,
            Consumer<PrioritizedTask> intermediateConsumer,
            int startId, int endExclusive
    ) {
        return IntStream.range(startId, endExclusive)
                .mapToObj(mapper)
                .peek(intermediateConsumer)
                .collect(Collectors.toList());
    }

    public PrioritizedTaskProducer(Queue<PrioritizedTask> queue, FinisherTask<?> finisherTask) {
        this.queue = queue;
        this.finisher = finisherTask;
    }

    @Override
    public List<PrioritizedTask> call() {
        final List<PrioritizedTask> result = new ArrayList<>(40);
        final Random random = new Random();

        // random priorities, without delay
        result.addAll(createPrioritizedTasks(
                        i -> createPrioritizedTask(i, () -> random.nextInt(10), 0),
                        queue::add, 0, 20
        ));

        // high priorities, with delay
        result.addAll(createPrioritizedTasks(
                i -> createPrioritizedTask(i, () -> 10, 250),
                queue::add, 20, 30
        ));

        // from low to high priorities, no delay
        result.addAll(createPrioritizedTasks(
                i -> createPrioritizedTask(i, () -> i - 30, 0),
                queue::add, 30, 40
        ));

        queue.add(new EndSentinel(finisher));

        return result;
    }

    private static class EndSentinel extends PrioritizedTask {

        private final FinisherTask<?> finisher;

        private EndSentinel(FinisherTask<?> finisher) {
            super(40, Integer.MAX_VALUE);
            this.finisher = finisher;
        }

        @Override
        public void run() {
            finisher.setResult(null);
        }
    }
 }
