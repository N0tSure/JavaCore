package ekkel.book.generics.store;

import ekkel.book.generics.Generators;

import java.util.ArrayList;

/**
 * Created by cresh on 29.08.16.
 */
class Shelf extends ArrayList<Product> {
    Shelf(int amountProducts) {
        Generators.fill(this,Product.generator(),amountProducts);
    }
}
