package ekkel.book.innerclasses.sequence;

/**
 * Created by cresh on 29.05.16.
 */
interface Selector<T> {
    boolean end();
    T current();
    void next();
}
