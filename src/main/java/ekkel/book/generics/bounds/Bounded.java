package ekkel.book.generics.bounds;

import java.awt.*;

/**
 * Created by cresh on 03.12.16.
 */
class Bounded extends Dimension implements HasColor, Weight {
    @Override
    public int weight() {
        return 0;
    }

    @Override
    public Color getColor() {
        return null;
    }
}
