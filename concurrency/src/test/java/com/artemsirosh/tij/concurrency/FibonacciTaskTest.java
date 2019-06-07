package com.artemsirosh.tij.concurrency;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

/**
 * Created at 03-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class FibonacciTaskTest {

    @Test
    @DisplayName("Runs Fibonacci tasks")
    void runFibonacciTask() {
        FibonacciTask task0 = new FibonacciTask(5);
        FibonacciTask task1 = new FibonacciTask(47);
        FibonacciTask task2 = new FibonacciTask(2);

        Stream.of(task0, task1, task2).map(Thread::new).forEach(Thread::start);
        System.out.println("End of main.");
    }
}
