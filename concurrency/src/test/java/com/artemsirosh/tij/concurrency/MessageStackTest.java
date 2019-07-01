package com.artemsirosh.tij.concurrency;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * Created at 28-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class MessageStackTest {

    private static final List<String> METASYNTACTIC_VALUES = ImmutableList.of(
            "foo", "bar", "baz", "qux", "quux", "corge", "grault",
            "garply", "waldo", "fred", "plugh", "xyzzy", "thud"
    );

    @Test
    @DisplayName("Should create a instance of stack")
    void shouldCreateAMessageStackInstance() {
        Assertions.assertDoesNotThrow(() -> new MessageStack<String>(String.class, 10) {});
    }

    @Test
    @DisplayName("Should save a few elements in FILO order")
    void shouldStoreAnElement() {
        final List<String> elements = METASYNTACTIC_VALUES.subList(0, 3);
        final MessageStack<String> stack = new MessageStack<String>(String.class, 10) {};

        elements.forEach(stack::push);
        IntStream.iterate(2, i -> i - 1)
                .limit(3)
                .forEach(i -> Assertions.assertEquals(elements.get(i), stack.pop()));
    }

    @Test
    void producerConsumerDemo() throws ExecutionException, InterruptedException {
        final ExecutorService executor = Executors.newCachedThreadPool();
        final MessageStack<String> stack = new MessageStack<String>(String.class, 5) {};

        executor.execute(new MessageProviderTask<>(METASYNTACTIC_VALUES, stack));
        Future<Map<String, Integer>> future = executor.submit(new MessageConsumerTask<>(stack));

        TimeUnit.SECONDS.sleep(5);
        executor.shutdownNow();

        Map<String, Integer> results = future.get();
        Assertions.assertEquals(ImmutableSet.copyOf(METASYNTACTIC_VALUES), ImmutableSet.copyOf(results.keySet()));
        Assertions.assertTrue(results.values().stream().allMatch(i -> i == 1));
    }
}
