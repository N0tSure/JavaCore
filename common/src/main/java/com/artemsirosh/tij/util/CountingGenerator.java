package com.artemsirosh.tij.util;


/**
 * Created by cresh on 06.02.17.
 */
public class CountingGenerator {
    private static byte byteCounter = 0;
    private static char[] chars = "abcdefghijklmnopqrstuwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static int charCounter = -1;
    private static short shortCounter = 0;
    private static int intCounter = 0;
    private static long longCounter = 0;
    private static float floatCounter = 0;
    private static double doubleCounter = 0;
    public static Generator<Boolean> getBooleanGenerator() {
        return () -> Math.random() > 0.5;
    }

    public static Generator<Byte> getByteGenerator() {
        return () -> byteCounter++;
    }

    public static Generator<Character> getCharacterGenerator() {
        return () -> {
            charCounter = (charCounter + 1) % chars.length;
            return chars[charCounter];
        };
    }

    public static Generator<String> getStringGenerator() {
        return CountingGenerator.getStringGenerator(7);
    }

    public static Generator<String> getStringGenerator(int size) {
        return () -> {
            Generator<Character> characterGenerator = CountingGenerator.getCharacterGenerator();
            char[] buffer = new char[size];
            for (int i = 0; i < size; i++) {
                buffer[i] = characterGenerator.next();
            }
            return new String(buffer);
        };
    }

    public static Generator<Short> getShortGenerator() {
        return () -> shortCounter++;
    }

    public static Generator<Integer> getIntegerGenerator() {
        return () -> intCounter++;
    }

    public static Generator<Long> getLongGenerator() {
        return () -> longCounter++;
    }

    public static Generator<Float> getFloatGenerator() {
        return () -> {
            floatCounter += 1.0f;
            return floatCounter;
        };
    }

    public static Generator<Double> getDoubleGenerator() {
        return () -> {
            doubleCounter += 1.0;
            return doubleCounter;
        };
    }
}
