//: generics/coffee/Cappuccino.java
package ekkel.book.generics.coffee;
public class Cappuccino extends Coffee {
    static class Factory implements ekkel.book.typeinfo.factory.Factory<Cappuccino> {
        @Override
        public Cappuccino create() {
            return new Cappuccino();
        }
    }
} ///:~
