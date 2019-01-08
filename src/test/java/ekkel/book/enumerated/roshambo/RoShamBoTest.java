package ekkel.book.enumerated.roshambo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static ekkel.book.enumerated.roshambo.Outcome.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created on 08 Jan, 2019.
 *
 * @author Artemis A. Sirosh
 */
class RoShamBoTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoShamBoTest.class);

    @Test
    @DisplayName("Should dispatching using constant's method with ternary operator")
    void anotherConstantMethodDispatching() {
        play(RoShamBo4.PAPER, RoShamBo4.ROCK, RoShamBo4.SCISSORS);
    }

    @Test
    @DisplayName("Should dispatching using constant's methods")
    void constantMethodDispatching() {
        play(RoShamBo3.PAPER, RoShamBo3.ROCK, RoShamBo3.SCISSORS);
    }

    @Test
    @DisplayName("Enum dispatching test")
    void enumDispatching() {
        play(RoShamBo2.PAPER, RoShamBo2.ROCK, RoShamBo2.SCISSORS);
    }

    @Test
    @DisplayName("Test for double dispatching")
    void doubleDispatching() {
        play(new Paper(), new Rock(), new Scissors());
    }

    private static <T extends Competitor<T>> void play(final T paper, final T rock, final T scissors) {

        assertOutcome(paper, scissors, LOSE);
        assertOutcome(paper, paper, DRAW);
        assertOutcome(paper, rock, WIN);

        assertOutcome(rock, paper, LOSE);
        assertOutcome(rock, rock, DRAW);
        assertOutcome(rock, scissors, WIN);

        assertOutcome(scissors, paper, WIN);
        assertOutcome(scissors, rock, LOSE);
        assertOutcome(scissors, scissors, DRAW);

    }

    private static <T extends Competitor<T>> void assertOutcome(final T first, final T second, final Outcome expected) {
        final Outcome actual = first.compete(second);
        LOGGER.info("{} vs {}: {}", first, second, actual);
        assertEquals(expected, actual, () -> first + " should " + expected);
    }
}
