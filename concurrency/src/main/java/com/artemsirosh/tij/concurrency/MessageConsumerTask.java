package com.artemsirosh.tij.concurrency;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created at 29-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class MessageConsumerTask<T> implements Callable<Map<T, Integer>> {

    private final MessageStack<T> stack;
    private final Map<T, Integer> consumedMessages;

    MessageConsumerTask(MessageStack<T> stack) {
        this.stack = stack;
        consumedMessages = new HashMap<>();
    }

    @Override
    public Map<T, Integer> call() {
        try {
            for (T message = stack.pop(); message != null; message = stack.pop()) {
                consumedMessages.compute(message, (m, c) -> (c != null) ? c + 1 : 1);

                System.out.println(this + ": consumed message: '" + message + "'");
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException exc) {
            System.out.println(this + ": interrupted.");
        }

        System.out.println(this + ": stopping.");
        return consumedMessages;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
