package com.artemsirosh.tij.generics.bounds;

/**
 * Created by cresh on 03.12.16.
 */
class Solid<T extends Dimension & HasColor & Weight> extends ColoredDimension<T> {

    Solid(T item) {
        super(item);
    }

    int weight() {
        return item.weight();
    }
}
