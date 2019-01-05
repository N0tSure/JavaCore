package ekkel.book.enumerated;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.EnumSet;

import static ekkel.book.enumerated.AlarmPoints.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created on 05 Jan, 2019.
 *
 * @author Artemis A. Sirosh
 */
class EnumSetTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(EnumSetTest.class);

    @Test
    void shouldAddReceiveSignals() {
        final EnumSet<AlarmPoints> points = EnumSet.noneOf(AlarmPoints.class);

        points.add(BATHROOM);
        LOGGER.info("Signals: {}", points);

        assertAll(
                "Receive only bathroom",
                () -> assertTrue(points.contains(BATHROOM)),
                () -> assertFalse(points.contains(LOBBY))
        );

        points.addAll(EnumSet.of(STAIR1, STAIR2, KITCHEN));
        LOGGER.info("Signals: {}", points);

        assertAll(
                "Receive signal from bathroom, kitchen, stairs 1 and 2",
                () -> assertTrue(points.containsAll(EnumSet.of(BATHROOM, STAIR1, STAIR2, KITCHEN))),
                () -> assertFalse(points.contains(OFFICE1))
        );

    }

    @Test
    void shouldRemoveSignals() {
        final EnumSet<AlarmPoints> points = EnumSet.allOf(AlarmPoints.class);
        points.removeAll(EnumSet.of(STAIR1, STAIR2, KITCHEN));
        LOGGER.info("Signals: {}", points);

        assertAll(
                "Should not contain kitchen, stair 1 and 2 signals",
                () -> assertFalse(points.contains(STAIR1)),
                () -> assertFalse(points.contains(STAIR2)),
                () -> assertFalse(points.contains(KITCHEN)),
                () -> assertTrue(points.contains(OFFICE1)),
                () -> assertTrue(points.contains(OFFICE4))
        );

        points.removeAll(EnumSet.range(OFFICE1, OFFICE4));
        LOGGER.info("Signals: {}", points);

        assertAll(
                "Should not contain office signal 1, 2, 3 and 4",
                () -> assertFalse(points.contains(OFFICE1)),
                () -> assertFalse(points.contains(OFFICE2)),
                () -> assertFalse(points.contains(OFFICE4))
        );

        final EnumSet<AlarmPoints> complement = EnumSet.complementOf(points);
        LOGGER.info("Signals complement: {}", complement);

        assertAll(
                "Should contain all removed signals",
                () -> assertTrue(complement.contains(STAIR1)),
                () -> assertTrue(complement.contains(STAIR2)),
                () -> assertTrue(complement.contains(KITCHEN)),
                () -> assertTrue(complement.contains(OFFICE1)),
                () -> assertTrue(complement.contains(OFFICE2)),
                () -> assertTrue(complement.contains(OFFICE4)),
                () -> assertFalse(complement.contains(BATHROOM)),
                () -> assertFalse(complement.contains(LOBBY))
        );
    }

    @Test
    void shouldCreateMoreThanSixtyFourEnumInstances() {
        final EnumSet<Big> bigEnumSet = EnumSet.allOf(Big.class);
        LOGGER.info("Big EnumSet: {}", bigEnumSet);

        assertEquals(76, bigEnumSet.size());
    }
}
