package com.artemsirosh.tij.concurrency;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

/**
 * Created at 09-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class CircularSetTest {

    @Test
    @DisplayName("Should save and find a bunch of numbers")
    void shouldSaveSeveralNumbers() {
        final CircularSet set = new CircularSet(10);

        Assertions.assertTrue(
                IntStream.generate(() -> (int) Math.round(Math.random() * 100))
                        .limit(9)
                        .peek(set::add)
                        .allMatch(set::contains)
        );
    }

    @Test
    @DisplayName("Should reuse memory")
    void shouldReuseMemory() {
        final CircularSet set = new CircularSet(10);

        Assertions.assertTrue(
                IntStream.generate(() -> (int) Math.round(Math.random() * 100))
                        .limit(1000)
                        .peek(set::add)
                        .allMatch(set::contains)
        );
    }
}
