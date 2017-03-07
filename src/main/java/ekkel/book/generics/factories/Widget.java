package ekkel.book.generics.factories;

import ekkel.book.typeinfo.factory.Factory;

/**
 * Created by cresh on 01.12.16.
 */
class Widget {
    static class FactoryBuilder implements Factory<Widget> {
        @Override
        public Widget create() {
            return new Widget();
        }
    }
}
