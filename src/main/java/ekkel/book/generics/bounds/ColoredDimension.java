package ekkel.book.generics.bounds;

/**
 * Created by cresh on 03.12.16.
 */
class ColoredDimension<T extends Dimension & HasColor> extends Colored<T> {
    ColoredDimension(T item) {
        super(item);
    }

    int getX() {
        return item.x;
    }

    int getY() {
        return item.y;
    }

    int getZ() {
        return item.z;
    }
}
