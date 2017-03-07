package ekkel.book.generics.factories;

/**
 * Created by cresh on 01.12.16.
 */
class FactoryConstraint {
    public static void main(String[] args) {
        new Foo<Integer>(new IntegerFactory());
        new Foo<Widget>(new Widget.FactoryBuilder());
    }
}
