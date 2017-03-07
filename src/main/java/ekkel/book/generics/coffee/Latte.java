//: generics/coffee/Latte.java
package ekkel.book.generics.coffee;
public class Latte extends Coffee {
    static class Factory implements ekkel.book.typeinfo.factory.Factory<Latte> {
        @Override
        public Latte create() {
            return new Latte();
        }
    }
} ///:~
