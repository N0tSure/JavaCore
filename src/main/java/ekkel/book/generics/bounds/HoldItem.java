package ekkel.book.generics.bounds;

/**
 * Created by cresh on 03.12.16.
 */
class HoldItem<T> {
    protected T item;

    HoldItem(T item) {
        this.item = item;
    }

    T getItem() {
        return item;
    }
}
