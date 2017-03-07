package ekkel.book.generics.bounds;

import java.awt.Color;

/**
 * Created by cresh on 03.12.16.
 */
class Colored<T extends HasColor> extends HoldItem<T> {

    Colored(T item) {
        super(item);
    }

    Color color() {
        return item.getColor();
    }
}
