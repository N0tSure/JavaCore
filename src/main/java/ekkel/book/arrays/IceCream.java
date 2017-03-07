package ekkel.book.arrays;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by cresh on 24.01.17.
 */
public class IceCream {
    private static Random random = new Random(47);
    private static final String[] FLAVORS = {
            "Chocolate", "Strawberry", "Vanilla Fudge Swirl",
            "Mint Chip", "Mocha Almond Fudge", "Rum Raisin",
            "Praline Cream", "Mud Pie"
    };

    private static String[] flavorSet(int amount) {
        if (amount > FLAVORS.length)
            throw new IllegalArgumentException("Set too big");

        String[] result = new String[amount];
        boolean[] picked = new boolean[FLAVORS.length];
        for (int tmp, i = 0; i < amount; i++) {
            do {
                tmp = random.nextInt(FLAVORS.length);
            } while (picked[tmp]);
            result[i] = FLAVORS[tmp];
            picked[tmp] = true;
        }
        return result;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 7; i++) {
            System.out.println(Arrays.toString(flavorSet(3)));
        }
    }
}
