package com.artemsirosh.tij.generics.store;

import java.util.ArrayList;

/**
 * Created by cresh on 29.08.16.
 */
class Aisle extends ArrayList<Shelf> {
    Aisle(int amountShelves, int amountProducts) {
        for (int i = 0; i < amountShelves; i++) {
            this.add(new Shelf(amountProducts));
        }
    }
}
