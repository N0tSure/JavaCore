package ekkel.book.generics.bounds;

import java.awt.*;

/**
 * Created by cresh on 03.12.16.
 */
class Bounds {
    private static <T extends HasColor> Color color(T colored) {
        return colored.getColor();
    }

    private static <T extends Weight> int weight(T weighed) {
        return weighed.weight();
    }

    public static void main(String[] args) {
        Liquid liquid = new Liquid();
        color(liquid);
        weight(liquid);
    }
}
