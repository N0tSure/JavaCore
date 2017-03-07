package ekkel.book.typeinfo.pets;

import ekkel.book.typeinfo.pets.*;
/**
 * Created by cresh on 13.12.16.
 */
class Second<T> {
    T t;
    T get() {
        return t;
    }

    static <M> void setT(First<? super M> first, M m) {
        first.set(m);

    }

    public static void main(String[] args) {
        setT(new First<Pet>(), new Pet());
        setT(new First<Pet>(), new Cat());
        setT(new First<Cat>(), new Manx());
        setT(new First<Manx>(), new Cymric());

    }
}
