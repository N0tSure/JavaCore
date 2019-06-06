package com.artemsirosh.tij.concurrency;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

/**
 * Created at 03-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class LiftOffTest {

    private static void useExecutorService(ExecutorService executor, Stream<? extends Runnable> tasks) {
        tasks.forEach(executor::execute);
        executor.shutdown();
    }

    @Test
    @DisplayName("Task work in main thread")
    void singleThread() {
        LiftOff liftOff = new LiftOff();
        liftOff.run();
    }

    @Test
    @DisplayName("Task is works in separated thread")
    void separatedThread() {
        Thread thread = new Thread(new LiftOff());
        thread.start();
        System.out.println("Main thread end.");
    }

    @Test
    @DisplayName("Several traceable task running in treads")
    void severalTasks() {
        Stream.generate(TraceableTask::new)
                .limit(5)
                .map(Thread::new)
                .forEach(Thread::start);
    }

    @Test
    @DisplayName("Run traceable task using SingleThreadExecutor")
    void useSingleThreadExecutor() {
        final ExecutorService executor = Executors.newSingleThreadExecutor();
        final Stream<TraceableTask> traceableTaskStream = Stream.generate(TraceableTask::new).limit(5);

        useExecutorService(executor, traceableTaskStream);
    }

    @Test
    @DisplayName("Run traceable task in a five threads")
    void useFixedThreadPool() {
        final ExecutorService executor = Executors.newFixedThreadPool(5);
        final Stream<TraceableTask> traceableTaskStream = Stream.generate(TraceableTask::new).limit(5);

        useExecutorService(executor, traceableTaskStream);

    }

    @Test
    @DisplayName("Run traceable tasks in a several threads")
    void useCachedTreadPool() {
        final ExecutorService executor = Executors.newCachedThreadPool();
        final Stream<TraceableTask> traceableTaskStream = Stream.generate(TraceableTask::new).limit(5);

        useExecutorService(executor, traceableTaskStream);
    }
}
