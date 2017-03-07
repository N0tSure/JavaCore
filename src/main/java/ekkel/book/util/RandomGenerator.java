package ekkel.book.util;

import java.util.Random;

/**
 * Created by cresh on 06.02.17.
 */
public class RandomGenerator {
    private static char[] chars = "abcdefghijklmnopqrstuwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static Random random = new Random(42);

    public static Generator<Boolean> getBooleanGenerator() {
        return () -> random.nextBoolean();
    }

    public static Generator<Byte> getByteGenerator() {
        return () -> (byte) random.nextInt();
    }

    public static Generator<Character> getCharacterGenerator() {
        return () -> chars[random.nextInt(chars.length)];
    }

    public static Generator<String> getStringGenerator() {
        return RandomGenerator.getStringGenerator(7);
    }

    public static Generator<String> getStringGenerator(int size) {
        return () -> {
            Generator<Character> characterGenerator = RandomGenerator.getCharacterGenerator();
            char[] buffer = new char[size];
            for (int i = 0; i < size; i++) {
                buffer[i] = characterGenerator.next();
            }
            return new String(buffer);
        };
    }

    public static Generator<Short> getShortGenerator() {
        return () -> (short) random.nextInt();
    }

    public static Generator<Integer> getIntegerGenerator(int modulo) {
        return () -> random.nextInt(modulo);
    }

    public static Generator<Integer> getIntegerGenerator() {
        return RandomGenerator.getIntegerGenerator(1000);
    }

    public static Generator<Long> getLongGenerator() {
        return RandomGenerator.getLongGenerator(1000);
    }

    public static Generator<Long> getLongGenerator(int modulo) {
        return () -> (long) random.nextInt(modulo);
    }

    public static Generator<Float> getFloatGenerator() {
        return () -> {
            int trimmed = Math.round(random.nextFloat() * 100);
            return ((float) trimmed)/100;
        };
    }

    public static Generator<Double> getDoubleGenerator() {
        return () -> {
            long trimmed = Math.round(random.nextDouble() * 100);
            return ((double) trimmed)/100;
        };
    }
}
