package ekkel.book.enumerated;

import java.util.Arrays;

/**
 * Created on 02 Jan, 2019.
 *
 * @author Artemis A. Sirosh
 */
public enum Sound {

    ;
//    VOWEL('a', 'e', 'i', 'o', 'u'), CONSONANT(CharSequence... chars), SOMETIMES_VOWEL(CharSequence... chars);

    private final char[] sounds;

    Sound(char... sounds) {
        this.sounds = new char[sounds.length];
        System.arraycopy(sounds, 0, this.sounds, 0, sounds.length);
        Arrays.sort(this.sounds);
    }

    public Sound resolve(char s) {
        return null;
    }
}
