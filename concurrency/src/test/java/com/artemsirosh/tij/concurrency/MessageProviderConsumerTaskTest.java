package com.artemsirosh.tij.concurrency;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created at 29-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
@ExtendWith(MockitoExtension.class)
class MessageProviderConsumerTaskTest {

    private static final List<String> STRINGS = ImmutableList.of("foo", "bar", "baz", "qux", "quux", "garple");

    @Test
    @DisplayName("Should add to stack a few messages")
    void shouldProvideAFewMessages(@Mock MessageStack<String> stack) {
        MessageProviderTask<String> provider = new MessageProviderTask<>(STRINGS, stack);
        provider.run();

        STRINGS.forEach(s -> Mockito.verify(stack).push(s));
    }

    @Test
    void shouldCollectAllMessages(@Mock MessageStack<String> stack) {
        Mockito.when(stack.pop()).thenReturn(STRINGS.get(0), STRINGS.get(1), STRINGS.get(2), null);
        final MessageConsumerTask<String> consumer = new MessageConsumerTask<>(stack);
        final Map<String, Integer> results = consumer.call();

        final Set<String> expected = ImmutableSet.of(STRINGS.get(0), STRINGS.get(1), STRINGS.get(2));
        results.forEach((m, c) -> Assertions.assertAll(
                () -> Assertions.assertTrue(expected.contains(m)),
                () -> Assertions.assertEquals(1, (int) c)
        ));

    }
}
