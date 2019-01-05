package ekkel.book.enumerated;

import java.util.Arrays;

/**
 * Created on 02 Jan, 2019.
 *
 * @author Artemis A. Sirosh
 */
public enum Sound {

    VOWEL('a', 'e', 'i', 'o', 'u' ),
    CONSONANT('b', 'c', 'd', 'f',
            'g', 'h', 'j', 'k', 'l', 'm',
            'n', 'p', 'q', 'r', 's', 't',
            'v', 'x', 'z'
    ),
    SOMETIMES_VOWEL('y', 'w' );

    private final char[] sounds;

    Sound(char... sounds) {
        this.sounds = new char[sounds.length];
        System.arraycopy(sounds, 0, this.sounds, 0, sounds.length);
        Arrays.sort(this.sounds);
    }

    public static Sound resolve(final char ch) {
        return Arrays.stream(Sound.values())
                .filter(sound -> Arrays.binarySearch(sound.sounds, ch) >= 0)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unsupported character: " + ch));
    }
}
