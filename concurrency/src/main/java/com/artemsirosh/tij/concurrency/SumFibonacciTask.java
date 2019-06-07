package com.artemsirosh.tij.concurrency;

import com.artemsirosh.tij.generics.Fibonacci;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.LongStream;

/**
 * Created at 07-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class SumFibonacciTask extends Fibonacci {

    private final ExecutorService executor;

    private static long fibonacciByNumber(long number) {
        int result;
        if (number == 1) {
            result = 0;
        } else if (number == 2) {
            result = 1;
        } else {
            int tmp, previous = 0;
            int current = 1;
            for (int i = 0; i < number - 2; i++) {
                tmp = current;
                current += previous;
                previous = tmp;
            }

            result = current;
        }

        return result;
    }

    SumFibonacciTask(ExecutorService executor) {
        this.executor = executor;
    }

    Future<Long> runTask(int limit) {
        return executor.submit(
                () -> LongStream.rangeClosed(1, limit)
                        .map(SumFibonacciTask::fibonacciByNumber)
                        .sum()
        );
    }
}
