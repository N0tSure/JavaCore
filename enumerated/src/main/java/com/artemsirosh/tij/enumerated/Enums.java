package com.artemsirosh.tij.enumerated;

import java.util.Random;

/**
 * Created on 26 Nov, 2018.
 *
 * @author Artemis A. Sirosh
 */
public class Enums {
    private static Random random = new Random();

    public static <T extends Enum<T>> T random(Class<T> enumClazz) {
        return random(enumClazz.getEnumConstants());
    }

    public static <T> T random(T[] constants) {
        return constants[random.nextInt(constants.length)];
    }
}
