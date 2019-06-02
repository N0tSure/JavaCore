package com.artemsirosh.tij.enumerated;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

/**
 * Created on 02 Jan, 2019.
 *
 * @author Artemis A. Sirosh
 */
class SoundsTest {

    private static final Character[] CONSONANTS = {
            'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k',
            'l', 'm', 'n', 'p', 'q', 'r', 's', 't',
            'v', 'x', 'z'
    };

    private static final Character[] VOWELS = { 'a', 'e', 'i', 'o', 'u' };
    private static final Character[] SOMETIME_VOWELS = { 'y', 'w' };

    @TestFactory
    Stream<DynamicTest> shouldDetectAllVowels() {
        return shouldDetectSound(VOWELS, Sound.VOWEL);
    }

    @TestFactory
    Stream<DynamicTest> shouldDetectAllConsonants() {
        return shouldDetectSound(CONSONANTS, Sound.CONSONANT);
    }

    @TestFactory
    Stream<DynamicTest> shouldDetectSemiVowels() {
        return shouldDetectSound(SOMETIME_VOWELS, Sound.SOMETIMES_VOWEL);
    }

    @Test
    void shouldRejectUnsupportedCharacter() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Sound.resolve('к'));
        assertEquals(exception.getMessage(), "Unsupported character: к");
    }

    private Stream<DynamicTest> shouldDetectSound(final Character[] characters, final Sound expectedSound) {
        return Arrays.stream(characters).map(character -> dynamicTest(
                "test for " + character,
                () -> assertEquals(expectedSound, Sound.resolve(character))
        ));
    }
}
