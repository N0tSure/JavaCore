package ekkel.book.generics.self.bounding;

/**
 * Created by cresh on 15.12.16.
 */
class SelfBounded<T extends SelfBounded<T>> {
    private T t;

    SelfBounded<T> set(T t) {
        this.t = t;
        return this;
    }

    T get() {
        return t;
    }
}
