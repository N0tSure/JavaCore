package ekkel.book.generics.self.bounding;

/**
 * Created by cresh on 15.12.16.
 */
class C extends SelfBounded<C> {
    C setAndGet(C c) {
        this.set(c);
        return this.get();
    }
}
