package ekkel.book.generics;

/**
 * Created by cresh on 15.12.16.
 */
abstract class Bounded<T extends Bounded<T>> {
    abstract T first(T t);
    T derived(T t) {
       return this.first(t);
    }
}
